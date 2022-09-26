package com.example.DTO;

import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseConnection {

    //URL, USERNAME AND PASSWORD TO CONNECT DATABASE FROM PROJECT
    private String URL = "jdbc:mysql://localhost:3306/MovieTicketManagment";
    private String DATABASEUSERNAME = "MAHMUT";
    private String DATABASEPASSWORD = "M.celik2001";

    //TO USE WHOLE CLASS I DEFINED THEM GLOBAL
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    //CONNECTION IN BEGINNING OF WHILE NEW OBJECT FROM CLASS
    public DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, DATABASEUSERNAME, DATABASEPASSWORD);
        }catch (ClassNotFoundException e){
            System.err.println("DATABASE CONNECTION CONSTRUCTOR EXCEPTION");
            e.printStackTrace();
        }
        catch (SQLException e){
            System.err.println("DRIVER MANAGER EXCEPTION");
            e.printStackTrace();
        }
    }

    /**
     *  RETURN VALUE
     *  0 -> NOT REGISTERED
     *  1 -> REGISTERED AS AN ADMIN
     *  2 -> REGISTERED AS AN USER
     *  -1 -> PROBLEM
     * */
    public int adminOrUserLoginControl(String username,String password){
        return adminOrUserExistControl(username,password);
    }


    /**
     *  RETURN VALUE
     *  TRUE -> COMING VALUE IS NOT 0
     *  FALSE -> COMING VALUE IS 0
     * */
    public boolean adminOrUserSignUpControl(String username, String password){
        return !(adminOrUserExistControl(username,password) == 0);
    }


    /**
     * THIS METHOD CONTROLS THAT PERSON IS EXIST IN DATABASE
     * IT HAS A QUERY AND USING PARAMETERS I HAVE SET QUERY.
     * IF RESULTSET.NEXT RETURN FALSE METHOD WILL RETURN 0 THAT MEANS PERSON DIDN'T REGISTER BEFORE
     *  RESULTSET WORKS -> * -------
     *  (*) IS ITERABLE BEGINNING SECTION AND USING NEXT() CONTROLS AND GOES THERE
     *  RESULTSET.NEXT() -> -*------ RETURNED TRUE AND SHOWS FIRST -
     *  IF RESULTSET WOULD BE ONLY -> *
     *  RESULTSET.NEXT() RETURNED FALSE
     *
     * */
    private int adminOrUserExistControl(String username,String password){
        String queryToSend = "SELECT * FROM `movieticketmanagment`.`adminanduser` WHERE USERNAME = ? AND PersonPASSWORD = ?";

        try {
            //PREPARING QUERY
            preparedStatement = connection.prepareStatement(queryToSend);

            //SET VALUES
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //EXECUTE OF QUERY AND STORING RESULTS
            resultSet = preparedStatement.executeQuery();

            //IF RESULTSET IS EMPTY RETURN 0
            if(!resultSet.next()) return 0;

            else{ //ADMIN RETURN 1 , USER RETURN 2
                if(resultSet.getString("USERorADMIN").equals("ADMIN")) return 1;
                return 2;
            }


        }catch (SQLException e){
            System.err.println("ADMIN OR USER EXIST EXCEPTION");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * TO SIGN UP NEW USER I CREATED THIS METHOD
     * AFTER CONTROL I USED BELOW PART IN SIGN UP PAGE.
     * IT PREPARE QUERY AND ADD PERSON TO DATABASE
     * */
    public void signUpNewUser(String username,String password,boolean adminRadio,boolean userRadio){
        String queryToSend = "INSERT INTO `movieticketmanagment`.`adminanduser` (USERNAME,PersonPASSWORD,USERorADMIN) VALUES (?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(queryToSend);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            if (adminRadio) {
                preparedStatement.setString(3, "ADMIN");
            } else {
                if (userRadio) {
                    preparedStatement.setString(3, "USER");
                } else {
                    preparedStatement.setString(3, "NULL");
                }
            }
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            System.err.println("SIGN UP NEW USER EXCEPTION");
            e.printStackTrace();
        }
    }


    //CLOSE PARTS

}
