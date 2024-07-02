<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>
<%@ include file="header.jsp" %>

    <div class="container">


        <div class="row">
            <div class="col-12">
                <div class="card border">
                    <div class="card-header">
                        <h3><strong>Detalle de Usuario</strong></h3>
                    </div>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Nombre: </strong><%=usuario.getNombre()%></li>
                        <li class="list-group-item"><strong>Apellidos: </strong><%=usuario.getApellido()%></li>
                        <li class="list-group-item"><strong>Email: </strong><%=usuario.getEmail()%></li>
                        <li class="list-group-item"><strong>Telefono: </strong><%=usuario.getTelefono()%></li>
                        <li class="list-group-item"><strong>Direccion: </strong><%=usuario.getDireccion()%></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>