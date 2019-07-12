<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<form action="StudentControllerServlet" method="GET">
			 	<input type="hidden" name="command" value="UPDATE">
			 	 <input type="hidden" name="id"
			 				value="${THE_STUDENT.id}">
			 <table>
			
			 	<tbody>
			 	
			 	<tr>
			 		
			 		<td>Name</td>
			 		<td><input type="text" name="name"
			 				value="${THE_STUDENT.name}"></td>
			 		
			 	</tr>

				<tr>
			 		<td>Last Name</td>
			 		<td> <input type="text" name="lastName"
			 				value="${THE_STUDENT.lastName}"></td>
			 	</tr>
			 	
			 	<tr>
			 		<td>Email</td>
			 		<td> <input type="text" name="email"
			 				value="${THE_STUDENT.email}"></td>
			 	</tr>		
			 	
			 	<tr>
			 		
			 		<td> <input type="submit" value="Save"></td>
			 	</tr>	 	
			 	</tbody>
			 
			 </table>
			 		
			
			 
			 
			  
	</form>

</body>
</html>