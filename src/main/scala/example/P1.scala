package example

import scala.io.StdIn.readLine;
import java.io._;
import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.io.PrintWriter;
import scala.io.Source;


object UDFHiveSpark {
  var NameArray= new Array[String](20)
  var PassArray= new Array[String](20)
  var UserInput: String = ""
  var UserSwitchCheck: String = ""
  var UserInputName: String = ""
  var UserInputTempName: String = ""
  var UserInputPass: String = "" 
  var UserInputTempPass: String = ""
  var UserInput2: String = ""
  var UserInput3: String = ""
  var AdminName: String = "Admin"
  var BasicName: String = "Basic"
  var AdminPass: String = "Admin"
  var BasicPass: String = "Basic"
  NameArray(0) = AdminName;
  NameArray(1) = BasicName;
  PassArray(0) = AdminPass;
  PassArray(1) = BasicPass;
  var Check: Boolean = true 
  var CheckCred: Boolean = false
  var CheckMatch: Boolean = true
  var CheckLoop: Boolean = false
  var temp: Int = 0
  var Hold: Int = 0
  


  
  
  def loginCheck ()  = {
    CheckCred == false
    while (CheckCred == false){
        println("Welcome to the Super Smash Bros Ultimate Popularity Analyzer. Please input your username: ")

            //Admin Login Info
        UserInputName = scala.io.StdIn.readLine()
        if (UserInputName == AdminName){
          println("Please input your password here:")
          UserInputPass = scala.io.StdIn.readLine()
          if (UserInputPass == AdminPass){
              println("Credentials accepted.")
              CheckCred = true;
          }
          else{
              println("Password incorrect. Would you like to try again?:" +
              "\n Yes/ No")
              UserInput = scala.io.StdIn.readLine()
              if (UserInput.equalsIgnoreCase("No")){
                  println("Understood. Take Care!")
                 System.exit(0)          }
                }
            }
        //Basic User Info
        else if (UserInputName == BasicName){
          println("Please input your password here:")
          UserInputPass = scala.io.StdIn.readLine()
          if (UserInputPass == BasicPass){
              println("Credentials accepted.")
              CheckCred = true;
          }
          else{
              println("Password incorrect. Would you like to try again?: " + 
              "\n Yes/No")
               UserInput = scala.io.StdIn.readLine()
              if (UserInput.equalsIgnoreCase("No")){
                 println("Understood. Take Care!")
                 System.exit(0)          }
                }
        }
        //Checking for other Users
        else{
            for (i <- 0 until NameArray.length){
                if (UserInputName == NameArray(i)){
                    println("Please input your password here:")
                    UserInputPass = scala.io.StdIn.readLine()
                    if (UserInputPass == PassArray(i)){
                        println("Credentials accepted.")
                        CheckCred = true;
                  }
                }
            }
            //User Not Found
            if (CheckCred != true){
            println("Username is not found within our system. Would you like to try again?:" +
            "\n Yes/No" )

            UserInput = scala.io.StdIn.readLine()
            if (UserInput.equalsIgnoreCase("No")){
                println("Understood. Take Care!")
                System.exit(0)
            }
        }

        }
    }
}
    
    
    def main(args: Array[String]) {

        val driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

        Class.forName(driverName);

        val con = DriverManager.getConnection("jdbc:hive://localhost:10000/default", "", "");
        val stmt = con.createStatement();
        val allTable = "project1.smashchar";
        val dlcTable = "project1.smashmiss";
        val playerTable = "project1.smashplayers";
  
    
    while (Check == true) {
        //Beginning Of User Input Menu
      if (CheckCred == false){
           loginCheck()
      }

      if (UserInputName == AdminName){
          println("What would you like to do today?: " +
          "\n A) View Query Questions" +
          "\n B) Switch Users" +
          "\n C) Add/Remove Users" +
          "\n D) End Program")}
      else{
          println("What would you like to do today?: " +
          "\n A) View Query Questions" +
          "\n B) Switch Users" +
          "\n C) Update Basic User Info" +
          "\n D) End Program")}
      
      UserInput = scala.io.StdIn.readLine()
      UserSwitchCheck = UserInput
      if (UserInput.equalsIgnoreCase("A")) {

           println("Here are the Query Questions currently provided within the program. Which One would you like to view?:" +
             "\n A) Most Popular Smash Character" +
             "\n B) Least Popular Smash Character" +
             "\n C) Most Popular DLC Character" +
             "\n D) Least Popular DLC Character"+
             "\n E) Most Requested Character to not make it into SMash as DLC" +
             "\n F) Most Popular Pro Player in the SSBU Tournament community")
           UserInput2= scala.io.StdIn.readLine()

           //Option 1, Most Popular Smash Character
           if (UserInput2.equalsIgnoreCase("A")){
                // select * query
                val sql = "select get_json_object(str,'$.text') as Tweet From," + allTable;
                val res = stmt.executeQuery(sql);
                while (res.next()) {
                     System.out.println(res.getString(1));
                }
                }

          // Option 2, Least Popular Smash Character
           if (UserInput2.equalsIgnoreCase("B")){
               // select * query
                val sql = "select get_json_object(str,'$.text') as Tweet From," + allTable;
                val res = stmt.executeQuery(sql);

                
           }

           //Option 3, Most Popular DLC Smash Character
           if (UserInput2.equalsIgnoreCase("C")){
               // select * query
                val sql = "select get_json_object(str,'$.text') as Tweet From," + allTable;
                val res = stmt.executeQuery(sql);
        }

          //Option 4, Least Popular DLC Smash Character
           if (UserInput2.equalsIgnoreCase("D")){
               // select * query
                val sql = "select get_json_object(str,'$.text') as Tweet From," + allTable;
                val res = stmt.executeQuery(sql);

        }

          //option 5, Most Requested Smash DLC Character
           if (UserInput2.equalsIgnoreCase("E")){
               // select * query
                val sql = "select get_json_object(str,'$.text') as Tweet From," + dlcTable;
                val res = stmt.executeQuery(sql);

        }

          //option 6, Most Popular Prop Player in the SSBU Tournament Community
           if (UserInput2.equalsIgnoreCase("F")){
               // select * query
                val sql = "select get_json_object(str,'$.text') as Tweet From," + playerTable;
                val res = stmt.executeQuery(sql);

        }
       

      }
      if (UserInput.equalsIgnoreCase("B")) {

           println("You have chosen to switch Users. Would you like to continue?" +
           "\n Yes/ No")
           UserInput2= scala.io.StdIn.readLine()

           //Option 1-1: Single Card Insert into Card Catalogue(good)
           if (UserInput2.equalsIgnoreCase("Yes")){
               CheckCred = false
               loginCheck()
        }
      }
      if (UserInput.equalsIgnoreCase("C") && UserInputName == AdminName) {

           println("You have chosen to add/remove Users. Which would you like to do?:" +
           "\n A) Add User" +
           "\n B) Remove User")
           UserInput2= scala.io.StdIn.readLine()

           //Adding a User to the System
           if (UserInput2.equalsIgnoreCase("A")){
               CheckMatch = false
               println("Please Insert the new UserName you Would Like to use:")
               UserInputTempName = scala.io.StdIn.readLine()
               while(CheckMatch == false){
                    println("Please Insert the new password you would like to use:")
                    UserInputPass = scala.io.StdIn.readLine()
                    println("Please re-enter the password just given:")
                    UserInputTempPass = scala.io.StdIn.readLine()
                    if(UserInputPass == UserInputTempPass){
                       println("Confirmed. Entering Information into the system now.")
                       CheckMatch = true;
                    }
                    else{
                       println("Passwords given did not match. Would you like to try again? If not, the program will close.:"+
                     " Yes/ No ")
                       UserInput3 = scala.io.StdIn.readLine()
                       if(UserInput3.equalsIgnoreCase("no")){
                          println("Understood. Have a nice day.")
                          System.exit(0)
                        }
                    }
               }
               temp = 0;
               Hold = 1;
               while(CheckLoop == false){
                   if(NameArray(temp) != null){
                    if(NameArray(Hold) == null){
                         NameArray(Hold) = UserInputTempName 
                         println("Here is the New USerName inputted into the system: " + NameArray(Hold))
                         println("Here is the position that it was added in: " + Hold)
                         PassArray(Hold) =  UserInputTempPass
                         println("Here is the password that was added in: " +PassArray(Hold))
                         println("Here is the position where it was added in: " +Hold)
                         CheckLoop = true
                    }
                   temp = temp + 1
                   Hold = Hold + 1
                }
               }
            }
  
             //Removing a User to the System
           if (UserInput2.equalsIgnoreCase("B")){
               CheckMatch = false
               while (CheckMatch == false){
                 println("Please Insert the UserName you Would Like to Remove:")   
                 UserInputTempName = scala.io.StdIn.readLine()
                 println("To Confirm, please also enter the password for the user being removed: ")
                 UserInputTempPass = scala.io.StdIn.readLine()
                 for(i <- 0 until NameArray.length){
                    if(UserInputTempName == NameArray(i)){
                        println("User Found. Removing Information.")
                        NameArray(i) = null
                        PassArray(i) = null 
                        CheckMatch = true
                    }
                 }
                 if(CheckMatch == false){
                     println("User Could not be found. Would you like to try again?:" +
                     "\n Yes/ No")
                     UserInput3 = scala.io.StdIn.readLine()
                     if (UserInput3.equalsIgnoreCase("no")){
                         println("Understood. No User was Removed.")
                         CheckMatch = true
                     }
                 }
                }  
            } 
        }
        if (UserInput.equalsIgnoreCase("C") && UserInputName != AdminName) {
            println("You have selected to Change Information within your User Profile. What would you like to change?:" +
            " \n A) UserName" +
            " \n B) Password")
            UserInput2 = scala.io.StdIn.readLine()
            if(UserInput2.equalsIgnoreCase("A")){
                println("Please enter the new Username for this profile:")
                UserInputTempName = scala.io.StdIn.readLine()
                for(i <- 0 until NameArray.length){
                    if(UserInputName == NameArray(i)){
                        NameArray(i) = UserInputTempName
                        Hold = i
                        CheckMatch = true
                    }
                }
                println("Here is the new Username for the profile: " +NameArray(Hold))
            }
            if(UserInput2.equalsIgnoreCase("B")){
                CheckMatch = false
                while(CheckMatch == false){
                    println("Please enter the old password for this profile:")
                    UserInputTempPass = scala.io.StdIn.readLine()
                    if(UserInputTempPass == UserInputPass){
                        println("Confirmed. Please enter the new password: ")
                        UserInputTempPass = scala.io.StdIn.readLine()
                        for(i <- 0 until PassArray.length){
                             if(UserInputPass == PassArray(i)){
                                 PassArray(i) = UserInputTempPass
                                 Hold = i
                                 CheckMatch = true
                             }
                        }
                        println("The password has been adjusted to: " +PassArray(Hold))
                    }
                    else{
                        println("Password is Incorrect. Would you like to try again?" +
                        " \n Yes/No")
                        UserInput3 = scala.io.StdIn.readLine()
                        if(UserInput3.equalsIgnoreCase("no")){
                            println("Understood. Have a nice day.")
                            System.exit(0)
                        }

                    }
            }
        }
        }


    


          
      

      
      if (UserSwitchCheck.equalsIgnoreCase("B")){
          println("Users Switched.")
      }
      else if(UserInput.equalsIgnoreCase("D")){
      println("You Have Chosen to End the Program. Is that Correct? (Yes/No)")

      UserInput = scala.io.StdIn.readLine()

      if (UserInput.equalsIgnoreCase("Yes")) {
        Check = false
        println("Thank You. Have a nice day!")
      }
    }




      else{
      println("Would you like to continue running the program? (Yes/No)")

      UserInput = scala.io.StdIn.readLine()

      if (UserInput.equalsIgnoreCase("No")) {
        Check = false
        println("Thank You. Have a nice day!")
      }
    }
    }
    }   
  }
