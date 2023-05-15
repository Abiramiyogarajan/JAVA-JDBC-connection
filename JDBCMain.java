import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMain {

	//private static Connection con=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	Connection con= null;
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
		//	String dbUrl="jdbc:mysql://localhost:3306/studentdemo";
			//String username="root";
			//String pswd = "root";
			//con =DriverManager.getConnection(dbUrl+username+pswd);			
			con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/company","root","root");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			
			ResultSet rs=stmt.executeQuery("select * from employeedetails");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
			//con.close();  
			}catch(Exception e){ System.out.println(e);}  
		

		
		
		// Insert Statement
		
		try {
			String sql = "INSERT INTO employeedetails (name, fathername,address) VALUES (?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			//statement.setInt(1,4);
			statement.setString(1, "Chandru");
			statement.setString(2, "varun");
			statement.setString(3, "kandha street");
			statement.executeUpdate();
			System.out.println("Record created.");
			} catch (SQLException e) {
			e.printStackTrace();
			}

		

	}

}
