package dao;

public class DeleteTaskLogic {
	public void execute(int id) {
		TaskDAO dao = new TaskDAO();
		dao.delete(id);
	}
}
