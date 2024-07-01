<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>

<%
Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
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
                <h2>Formulario Alta Autor</h2>
            </div>
        </div>

        <br>
        <% if(errores != null && errores.size()>0){ %>
            <ul class="alert alert-danger mx-5 px-5">
                <% for(String error: errores.values()){ %>
                <li><%=error%></li>
            <% } %>
            </ul>
        <% } %>

        <div class="row">
            <form action="<%=request.getContextPath()%>/autores/alta" method="post">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="">Nombre</label>
                        <input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}">
                        <%
                            if (errores != null && errores.containsKey("nombre")){
                                out.println("<span class='text-danger'>"+ errores.get("nombre")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Paterno</label>
                        <input type="text" name="apPaterno" id="apPaterno" class="form-control" value="${param.apPaterno}">
                        <%
                            if (errores != null && errores.containsKey("apPaterno")){
                                out.println("<span class='text-danger'>"+ errores.get("apPaterno")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Materno</label>
                        <input type="text" name="apMaterno" id="ApMaterno" class="form-control" value="${param.apMaterno}">
                        <%
                            if (errores != null && errores.containsKey("apMaterno")){
                                out.println("<span class='text-danger'>"+ errores.get("apMaterno")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Licencia</label>
                        <input type="text" name="descripcion" id="descripcion" class="form-control" value="${param.descripcion}">
                        <%
                            if (errores != null && errores.containsKey("descripcion")){
                                out.println("<span class='text-danger'>"+ errores.get("descripcion")+"</span>");
                            }
                        %>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>