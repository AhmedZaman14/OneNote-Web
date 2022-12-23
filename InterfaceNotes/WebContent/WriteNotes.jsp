<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OneNote</title>
<head>
  <style>
  
body {
  font-family: 'Roboto', sans-serif;
}

/* Give the heading some color */
h1 {
  color: #2196f3;
  font-size: 36px;
}

/* Make the form inputs larger and give them a border */
form input {
  width: 300px;
  height: 30px;
  border: 1px solid #bdbdbd;
  border-radius: 4px;
  font-size: 18px;
}

/* Add some padding to the form elements */
form label, form input, form select {
  padding: 10px;
}

/* Center the form on the page */
form {
  margin: 0 auto;
  width: 500px;
  box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.2);
}

form button {
  background-color: #2196f3;
  color: white;
  font-size: 18px;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

form button:hover {
  background-color: #1e88e5;
}
    
  </style>
</head>

</head>
<body>

<h1>Take Notes</h1>
<form action="CreateANote.jsp">
	<label>User</label>
	<select name="Users">
			<option value="1">Ahmed Zaman</option>
			<option value="2">Mohammad Nabeel</option>
			<option value="3">Mohammad Zaman</option>
	</select>
	<br>
	<br>
	<label>Section</label>
	<select name="Sections">
			<option value="Education/Work">Education/Work</option>
			<option value="Weekend">Weekend</option>
			<option value="Important">Important</option>
	</select>
	<br>
	<br>
	<label>Note Title</label>
	<input name="PageTitle" type="Text"></input>
	<br>
	<br>
	<label>Write a Note</label>
	<input name="text" type="Text"></input>
	<br>
	<br>
	<button type="submit">Create Note</button>
	
	
</form>
</body>
</html>