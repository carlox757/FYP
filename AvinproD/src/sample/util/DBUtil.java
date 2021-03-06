package sample.util;


import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;


/**
 * Created by Carlito on 12/04/2017.
 */
public class DBUtil {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //Connection
    private static Connection conn = null;
    private static final String db = "db_k1418717";
    private static final String URL = "jdbc:mysql://kunet.kingston.ac.uk:3306/" + db;
    private static final String user = "k1418717";
    private static final String pass = "monday12";



    public static void dbConnect() throws SQLException, ClassNotFoundException{
        //Sets MySQL driver
        try{
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("Where is MySQL driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("MySQL driver Registered!");

        //Establish the MySQL Connection using Connection String

        try{
            conn = DriverManager.getConnection(URL, user, pass);
        }catch (SQLException e){
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish MySQL Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //CachedRowSet is there
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);

        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish MySQL Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }


}
