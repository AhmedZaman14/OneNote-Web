<%
  String us= request.getParameter("Users");
  String n = request.getParameter("NoteName");
  if (us == null || n == null) {
    response.sendRedirect("DeleteNote.jsp");
    return;
  }
%>
<%@page import="onenote.zaman.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>OneNote</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
  <style>
    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 1rem;
    }
  </style>
</head>
<body>
  <section class="hero is-info is-fullheight">
    <div class="hero-body">
      <div class="container">
        <h1 class="title">OneNote</h1>
       
        <%
          String userIdString = request.getParameter("Users");
          int user_id = Integer.parseInt(userIdString);
          String noteName = request.getParameter("NoteName");
          FileManagement obj = new FileManagementProxy().getFileManagement();
          String result = obj.deleteNote(user_id, noteName);
          obj.deleteNote(user_id, noteName);
        %>
        <% if (!result.equals("Note Deleted")) { %>
          <div class="notification is-danger">
            <%=result%>
          </div>
        <% } else { %>
          <div class="notification is-success">
            <%=result%>
          </div>
        <% } %>
      </div>
    </div>
  </section>
</body>
</html>
