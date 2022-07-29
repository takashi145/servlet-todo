package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CreateTaskLogic;
import model.Task;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CreateServlet() {
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
		String description = request.getParameter("description");
		String new_deadline = request.getParameter("deadline");
		Date deadline = null;
		if(new_deadline != "") {
			deadline = Date.valueOf(new_deadline);
		}
		List<String> errorList = validation(task_name, description, deadline);
		
		HttpSession session = request.getSession();
		if(errorList.size() > 0) {
			session.setAttribute("errorList", errorList);
			response.sendRedirect("/Todo/CreateServlet");
		}else {
			Task task = new Task(task_name, description, deadline, false);
		
			CreateTaskLogic createTaskLogic = new CreateTaskLogic();
			createTaskLogic.execute(task);
			
			session.setAttribute("message", "タスクが追加されました。");
			response.sendRedirect("/Todo/IndexServlet");
		}
		return;
	}
	
	private List<String> validation(String task_name, String description, Date deadline) {
		List<String> list = new ArrayList<>();
		
		if(task_name == "") {
			list.add("タスク名は必須です。");
		}
		
		if(task_name.length() > 30) {
			list.add("タスク名は30文字以内で入力してください。");
		}
		
		if(description.length() > 200) {
			list.add("タスクの詳細は200文字以内で入力してください。");
		}
		
		if(deadline != null) {
			Date now = Date.valueOf(LocalDate.now());
			if(!deadline.equals(now) && deadline.before(now)) {
				list.add("期限は今日以降の日付を選択してください。");
			}
		}
		
		return list;
	}

}
