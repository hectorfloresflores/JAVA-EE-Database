package com.hectorflores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStat = null;
		ResultSet myRes = null;

		try {
			// get connection
			myConn = dataSource.getConnection();
			// Create sql statement
			String sql = "select * from student ORDER BY lastName";
			myStat = myConn.createStatement();
			// Execite query
			myRes = myStat.executeQuery(sql);
			// pROCESS RESULT SET
			while (myRes.next()) {
				// retrieve data from result set row
				int id = myRes.getInt("id");
				String name = myRes.getString("Name");
				String lastName = myRes.getString("lastName");
				String email = myRes.getString("email");

				// create new student object
				Student theStudent = new Student(name, lastName, email, id);
				// add ut to the list of students
				students.add(theStudent);
			}

			return students;

		} finally {
			// Close JDBC Objects
			close(myConn, myRes, myStat);
		}

	}

	private void close(Connection myConn, ResultSet myRes, Statement myStat) {

		try {
			if (myRes != null) {
				myRes.close();
			}
			if (myConn != null) {
				myConn.close();
			}
			if (myStat != null) { // Make it available
				myStat.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addStudent(Student me) throws SQLException {
		Connection myConn = null;
		Statement myStat = null;
		ResultSet myRes = null;

		try {
			// get connection
			myConn = dataSource.getConnection();
			// Create sql statement
			myStat = myConn.createStatement();
			
			myRes = myStat.executeQuery("SELECT MAX(id) FROM Student;");
			
			myRes.next();
			int a = myRes.getInt("MAX(id)");
			// the mysql insert statement
			String query = " insert into Student (Name, LastName, email, id)" + " values (?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			preparedStmt.setString(1, me.getName());
			preparedStmt.setString(2, me.getLastName());
			preparedStmt.setString(3, me.getEmail());

			preparedStmt.setInt(4, a+1);

			// execute the preparedstatement
			preparedStmt.execute();

		} finally {
			// Close JDBC Objects
			close(myConn, myRes, myStat);
		}
	}

	public Student getStudent(String id) throws Exception {
		// TODO Auto-generated method stub
		Student theStudent = null;
		Connection myConn = null;
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		
		try {
			int i = Integer.parseInt(id); 
			myConn = dataSource.getConnection();
			
			 String sql = "SELECT * FROM Student WHERE id=?";
			 
			 myStat = myConn.prepareStatement(sql);
			 
			 myStat.setInt(1, i);
			 
			 myRes = myStat.executeQuery();
			 
			 //retrive data from result set row
			 if (myRes.next()) {
				String name = myRes.getString("Name");
				String lastName = myRes.getString("LastName");
				String email = myRes.getString("email");
				Integer myid = myRes.getInt("id");
				theStudent = new Student(name, lastName, email);
				theStudent.setId(myid);
			}else {
				throw new Exception("Could not find student id: " + i);
			}
			 
			return theStudent;
		}finally {
			close(myConn, myRes, myStat);
		}
	}

	public void updateStudent(Student me) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "UPDATE Student " + 
					"SET Name = ?, LastName = ?, email = ?" + 
					" WHERE id = ?";
			myStat = myConn.prepareStatement(sql);
			 
			myStat.setString(1, me.getName());
			myStat.setString(2, me.getLastName());
			myStat.setString(3, me.getEmail());
			myStat.setInt(4, me.getId());
			
			myStat.execute();
			 
		}finally {
			close(myConn, myRes, myStat);
		}
		
	}

	public void deleteStudent(String parameter) throws SQLException {
		
		Connection myConn = null;
		
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		
		try {
			myConn = dataSource.getConnection();
			
			
			
			String sql = "DELETE FROM Student WHERE id = ?;";
			myStat = myConn.prepareStatement(sql);
			myStat.setInt(1, Integer.parseInt(parameter));
			
			myStat.execute();
			 
		}finally {
			close(myConn, myRes, myStat);
		}
		
	}

}
