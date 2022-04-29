package dao;

import model.Task;

public class CreateTaskLogic {
	public void execute(Task task) {
		TaskDAO dao = new TaskDAO();
		dao.create(task);
	}
}
