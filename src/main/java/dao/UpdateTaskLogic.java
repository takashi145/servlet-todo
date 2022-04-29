package dao;

import model.Task;

public class UpdateTaskLogic {
	public void execute(Task task, int id) {
		TaskDAO dao = new TaskDAO();
		dao.update(task, id);
	}
}
