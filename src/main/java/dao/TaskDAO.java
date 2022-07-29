package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/todo_db?characterEncoding=UTF-8";
	private final String DB_USER = System.getenv("DB_USER");
	private final String DB_PASS = System.getenv("DB_PASS");
	
	public List<Task> findAll() {
		List<Task> taskList = new ArrayList<>();
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "(select * "
					+ "from tasks "
					+ "where deadline >= curdate() "
					+ "order by deadline)"
					+ "union"
					+ "(select * "
					+ "from tasks "
					+ "where deadline is null)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Date deadline = rs.getDate("deadline");
				Boolean status = rs.getBoolean("status");
				Task task = new Task(id, title, description, deadline, status);
				taskList.add(task);
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return taskList;
	}
	
	public List<Task> findExpiredTask() {
		List<Task> taskList = new ArrayList<>();
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "select * "
					+ "from tasks "
					+ "where deadline < curdate()"
					+ "order by deadline";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Date deadline = rs.getDate("deadline");
				Boolean status = rs.getBoolean("status");
				Task task = new Task(id, title, description, deadline, status);
				taskList.add(task);
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return taskList;
	}
	
	public boolean create(Task task) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "insert into tasks(title, description, deadline) values(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, task.getTitle());
			pStmt.setString(2, task.getDescription());
			pStmt.setDate(3, task.getDeadline());
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException  | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Task findOne(int task_id) {
		Task task = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "select * from tasks where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, task_id);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Date deadline = rs.getDate("deadline");
				Boolean status = rs.getBoolean("status");
				task = new Task(id, title, description, deadline, status);
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return task;
	}
	
	public boolean update(Task task, int id) {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "update tasks set title=?, description=?, deadline=?, status=? where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, task.getTitle());
			pStmt.setString(2, task.getDescription());
			java.sql.Date deadline;
			if(task.getDeadline() == null) {
				deadline = null;
			}else {
				deadline = new java.sql.Date(task.getDeadline().getTime());
			}
			pStmt.setDate(3, deadline);
			pStmt.setBoolean(4, task.getStatus());
			pStmt.setInt(5, id);
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean delete(int id) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "delete from tasks where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
