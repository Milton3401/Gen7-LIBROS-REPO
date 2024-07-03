<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>
<%@page import="com.jimenez.app.libreria.models.DTO.*" %>

<%
List<LibroDTO> librosDTO =  (List<LibroDTO>) request.getAttribute("libros");
List<Libro> libros =  (List<Libro>) request.getAttribute("libros2");
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
                <h2>Listado de Libros</h2>
            </div>

            <div class="col-6">
                <a href="<%=request.getContextPath()%>/libros/alta"
                    class="btn btn-success">Alta Libro</a>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Titulo</th>
                                <th>Nombre Del Autor</th>
                                <th>Categoria</th>
                                <th>Año de Publicacion</th>
                                <th>ISBN</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(LibroDTO c : librosDTO){ %>
                                <tr>
                                    <td><%=c.getId()%></td>
                                    <td><%=c.getTitulo()%></td>
                                    <td><%=c.getNombreAutor()%></td>
                                    <td><%=c.getNombreCategoria()%></td>
                                    <td><%=c.getAnio()%></td>
                                    <td><%=c.getISBN()%></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Titulo</th>
                                <th>CategoriaId</th>
                                <th>AutorId</th>
                                <th>Año de Publicacion</th>
                                <th>ISBN</th>
                                <th>Stock</th>
                                <th>Detalle</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Libro libro : libros){ %>
                                <tr>
                                    <td><%=libro.getId()%></td>
                                    <td><%=libro.getTitulo()%></td>
                                    <td><%=libro.getCategoriaId()%></td>
                                    <td><%=libro.getAutorId()%></td>
                                    <td><%=libro.getAnoPublicacion()%></td>
                                    <td><%=libro.getIsbn()%></td>
                                    <td><%=libro.getStock()%></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/libros/detalle?id=<%=libro.getId()%>" class="btn btn-success">Detalle</a>
                                    </td>

                                    <td>
                                        <a href="<%=request.getContextPath()%>/libros/eliminar?id=<%=libro.getId()%>" class="btn btn-danger">Eliminar</a>
                                    </td>
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