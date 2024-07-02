<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>
<%
Responsable responsable = (Responsable) request.getAttribute("responsable");
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
                <h2>Formulario Editar Informacion Trabajador</h2>
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
            <form action="<%=request.getContextPath()%>/responsables/editar" method="post">
            <input type="hidden" name="id" value="<%=responsable.getId()%>">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="">Nombre</label>
                        <input type="text" name="nombre" id="nombre" class="form-control" value="<%=responsable.getNombre() !=null? responsable.getNombre(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("nombre")){
                                out.println("<span class='text-danger'>"+ errores.get("nombre")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Paterno</label>
                        <input type="text" name="apPaterno" id="apPaterno" class="form-control" value="<%=responsable.getApPaterno() !=null? responsable.getApPaterno(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("apPaterno")){
                                out.println("<span class='text-danger'>"+ errores.get("apPaterno")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Paterno</label>
                        <input type="text" name="apMaterno" id="apMaterno" class="form-control" value="<%=responsable.getApMaterno() !=null? responsable.getApMaterno(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("apMaterno")){
                                out.println("<span class='text-danger'>"+ errores.get("apMaterno")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido Materno</label>
                        <input type="text" name="email" id="email" class="form-control" value="<%=responsable.getEmail() !=null? responsable.getEmail(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("email")){
                                out.println("<span class='text-danger'>"+ errores.get("email")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Telefono</label>
                        <input type="text" name="telefono" id="telefono" class="form-control" value="<%=responsable.getTelefono() !=null? responsable.getTelefono(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("telefono")){
                                out.println("<span class='text-danger'>"+ errores.get("telefono")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Direccion</label>
                        <input type="text" name="direccion" id="direccion" class="form-control" value="<%=responsable.getDireccion() !=null? responsable.getDireccion(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("direccion")){
                                out.println("<span class='text-danger'>"+ errores.get("direccion")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Turno</label>
                        <input type="text" name="turno" id="turno" class="form-control" value="<%=responsable.getTurno() !=null? responsable.getTurno(): ""%>">
                        <%
                            if (errores != null && errores.containsKey("turno")){
                                out.println("<span class='text-danger'>"+ errores.get("turno")+"</span>");
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
