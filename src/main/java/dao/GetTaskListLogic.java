package dao;

import java.util.List;

import model.Task;

public class GetTaskListLogic {
	public List<Task> execute(){
		TaskDAO dao = new TaskDAO();
		List<Task> taskList = dao.findAll();
		return taskList;
	}
}
