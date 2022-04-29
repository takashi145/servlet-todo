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
		
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "select * from tasks";
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
			java.sql.Date deadline = new java.sql.Date(task.getDeadline().getTime());
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
}
