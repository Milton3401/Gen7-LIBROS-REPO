<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
Responsable responsable = (Responsable) request.getAttribute("responsable");
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
<nav class="navbar navbar-inverse">
   <div class="container-fluid">
       <!-- Brand and toggle get grouped for better mobile display -->
       <div class="navbar-header" id="div1">
           <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
               data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
           </button>
           <a class="navbar-brand" href="#" id="enlace1">Libreria Calabozos Y Dragones App</a>
       </div>

       <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
           <ul class="nav navbar-nav">
               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Autores<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/autores/listar">Lista Autoress</a></li>
                       <li><a href="<%=request.getContextPath()%>/autores/alta">Alta Autores</a></li>
                   </ul>
               </li>

               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Camiones<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/usuarios/listar">Lista Usuarios</a></li>
                       <li><a href="<%=request.getContextPath()%>/usuarios/alta">Lista Usuarios</a></li>
                   </ul>
               </li>

               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Libros Rentados<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/rentas/alta">Alta Ruta</a></li>
                   </ul>
               </li>
           </ul>
       </div><!-- /.navbar-collapse -->
   </div><!-- /.container-fluid -->
</nav>

    <div class="container">


        <div class="row">
            <div class="col-12">
                <div class="card border">
                    <div class="card-header">
                        <h3><strong>Detalles Del Trabajador</strong></h3>
                    </div>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Nombre: </strong><%=responsable.getNombre()%></li>
                        <li class="list-group-item"><strong>Ap. Paterno: </strong><%=responsable.getApPaterno()%></li>
                        <li class="list-group-item"><strong>Ap. Materno: </strong><%=responsable.getApMaterno()%></li>
                        <li class="list-group-item"><strong>Email: </strong><%=responsable.getEmail()%></li>
                        <li class="list-group-item"><strong>Telefono : </strong><%=responsable.getTelefono()%></li>
                        <li class="list-group-item"><strong>Direccion: </strong><%=responsable.getDireccion()%></li>
                        <li class="list-group-item"><strong>Turno: </strong><%=responsable.getTurno()%></li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>