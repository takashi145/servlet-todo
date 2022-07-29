package dao;

import java.util.List;

import model.Task;

public class GetTaskListLogic {
	public List<Task> execute(String type){
		TaskDAO dao = new TaskDAO();
		List<Task> taskList = null;
		if(type == null) {
			taskList = dao.findAll();
		}else if(type.equals("expired")) {
			taskList = dao.findExpiredTask();
		}
		return taskList;
	}
}
