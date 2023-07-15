<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table border="1px solid">
        <% for(int i=0;i<vols.length;i++)  { %>
            <tr>
                <td><% out.println(vols[i].get_avion().get_typeCourier()); %></td>
                <td><% out.println(vols[i].get_avion().get_typeCourier()); %></td>
                <td><% out.println(vols[i].get_LieuDepart().get_nomLieu()); %></td>
                <td><% out.println(vols[i].get_LieuArrive().get_nomLieu()); %></td>
                <td><% out.println(vols[i].get_DateHeureDepart()); %></td>
                <td><a href="Valider?idvol="<% out.println(vols[i].get_avion().get_idAvion()); %>><button>Reserver</button></a></td>
            </tr>
        <% } %>
    </table>
</body>
</html>