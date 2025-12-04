<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="deim.urv.cat.homework2.model.Game" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Juegos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../resources/css/stylesheet.css">

    </head>
    <body>

        <jsp:include page="/WEB-INF/views/layout/menu.jsp" />


        <div class="busqueda">
            <form id="filter" action="${mvc.uri('filter')}" method="post">
                <input type="text" id="busqueda" name="busqueda" placeholder="Buscar por videoconsola o por tipo"/>
                <select class="combos" id="filtro" name="filtro">
                    <option value="0">Tipo</option>
                    <option value="1">Consola</option>
                </select>
                <button type="submit"><i class="fa fa-search"></i>Buscar</button>
            </form>
        </div>

        <br>

        <c:forEach var="game" items="${videojuegos}">

            <a href="details?titulo=${game.titulo}">
                <div id="game-container">

                    <div style="border-radius: 20px;" id="img-container">
                        <img style="width: 75%; height: auto" src="../resources/img/${game.imagen}" alt="Imagen del videojuego" class="imagen-juego"/>
                    </div>

                    <div id="title-row">
                        <h2 class="detail-style" id="detail-bold">${game.titulo}</h2>
                        <h2 style="color: #9DCC9B;" class="detail-style" id="detail-bold">${game.precio} &euro;</h2>
                    </div>

                    <p class="detail-style" id="detail-normal">Tipo de juego: ${game.tipo.tipo}</p>
                    <p class="detail-style" id="detail-normal">Stock: ${game.disponibilidad}</p>
                    <p class="detail-style" id="detail-normal">Consola compatible: ${game.videoconsola.nombre}</p>
 
                </div>
            </a>

        </c:forEach>

        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />

    </body>
</html>