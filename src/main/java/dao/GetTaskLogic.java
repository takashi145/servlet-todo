package dao;

import model.Task;

public class GetTaskLogic {
	public Task execute(int task_id) {
		TaskDAO dao = new TaskDAO();
		Task task = dao.findOne(task_id);
		return task;
	}
}
