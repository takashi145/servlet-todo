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

import dao.CreateTaskLogic;
import model.Task;

@WebServlet("/createServlet")
public class createServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public createServlet() {
        super();
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/create.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String task_name = request.getParameter("task_name");
		String explanation = request.getParameter("explanation");
		String deadline_str = request.getParameter("deadline");
		
		Date deadline = null;
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			deadline = sdFormat.parse(deadline_str);
			
			Task task = new Task(task_name, explanation, deadline);
			
			CreateTaskLogic createTaskLogic = new CreateTaskLogic();
			createTaskLogic.execute(task);
			
			response.sendRedirect("/Todo/IndexServlet");
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}

}
