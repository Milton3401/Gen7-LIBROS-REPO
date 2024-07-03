<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.libreria.models.*" %>

<%
Libro libro = (Libro) request.getAttribute("libro");
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
            <div class="card border">
                <div class="card-header">
                    <h3><strong>Detalle de Libro</strong></h3>
                </div>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item"><strong>Titulo: </strong><%=libro.getTitulo()%></li>
                    <li class="list-group-item"><strong>Autor Id: </strong><%=libro.getAutorId()%></li>
                    <li class="list-group-item"><strong>Categoria Id: </strong><%=libro.getCategoriaId()%></li>
                    <li class="list-group-item"><strong>Año de Publicacion: </strong><%=libro.getAnoPublicacion()%></li>
                    <li class="list-group-item"><strong>ISBN: </strong><%=libro.getIsbn()%></li>
                    <li class="list-group-item"><strong>Stock: </strong><%=libro.getStock()%></li>
                </ul>
                <h1 class="mt-5">Buscar Libro</h1>
                <div class="form-group">
                    <label for="nombreLibro">Nombre del Libro:</label>
                    <input type="text" class="form-control" id="nombreLibro" name="nombreLibro" value="<%=libro.getTitulo()%>">
                </div>
                <button class="btn btn-primary" onclick="buscarLibro()">Buscar</button>

                <div class="container mt-4">
                    <textarea id="textAreaSnippet" style="margin-top: 20px;" class="form-control" rows="5"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
function buscarLibro() {
    var nombreLibro = document.getElementById('nombreLibro').value;

    var url = 'https://www.googleapis.com/books/v1/volumes?q=' + encodeURIComponent(nombreLibro);
    fetch(url)
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Error en la consulta');
            }
            return response.json();
        })
        .then(function(data) {
            // Llamar a la función handleResponse con los datos recibidos
            handleResponse(data);
        })
        .catch(function(error) {
            console.error('Error en la solicitud:', error);
        });
}

    function handleResponse(data) {
        var foundTextSnippet = false; // Variable para rastrear si se encontró textSnippet

        for (var i = 0; i < data.items.length; i++) {
            var item = data.items[i];

            // Verificar si existe 'searchInfo' y 'textSnippet' dentro de 'item'
            if (item.searchInfo && item.searchInfo.textSnippet) {
                var textSnippet = item.searchInfo.textSnippet;
                console.log('textSnippet:', textSnippet);
                
                // Mostrar textSnippet en un textarea
                document.getElementById("textAreaSnippet").value = textSnippet;
                
                foundTextSnippet = true; // Marcamos que hemos encontrado textSnippet
                break; // Salir del bucle una vez encontrado
            }
        }

        if (!foundTextSnippet) {
            console.log('No se encontró textSnippet en ningún item.');
            // Puedes manejar aquí el caso donde no se encontró textSnippet, si es necesario
        }
    }
</script>
</body>
</html>
