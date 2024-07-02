<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>
<%@page import="com.jimenez.app.libreria.models.DTO.*" %>

<%
List<RentaDTO> rentasDTO =  (List<RentaDTO>) request.getAttribute("rentasDTO");
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
                <a href="<%=request.getContextPath()%>/renta/alta"
                    class="btn btn-success">Rentar Un Libro</a>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Cliente</th>
                                <th>Titulo</th>
                                <th>Responsable De Renta</th>
                                <th>Fecha de Renta</th>
                                <th>Fecha de Entrega</th>
                                <th>Estado</th>
                                <th>Precio de Renta</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(RentaDTO renta : rentasDTO){ %>
                                <tr>
                                    <td><%=renta.getRentaId()%></td>
                                    <td><%=renta.getNombreUsuario()%></td>
                                    <td><%=renta.getTitulo()%></td>
                                    <td><%=renta.getNombreResponsable()%></td>
                                    <td><%=renta.getFechaRenta()%></td>
                                    <td><%=renta.getFechaDevolucion()%></td>
                                    <td><%=renta.getEstado()%></td>
                                    <td><%=renta.getPrecio()%></td>
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