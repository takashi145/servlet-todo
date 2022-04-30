package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Task;

public class TaskDAO {
	private final String JDBC_URL = System.getenv("JDBC_URL");
	private final String DB_USER = System.getenv("DB_USER");
	private final String DB_PASS = System.getenv("DB_PASS");
	
	public List<Task> findAll() {
		List<Task> taskList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "select * "
					+ "from tasks "
					+ "where deadline >= curdate() "
					+ "or deadline is null "
					+ "order by deadline";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String task_name = rs.getString("task");
				String explanation = rs.getString("explanation");
				Date deadline = rs.getDate("deadline");
				Task task = new Task(id, task_name, explanation, deadline);
				taskList.add(task);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return taskList;
	}
	
	//期限切れのタスクのリストを返す
	public List<Task> expiredFindAll() {
		List<Task> taskList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "select * "
					+ "from tasks "
					+ "where deadline < curdate()"
					+ "order by deadline";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String task_name = rs.getString("task");
				String explanation = rs.getString("explanation");
				Date deadline = rs.getDate("deadline");
				Task task = new Task(id, task_name, explanation, deadline);
				taskList.add(task);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return taskList;
	}
	
	public boolean create(Task task) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "insert into tasks(task, explanation, deadline) values(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, task.getTask());
			pStmt.setString(2, task.getExplanation());
			java.sql.Date deadline;
			if(task.getDeadline() == null) {
				deadline = null;
			}else {
				deadline = new java.sql.Date(task.getDeadline().getTime());
			}
			pStmt.setDate(3, deadline);
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Task findOne(int task_id) {
		Task task = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "select * from tasks where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, task_id);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String task_name = rs.getString("task");
				String explanation = rs.getString("explanation");
				Date deadline = rs.getDate("deadline");
				task = new Task(id, task_name, explanation, deadline);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return task;
	}
	
	public boolean update(Task task, int id) {
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "update tasks set task=?, explanation=?, deadline=? where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, task.getTask());
			pStmt.setString(2, task.getExplanation());
			java.sql.Date deadline;
			if(task.getDeadline() == null) {
				deadline = null;
			}else {
				deadline = new java.sql.Date(task.getDeadline().getTime());
			}
			pStmt.setDate(3, deadline);
			pStmt.setInt(4, id);
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean delete(int id) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "delete from tasks where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
