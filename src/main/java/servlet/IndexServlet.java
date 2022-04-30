package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GetTaskListLogic;
import model.Task;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorList");
		
		GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
		List<Task> taskList = getTaskListLogic.execute();
		request.setAttribute("taskList", taskList);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}
	
}
