package com.jdbc.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employeedatabase {

	private static Connection con = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Employeedatabase empdb = new Employeedatabase();

		try {
			// download jdbc driver and configure build path -> add external jars
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost:3306/company";
			String username = "root";
			String pswd = "root";
			con = DriverManager.getConnection(dbUrl, username, pswd);
			
			System.out.println("Enter Your Choice");
			System.out.println("1. Insert Record");
			System.out.println("2.Select Record");
			System.out.println("3.Update Record");
			System.out.println("4.Delete record");
			System.out.println("0. Exit");
			
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				empdb.insertRecord();
				break;
			case 2:
				empdb.selectRecord();
				break;
			case 3:
				empdb.updateRecord();
				break;
			case 4:
				empdb.deleteRecord();
				break;
			case 0:

				break;

			default:
				break;
			}

		} catch (Exception e) {
			throw new RuntimeException("Something is wrong with the driver");
		}
	}

	private void deleteRecord() throws SQLException {
		System.out.println("Enter the ID to be deleted");
		int id = Integer.parseInt(scanner.nextLine());
		String sql = "delete from employeedetails where id = " + id;

		Statement stmt = con.createStatement();
		int rows = stmt.executeUpdate(sql);
		if (rows > 0) {
			System.out.println("Record deleted successfully");
		}
	}

	public void insertRecord() throws SQLException {
		System.out.println("entering");
		String sql = "insert into employeedetails(name,fathername,address) values (?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		System.out.println("Enter name");
		preparedStatement.setString(1, scanner.nextLine());
		System.out.println("Enter Father Name");
		preparedStatement.setString(2, scanner.nextLine());
		System.out.println("Enter Address");
		preparedStatement.setString(3, scanner.nextLine());
		preparedStatement.executeUpdate();

		int rows = preparedStatement.executeUpdate();
		if (rows > 0)
			System.out.println("inserted successfully");
	}

	private void selectRecord() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("select method entering");
		System.out.println("Enter the Id to Fetch details");
		int id = Integer.parseInt(scanner.nextLine());
		String sql = "select * from employeedetails where id= " + id;
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(sql);

		if (result.next()) {
			String empID = result.getString("id");
			String empName = result.getString("name");
			String empFatherName = result.getString("fathername");
			String address = result.getString("address");
			System.out.println("Emp id is " + empID + " Employee Name is " + empName + " Employee's Father Name is "
					+ empFatherName + " Address is " + address);

		} else {
			System.out.println("No Records Found");
		}

	}

	private void updateRecord() throws SQLException {
		System.out.println("Enter the Id to be updated");
		int id = Integer.parseInt(scanner.nextLine());
		String sql = "select * from employeedetails where id= " + id;
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(sql);

		if (result.next()) {
			String empID = result.getString("id");
			String empName = result.getString("name");
			String empFatherName = result.getString("fathername");
			String address = result.getString("address");
			System.out.println("Emp id is " + empID + " Employee Name is " + empName + " Employee's Father Name is "
					+ empFatherName + " Address is " + address);
			System.out.println("What do you want to update?");
			System.out.println("1.Name");
			System.out.println("2.Father name");
			System.out.println("3.Address");
			int choicetoUpdate = Integer.parseInt(scanner.nextLine());

			String baseQuery = "update employeedetails set ";
			switch (choicetoUpdate) {
			case 1:
				System.out.println("Name to be updated?");
				String newName = scanner.nextLine();

				baseQuery = baseQuery + "name = ? where id = " + id;
				PreparedStatement prepStatement = con.prepareStatement(baseQuery);
				prepStatement.setString(1, newName);
				int rows = prepStatement.executeUpdate();
				if (rows > 0) {
					System.out.println("Record successfully Updated With name" + newName);
				}
				break;
			case 2:
				System.out.println("Father name to be updated?");
				String newFatherName = scanner.nextLine();

				baseQuery = baseQuery + "fathername = ? where id = " + id;
				PreparedStatement prepStatement1 = con.prepareStatement(baseQuery);
				prepStatement1.setString(1, newFatherName);
				int rows1 = prepStatement1.executeUpdate();
				if (rows1 > 0) {
					System.out.println("Record successfully Updated With name" + newFatherName);
				}
				break;
			case 3:
				System.out.println("Address to be updated?");
				String newAddress = scanner.nextLine();

				baseQuery = baseQuery + "address = ? where id = " + id;
				PreparedStatement prepStatement2 = con.prepareStatement(baseQuery);
				prepStatement2.setString(1, newAddress);
				int rows2 = prepStatement2.executeUpdate();
				if (rows2 > 0) {
					System.out.println("Record successfully Updated With name" + newAddress);
				}

				break;

			default:
				break;
			}
		} else {
			System.out.println("No Records Found");
		}

	}

}
