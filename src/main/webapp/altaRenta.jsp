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
                <h2>Iniciar Ruta</h2>
            </div>
            <!-- <div style="display: block;"><input type="text" name="" id="txtEsOD"></div> -->
        </div>

        <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Chofer</label>
                        <select name="chofer" id="chofer" class="form-control">
                            <option value="" >SELECCIONAR</option>
                            <% for(Libro c: libros){ %>
                                <option value="<%=c.getId()%>"><%=c.getTitulo()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label for="origen">Origen</label>
                                <input type="text" name="origen" id="origen" class="form-control">
                                <input type="hidden" name="idOrigen" id="idOrigen" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="direcciones">Direcciones Frecuentes</label>
                        <select name="direcciones" id="direcciones" class="form-control" onchange="actualizarOrigen()">
                            <option value="" data-direccion="">SELECCIONAR</option>
                            <% for (Responsable c : responsables) { %>
                                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="">Fecha Salida</label>
                        <input type="datetime-local" name="FSalida" id="FSalida" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="">Distancia</label>
                        <input type="text" name="distancia" id="distancia" class="form-control">
                    </div>

                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Camion</label>

                        <select name="camion" id="camion" class="form-control">
                            <option value="">SELECCIONAR</option>
                            <% for(Usuario c: usuarios){ %>
                                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <% } %>
                        </select>

                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label for="destino">Destino</label>
                                <input type="text" name="destino" id="destino" class="form-control">
                                <input type="hidden" name="idDestino" id="idDestino" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="">Fecha Estima de LLegada</label>
                        <input type="datetime-local" name="FELlegada" id="FELlegada" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="">Capacidad Camion</label>
                        <input type="text" name="capCamion" id="capCamion" class="form-control">
                    </div>

                </div>
            </div>
        </div>
    </div>
        <script>
          function handleResponse(response) {
          for (var i = 0; i < response.items.length; i++) {
            var item = response.items[i];
            // in production code, item.text should have the HTML entities escaped.
            document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title;
          }
        }
        </script>
        <script src="https://www.googleapis.com/books/v1/volumes?q=harry+potter&callback=handleResponse"></script>
    </body>
</html>