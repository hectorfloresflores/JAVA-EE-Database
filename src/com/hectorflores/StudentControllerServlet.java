package com.hectorflores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
  
    public StudentControllerServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			//read the command parameter
			String theCommand = request.getParameter("command");
			
			//If the command is missing list the student
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			switch(theCommand) {
			
			case "LIST":
				//list students in MVC fashion
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
			case "DELETE":
				deleteStudent(request,response);
				break;
			default:
				listStudents(request, response);
			}
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}

	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		studentDbUtil.deleteStudent(request.getParameter("studentId"));
		listStudents(request, response);
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Student me = new Student(request.getParameter("name"), 
				request.getParameter("lastName"),request.getParameter("email"));
		me.setId(Integer.parseInt(request.getParameter("id")));
		studentDbUtil.updateStudent(me);
		
		listStudents(request, response);
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("studentId");
		
		
		
		Student thestudent = studentDbUtil.getStudent(id);
		
		request.setAttribute("THE_STUDENT", thestudent);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 //read student info form data
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		//create a new student object
		Student me = new Student(name, lastName, email);
		//add the studemt to the database
		studentDbUtil.addStudent(me); 
		
		//send back to main
		listStudents(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		// get students from dbutil
		List<Student> students = studentDbUtil.getStudents();
		
		//add students to the request
		request.setAttribute("STUDENT_LIST", students);
		
		//send to jsp page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}

}
