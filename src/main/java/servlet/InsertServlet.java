package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.InsertDAO;
import utils.InputValidationUtil;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert-servlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    // リクエストパラメータのname属性がtodoの値を受け取る
	    String todo = (String) request.getParameter("todo");
	    // リクエストパラメータのname属性がtimeLimitの値を受け取る
	    String timeLimitString = request.getParameter("timeLimit");

	    List<String> errorMessages = new ArrayList<>();
	    
	    
	    if (InputValidationUtil.isNullOrEmpty(todo)) {
	    	errorMessages.add("Todoは必須項目です。");
	    }

	    if (InputValidationUtil.isNullOrEmpty(timeLimitString)) {
	    	errorMessages.add("期限は必須項目です。");
	    } else if (!InputValidationUtil.isValidDateFormat(timeLimitString)) {
	    	errorMessages.add("期限の入力を見直してください。");
	    }
	    
	    if (!errorMessages.isEmpty()) {
	    	HttpSession session = request.getSession();
		    session.setAttribute("errorMessages", errorMessages);
	    } else {
	    	// DAOを生成し、Todoをデータベースに登録する
	    	InsertDAO insertDao = new InsertDAO();
	    	try {
	    		Date timeLimit = Date.valueOf(timeLimitString);
	    		// 受け取ったパラメータを引数に渡す
	    		insertDao.insertTodo(todo, timeLimit);
	    	}catch(SQLException | ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
	    }
	    
        response.sendRedirect("list-servlet");
	}

}
