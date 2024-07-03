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
    <div class="row">
         <div class="col-md-12">
             <h2>Registrar Un Nuevo Libro</h2>
         </div>
         <div style="display: block;"><input type="text" name="" id="txtEsOD" hidden></div>

     </div>

    <div class="row">
        <form action="<%=request.getContextPath()%>/libros/alta" method="post">
         <div class="col-md-6">

            <div class="form-group">
                <div class="form-group">
                    <label for="">NOMBRE DEL LIBRO</label>
                    <input type="text" name="titulo" id="titulo" class="form-control" required>
                </div>
            </div>

            <div class="form-group">
                <div class="form-group">
                    <label for="">Stock</label>
                    <input type="number" name="stock" id="stock" class="form-control" required>
                </div>
            </div>

            <div class="form-group">
                <label for="autor">AUTORES</label>
                <select name="autorId" id="autorId" class="form-control">
                    <option value="">SELECCIONAR AUTOR</option>
                    <% for (Autor c : autores) { %>
                        <option value="<%= c.getId() %>"><%= c.getNombre() + " " + c.getApPaterno() + " " + c.getApMaterno() %></option>
                    <% } %>
                </select>
                <button class="btn btn-success" style="margin-top: 30px;" onclick="registrarAutor()">Registar Un Autor Nuevo</button>
            </div>
         </div>

        
        <div class="col-md-6">
            <div class="form-group">
                <label for="categoria">CATEGORÍA</label>
                <select name="categoriaId" id="categoriaId" class="form-control" required>
                    <option value="">SELECCIONAR CATEGORÍA</option>
                    <% for (Categoria c : categorias) { %>
                        <option value="<%= c.getId() %>"><%= c.getCategoria() %></option>
                    <% } %>
                </select>                
            </div> 
            
            <div class="form-group">
                <div class="form-group">
                    <label for="anoPublicacion">Año de Publicacion</label>
                    <input class="form-control" type="number" id="year" min="1900" max="2100" step="1" name="anoPublicacion" id="anoPublicacion" required>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <label for="">Identificador Unico Del Libro (ISBN)</label>
                        <input type="text" name="isbn" id="isbn" class="form-control" >
                    </div>
                    <button class="btn btn-success" style="margin-top: 15px;" onclick="generarISBN13()">Generar ISBN y Guardar</button>
                </div>
            </div>
        </div>
     </div>
    </form>

     <div class="modal fade" id="myModal" role="dialog">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Guardar Autor</h4>
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

        function LimpiarDatos(){
            $("#Nombre").val("");
            $("#apPaterno").val("");
            $("#apMaterno").val("");
            $("#Descripcion").val("");

        }

        function registrarAutor() {
            $('#myModal').modal('show'); 
            $("#txtEsOD").val(fuente);
        }

        function btnGuardarDir(){
            //Recuperamos la direccion
            var nombre = $("#Nombre").val();
            var apPaterno = $("#apPaterno").val();
            var apMaterno = $("#apMaterno").val();
            var descripcion = $("#Descripcion").val();

            //HACER LA PETICION a mi Api, mandandole los valores de las cajas
            $.ajax({
                type:'POST',
                url:"http://localhost:8080/Gen7-ProyectoApi/api/autores",
                data:{ "nombre":nombre, "apPaterno":apPaterno, "apMaterno": apMaterno,"descripcion":descripcion},
                    //espera la respuesta del servidor
                    success:function(resp){
                        //Mosrtar la respuesta
                        console.log(resp);
                        //habiliatar el boton
                        $('#myModal').modal('hide');
                            if($("#txtEsOD").val()==1){
                              $("#idOrigen").val(resp.message);
                          }else{//es destino
                              $("#idDestino").val(resp.message);
                         }
                    }
            });
        }
       
        function generarISBN13() {
            let isbn = '978';
            let suma = 0;
            for (let i = 0; i < 9; i++) {
                const digito = Math.floor(Math.random() * 10);
                isbn += digito;
                suma += digito * (i % 2 === 0 ? 1 : 3);
            }
            let digitoControl = 10 - (suma % 10);
            if (digitoControl === 10) {
                digitoControl = 0;
            }
            isbn += digitoControl;

            // Establecer el valor del ISBN generado en el campo de texto
            document.getElementById('isbn').value = isbn;
        }

        document.getElementById("btnGuardar").addEventListener('click', function () {
                    document.getElementById('cargando').style.display = 'flex';

                    setTimeout(function () {
                        document.getElementById('cargando').style.display = 'none';
                    }, 6000);
                });
     </script>
</div>
</body>
</html>
