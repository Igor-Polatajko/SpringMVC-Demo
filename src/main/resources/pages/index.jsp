<%@ page import="com.spring.domains.Person" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>Hello from index page!</h1>

<%
    List<Person> people = (List) request.getAttribute("people");

    if (!people.isEmpty()) {
%>
<h2>People in system:</h2>

<%
    for (Person person : people) {
%>
<h3>
    <%=
    person.getId() + " - " + person.getName()
            + " [created date: " + person.getCreatedDate()
            + "; updated date: " + person.getUpdatedDate() + "]"
    %>
</h3>
<%
    }
%>

<%
}
else {
%>
<h2>There are no people in system!</h2>
<%
    }
%>
</body>
</html>