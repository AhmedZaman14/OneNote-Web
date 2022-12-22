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
          String pageTitle = request.getParameter("PageTitle");
          String txt = request.getParameter("text");
          String sectionName = request.getParameter("Sections");
          int user_id = Integer.parseInt(request.getParameter("Users"));
          Notes obj = new NotesProxy().getNotes();
          String result = obj.createANote(pageTitle, txt, sectionName, user_id);
          obj.createANote(pageTitle, txt, sectionName, user_id);
        %>
        <% if (result.startsWith("Plz")) { %>
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
