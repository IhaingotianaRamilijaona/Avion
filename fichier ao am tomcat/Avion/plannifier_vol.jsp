<%@page import="connection.*"%>
<%@page import="information.*"%>
<%@page import="materiel.*"%>
<%@page import="utils.*"%>
<% Avion[] avions = (Avion[]) request.getAttribute("avions"); %>
<% Lieu[] lieux = (Lieu[]) request.getAttribute("lieux"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Form </h1>
    <form action="plannifier_vol" method="post">
        <select name="avion">
            <% for(int i=0;i<avions.length;i++)  { %>
                <option value=<% avion.get_idAvion() %>><% out.println( avions[i].get_typeCourier()) %></option>
            <% } %>
        </select><br><br>
        <select name="lieu_arrive">
            <% for(int i=0;i<lieux.length;i++)  { %>
                <option value=<% lieu.get_idLieu()%>><% out.println(lieux[i].get_nomLieu()) %></option>
            <% } %>
        </select><br><br>
        <select name="lieu_depart">
            <% for(int i=0;i<lieux.length;i++)  { %>
                <option value=<% lieu.get_idLieu()%>><% out.println(lieux[i].get_nomLieu()) %></option>
            <% } %>
        </select><br><br>
        <input type="datetime" name="Date_heure"><br><br>
        <input type="submit" value="Valider">
    </form>
</body>
</html>