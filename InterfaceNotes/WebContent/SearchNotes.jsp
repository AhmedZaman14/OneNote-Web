<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OneNote</title>
<head>
  <style>
    /* CSS code goes here */
    form {
  width: 500px;
  margin: 0 auto;
  text-align: center;
}

label {
  display: block;
  margin-bottom: 0.5em;
  font-weight: bold;
}

select,
input[type="text"] {
  width: 100%;
  padding: 0.5em;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 0.5em 1em;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
    
  </style>
</head>

</head>
<body>

<form action="Note.jsp">
<h1>Search Notes</h1>
	<br>
	<br>
	<label>User</label>
	<select name="Users">
			<option value="1">Ahmed Zaman</option>
			<option value="2">Mohammad Nabeel</option>
			<option value="3">Mohammad Zaman</option>
	</select>
	<br>
	<br>
	
	<label>Note Title</label>
	<input name="NoteName" type="Text"></input>
	
	<br>
	<br>
	<button type="submit">Search</button>
	
	
</form>

</body>
</html>