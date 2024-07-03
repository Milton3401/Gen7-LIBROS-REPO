<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
//Recuperamos la lista de choferes que seteamos en el request desde serverlet
List<Libro> libros =  (List<Libro>) request.getAttribute("libros");
List<Responsable> responsables =  (List<Responsable>) request.getAttribute("responsables");
List<Usuario> usuarios =  (List<Usuario>) request.getAttribute("usuarios");
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

 <style>
        .contenedor {
            display: none;
            justify-content: center;
            align-items: center;
            height: 100px;
        }
        .cargando {
            display: flex;
            align-items: center;
        }
        .pelotas {
            width: 10px;
            height: 10px;
            margin: 5px;
            background-color: #000;
            border-radius: 50%;
            animation: bounce 0.5s infinite alternate;
        }
        .pelotas:nth-child(2) {
            animation-delay: 0.2s;
        }
        .pelotas:nth-child(3) {
            animation-delay: 0.4s;
        }
        @keyframes bounce {
            to {
                transform: translateX(-20px);
            }
        }
        .texto-cargando {
            margin-left: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
    <div class="container body-content">
       <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>
        <div class="row">
            <div class="col-md-12">
                <h2>Rentar UN Libro </h2>
            </div>
            <!-- <div style="display: block;"><input type="text" name="" id="txtEsOD"></div> -->
        </div>

        <div class="row">
            <form action="<%=request.getContextPath()%>/rentas/alta" method="post">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Titulo</label>
                        <select name="titulo" id="titulo" class="form-control">
                            <option value="" >SELECCIONAR</option>
                            <% for(Libro c: libros){ %>
                                <option value="<%=c.getId()%>"><%=c.getTitulo()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="precio">Precio</label>
                                <input type="precio" name="precio" id="precio" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="responsable">Trabajador en turno</label>
                        <select name="responsable" id="responsable" class="form-control" onchange="actualizarOrigen()">
                            <option value="" data-direccion="">SELECCIONAR</option>
                            <% for (Responsable c : responsables) { %>
                                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="fechaRenta">Fecha De Renta</label>
                        <input type="date" name="fechaRenta" id="fechaRenta" class="form-control">
                    </div>


                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Usuario</label>

                        <select name="usuario" id="usuario" class="form-control">
                            <option value="">SELECCIONAR</option>
                            <% for(Usuario c: usuarios){ %>
                                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <% } %>
                        </select>

                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="destino">Estado(Disponible)</label>
                                <input type="text" name="disponible" id="disponible" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="">Fecha De Entrega</label>
                        <input type="date" name="fechaEntrega" id="fechaEntrega" class="form-control">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </form>
        <script>
          function handleResponse(response) {
          for (var i = 0; i < response.items.length; i++) {
            var item = response.items[i];
            // in production code, item.text should have the HTML entities escaped.
            document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title;
          }
        }

            $(document).ready(function() {
                var today = new Date();
                var dd = String(today.getDate()).padStart(2, '0');
                var mm = String(today.getMonth() + 1).padStart(2, '0');
                var yyyy = today.getFullYear();

                var todayFormatted = yyyy + '-' + mm + '-' + dd;

                $('#fechaRenta').val(todayFormatted);
            });

        </script>
        <script src="https://www.googleapis.com/books/v1/volumes?q=harry+potter&callback=handleResponse"></script>
    </body>
</html>