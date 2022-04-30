package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		
		List<String> errorList = new ArrayList<>();
		if(task_name == "") {
			errorList.add("タスク名は必須です。");
		}
		
		if(task_name.length() > 30) {
			errorList.add("タスク名は30文字以内で入力してください。");
		}
		
		if(explanation.length() > 200) {
			errorList.add("タスクの詳細は200文字以内で入力してください。");
		}
		
		Date deadline = null;
		if(deadline_str != "") {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				deadline = sdf.parse(deadline_str);
				
				Date now = new Date();
				String now_str = sdf.format(now);
				now = sdf.parse(now_str);
				
				if(!deadline.equals(now) && deadline.before(now)) {
					errorList.add("期限は今日以降の日付を選択してください。");
				}
			}catch(ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(errorList.size() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("errorList", errorList);
			response.sendRedirect("/Todo/createServlet");
		}else {
			Task task = new Task(task_name, explanation, deadline);
		
			CreateTaskLogic createTaskLogic = new CreateTaskLogic();
			createTaskLogic.execute(task);
			
			response.sendRedirect("/Todo/IndexServlet");
		}
		
		
	}

}
