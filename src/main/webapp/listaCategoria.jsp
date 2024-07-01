<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
List<Categoria> categorias =  (List<Categoria>) request.getAttribute("categorias");
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
            <div class="col-6">
                <h2>Listado de Autores</h2>
            </div>

            <div class="col-6">
                <a href="<%=request.getContextPath()%>/autores/alta"
                    class="btn btn-success">Alta Autor</a>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Ap Paterno</th>
                                <th>Ap. Materno</th>
                                <th>Descripcion</th>
                                <th>Detalle</th>
                                <th>Editar </th>
                                <th>Eliminar Autor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Categoria c: categorias){ %>
                                <tr>
                                    <td><%=c.getId()%></td>
                                    <td><%=c.getCategoria()%></td>
                                </tr>
                                <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


</body>
</html>