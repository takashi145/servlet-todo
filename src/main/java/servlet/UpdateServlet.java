package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetTaskLogic;
import dao.UpdateTaskLogic;
import model.Task;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		GetTaskLogic getTaskLogic = new GetTaskLogic();
		Task task = getTaskLogic.execute(id);
		request.setAttribute("task", task);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String task_name = request.getParameter("task_name");
		String explanation = request.getParameter("explanation");
		String deadline_str = request.getParameter("deadline");
		
		Date deadline = null;
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			deadline = sdFormat.parse(deadline_str);
			
			Task task = new Task(task_name, explanation, deadline);
			
			UpdateTaskLogic updateTaskLogic = new UpdateTaskLogic();
			updateTaskLogic.execute(task, id);
			
			response.sendRedirect("/Todo/IndexServlet");
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}

}
