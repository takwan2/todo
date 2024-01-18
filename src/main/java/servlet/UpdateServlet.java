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

import model.dao.UpdateDAO;
import model.dao.dto.TodoDTO;
import utils.InputValidationUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update-servlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータからtodoIdを取得する
        int todoId = Integer.parseInt(request.getParameter("id"));

        UpdateDAO dao = new UpdateDAO();
        TodoDTO todo = new TodoDTO();
        try {
            // todoの取得
            todo = dao.getTodo(todoId);
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }

        request.setAttribute("todo", todo);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // リクエストパラメータから値を取得する
	    request.setCharacterEncoding("UTF-8");
	    int id = Integer.parseInt(request.getParameter("id"));
	    String todo = request.getParameter("todo");
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
	    	UpdateDAO dao = new UpdateDAO();
	        TodoDTO todoDto = new TodoDTO();
	        try {
	            todoDto = dao.getTodo(id);
	        } catch (SQLException | ClassNotFoundException e ) {
	            e.printStackTrace();
	        }
	        request.setAttribute("todo", todoDto);
	        request.setAttribute("errorMessages", errorMessages);
	        request.getRequestDispatcher("update.jsp").forward(request, response);
		    return;
	    } else {  	
	    	// DAOを生成し、Todoを更新する
	    	UpdateDAO dao = new UpdateDAO();
	    	try {
	    		Date timeLimit = Date.valueOf(request.getParameter("timeLimit"));
	    		// 受け取ったパラメータを元にデータベースを更新する
	    		dao.updateTodo(id, todo, timeLimit);
	    	} catch(SQLException | ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
	    	response.sendRedirect("list-servlet");
	    }
	    
	}

}
