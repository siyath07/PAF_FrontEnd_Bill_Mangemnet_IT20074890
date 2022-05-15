package model;

import java.sql.*;

public class Ebill {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bill", "root", "admin");
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }

	 return con;
	}
	
	//Read function
	public String readEbill()
	{
	 String output = "";
	 
	 try
	 {
	  Connection con = connect();
	  if (con == null)
	  {
		  return "Error while connecting to the database for reading.";
	  }
	 
	// Prepare the HTML table to be displayed
	 output = "<table border='1' class='table table-striped'><tr>"
	 + "<th>Bill ID</th>"
	 + "<th>User ID</th>"
	 + "<th>Amount of Unit</th>"
	 + "<th>Total Amount</th>"
	 + "<th>Update</th><th>Delete</th></tr>";
	 
	 String query = "select * from ebill";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // Iterate through the rows in the result set
	 while (rs.next())
	 {
		 String noticeId = Integer.toString(rs.getInt("noticeId"));
		 String billID = rs.getString("billID");
		 String cusID = rs.getString("cusID");
		 String unit = rs.getString("unit");
		 String amount = rs.getString("amount");
	 
		 // Add a row into the HTML table
//		 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + noticeId
//				 + "'>" + phone + "</td>"; 
		 output += "<tr><td><input id=\'hidItemIDUpdate\' name=\'hidItemIDUpdate\' type=\'hidden\' value=\'"
					+ noticeId + "'>" + billID + "</td>";
		 output += "<td>" + cusID + "</td>";
		 output += "<td>" + unit + "</td>";
		 output += "<td>" + amount + "</td>";
		
		 
		 // Buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success' data-noticeid='" + noticeId +"'></td>"
		 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-noticeid='" + noticeId + "'></td></tr>";
	 }
	 con.close();
	 
	 // Complete the HTML table
	 output += "</table>";
	}
	 
	catch (Exception e)
	{
		output = "Error while reading the EBill.";
		System.err.println(e.getMessage());
	}
	 
	return output;
	}
	
	//Insert function
	public String insertEbill(String billID, String cusID, String unit, String amount)
	{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 
	 // Prepared statement
	 String query = " insert into ebill(`noticeId`,`billID`,`cusID`,`unit`, `amount`)" + " values (?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // Binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, billID);
	 preparedStmt.setString(3, cusID);
	 preparedStmt.setString(4, unit); 
	 preparedStmt.setString(5, amount);
	 
	 //Execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newEbill = readEbill();
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 		newEbill + "\"}"; 
	 }
	
	catch (Exception e)
	{
	 //output = "Error while inserting the notice";
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Ebill.\"}"; 
	 System.err.println(e.getMessage());
	 }
	return output; 
	}
	
	//Update function
	public String updateEbill(String noticeId, String billID, String cusID, String unit, String amount) 
	{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // Create a prepared statement
			 String query = "update ebill set billID=?, cusID=?, unit=?, amount=? where noticeId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // Binding values
			 preparedStmt.setString(1, billID);
			 preparedStmt.setString(2, cusID);
			 preparedStmt.setString(3, unit);
			 preparedStmt.setString(4, amount);
			 preparedStmt.setInt(5, Integer.parseInt(noticeId));
			 
			 // Execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newEbill = readEbill();
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 	newEbill + "\"}"; 
     		 //output = "Updated successfully";
			 }
			 
			 catch (Exception e)
			 {
			 //output = "Error while updating the notice.";
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the Ebill.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
	}
	
	//Delete function
	public String deleteEbill(String noticeId)
	{
	 String output = "";
	 try
	  {
	  Connection con = connect();
	  if (con == null)
	  {
	  return "Error while connecting to the database for deleting.";
	  }
	  
	  // Create a prepared statement
	  String query = "delete from ebill where noticeId=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	  
	  // Binding values
	  preparedStmt.setInt(1, Integer.parseInt(noticeId));

	  // Execute the statement
	  preparedStmt.execute();
	  con.close();
	  
	  String newEbill = readEbill();
	  output = "{\"status\":\"success\", \"data\": \"" + newEbill + "\"}"; 
	  //output = "Deleted successfully";
	  }
	 catch (Exception e)
	  {
	  //output = "Error while deleting the notice.";
	  output = "{\"status\":\"error\", \"data\": \"Error while deleting the Ebill.\"}";
	  System.err.println(e.getMessage());
	  }
	 return output; 
	}
}
