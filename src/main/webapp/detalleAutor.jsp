<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
Autor autor = (Autor) request.getAttribute("autor");
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
                        <h3><strong>Detalle de Chofer</strong></h3>
                    </div>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Nombre: </strong><%=autor.getNombre()%></li>
                        <li class="list-group-item"><strong>Ap. Paterno: </strong><%=autor.getApPaterno()%></li>
                        <li class="list-group-item"><strong>Ap. Materno: </strong><%=autor.getApMaterno()%></li>
                        <li class="list-group-item"><strong>Licencia: </strong><%=autor.getDescripcion()%></li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>