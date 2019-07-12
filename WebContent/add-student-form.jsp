<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student fill form</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
		 <div id ="wrapper">
		
			<div id="header">
			
				<h2>ITESO</h2>
			
			</div>
		
		</div>
		
		<div>
		
		 	<form action="StudentControllerServlet" method="GET">
			 	<input type="hidden" name="command" value="ADD">
			 <table>
			 
			 	<tbody>
			 	
			 	<tr>
			 		
			 		<td>Name</td>
			 		<td><input type="text" name="name"></td>
			 		
			 	</tr>

				<tr>
			 		<td>Last Name</td>
			 		<td> <input type="text" name="lastName"></td>
			 	</tr>
			 	
			 	<tr>
			 		<td>Email</td>
			 		<td> <input type="text" name="email"></td>
			 	</tr>		
			 	
			 	<tr>
			 		
			 		<td> <input type="submit" value="Save"></td>
			 	</tr>	 	
			 	</tbody>
			 
			 </table>
			 		
			
			 
			 
			  
			</form>
		
		
			<p>
					<a href="StudentControllerServlet">Back to List</a>			
			</p>
		</div>
	
</body>
</html>