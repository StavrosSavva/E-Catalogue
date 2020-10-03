package com.e_catalogue;


import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class helps to connect to the server database
 * Firstly to be used with local host and the to an actual database server
 * @author Stavros Savva
 * @date 06/06/2020*/
public class ServerDatabase {
    private static final String LOG = "DEBUG";
    private static String ip = "192.168.3.85"; //the ip of the database
    private static String port = "1433"; //the port to needed to access the database
    private static String classs = "net.sourceforge.jtds.jdbc.Driver"; //?
    private static String db = "THTData"; //name of the database
    private static String un = "sa"; //usename credentials
    private static String password = "admin"; //password credentials

    /**
     * Method to establish the connection
     * @author Stavros Savva
     * @date same as the parent class
     * @return conn which is the connection with the database */
    public static Connection connect() {
        Connection conn = null;
        String ConnURL = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip +":"+port+";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            Log.d(LOG, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.d(LOG, e.getMessage());
        }
        return conn;
    }
}
