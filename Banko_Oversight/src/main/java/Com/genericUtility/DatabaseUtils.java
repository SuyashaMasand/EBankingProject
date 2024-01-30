package Com.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtils {


    public	Connection conn;

	/**
	 * This method is used to get the connection of the database
	 * @throws SQLException 
	 */
	public void getConnection() throws SQLException {
		//register driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);

		//get connection
		conn = DriverManager.getConnection(IpathConstants.DBURL , IpathConstants.DBUsername , IpathConstants.DBPassword);

	}


	/**
	 * this method is used to execute query
	 * @param query
	 * @param colIndex
	 * @param ExpData
	 * @return
	 * @throws SQLException 
	 */
	public String ExecuteQuery(String query , int colIndex , String ExpData) throws SQLException {
		//create statement and execute query
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		String  data ="";
		boolean flag=false;
		while(result.next()) {
			data = result.getString(colIndex);
			if(data.equalsIgnoreCase(ExpData)) {

				flag = true;
				break;
			}
		}
			if(flag)
			{
				System.out.println(data+"----> data verified");
				return ExpData;
			}
			else {
				System.out.println("data not verified");

				return "";

			} 



		}
	






	/**
	 * This method is to close the database connection
	 * @param conn
	 * @throws SQLException 
	 */
	public void closeConnection() throws SQLException {

		conn.close();


	}



}
