<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
//Recuperamos la lista de choferes que seteamos en el request desde serverlet
List<Autor> autores =  (List<Autor>) request.getAttribute("autores");
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

<div class="container body-content">
    <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>
     <div class="row">
         <div class="col-md-12">
             <h2>Iniciar Ruta</h2>
         </div>
         <!-- <div style="display: block;"><input type="text" name="" id="txtEsOD"></div> -->

     </div>

     <div class="row">
         <div class="col-md-6">
            <div class="form-group">
                <label for="">AUTORES</label>
                <div class="col-md-3">
                    <button class="btn btn-primary btn-xs" style="margin-top: 30px;" onclick="registrarAutor()">Registrar Nuevo Autor</button>
                </div>
                <select name="autor" id="autor" class="form-control">
                    <option value="" >SELECCIONAR AUTOR</option>
                    <% for(Autor c: autores){ %>
                        <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <div class="form-group">
                    <label for="">NOMBRE DEL LIBRO</label>
                    <input type="text" name="titulo" id="titulo" class="form-control">
                </div>
            </div>
         </div>
        
        <div class="col-md-6">
            <div class="form-group">
                <label for="">CATEGORIA </label>

                <select name="categoria" id="categoria" class="form-control">
                    <option value="" >SELECCIONAR CATEGORIA</option>
                    <% for(Categoria c: categorias){ %>
                        <option value="<%=c.getId()%>"><%=c.getCategoria()%></option>
                    <% } %>
                </select>
            </div> 
            
            <div class="form-group">
                <div class="form-group">
                    <label for="anoPublicacion">Año de Publicacion</label>
                    <input class="form-control" type="number" id="year" name="year" min="1900" max="2100" step="1">
                </div>


                <div class="form-group">
                    <button class="btn btn-success">Registrar Libro</button>
                </div>
            </div>
        </div>
     </div>

     <div class="modal fade" id="myModal" role="dialog">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Guardar Direccion</h4>
                        </div>
                    </div>
                </div>
                <div class="contenedor" id="cargando">
                            <div class="cargando">
                                <div class="pelotas"></div>
                                <div class="pelotas"></div>
                                <div class="pelotas"></div>
                                <span class="texto-cargando">Guardando...</span>
                            </div>
                        </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="Nombre">Nombre</label>
                                    <input type="text" name="Nombre" id="Nombre" class="form-control" required>
                                </div>
                        
                                <div class="form-group">
                                    <label for="apPaterno">Apellido Paterno</label>
                                    <input type="text" name="apPaterno" id="apPaterno" class="form-control" required>
                                </div>
                        
                                <div class="form-group">
                                    <label for="apMaterno">Apellido Materno</label>
                                    <input type="text" name="apMaterno" id="apMaterno" class="form-control" required>
                                </div>
                        
                                <div class="form-group">
                                    <label for="Descripcion">Descripción</label>
                                    <input type="text" name="Descripcion" id="Descripcion" class="form-control" required>
                                </div>
                            </div>
                        </div>
                    </div>
                        

                <div class="modal-footer">
                    <div class="row">
                        <div class="col-md-10 col-md-offset-1">
                            <div class="col-md-4">
                                <button class="btn btn-success" id="btnGuardar" onclick="btnGuardarDir()">Guardar</button>
                            </div>

                            <div class="col-md-4 col-md-offset-2">
                                <button class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

     <script>
        function registrarAutor() {
            $('#myModal').modal('show'); 
        }

        function btnGuardarDir() {
            var nombre = $('#Nombre').val();
            var apPaterno = $('#apPaterno').val();
            var apMaterno = $('#apMaterno').val();
            var descripcion = $('#Descripcion').val();
        }
     </script>
</div>
</body>
</html>
