<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="deim.urv.cat.homework2.model.Game" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Carrito</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../resources/css/stylesheet.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/layout/menu.jsp" />


        <h1 class="center-title">Perfil de usuario</h1>

        <hr>
        <br>

        <div class="centrar-div">
            <p style="font-size: 30px; font-weight: bold;">&iexcl;Hola ${user.nombre}!</p>

                <p>Aqu&iacute; tienes tus alquileres:</p>

                <c:forEach var="alquiler" items="${rentals}">
                    <p>Alquiler ${alquiler.id} (Fecha de devoluci&oacute;n ${alquiler.fechaRetorno}):</p>
                    <c:forEach var="videojuego" items="${alquiler.videojuegos}">
                        <p>${videojuego.titulo}</p>
                    </c:forEach>
                </c:forEach>

                <form id="confirmForm" action="${mvc.uri('cerrarSesion')}" method="post">
                    <button style="background-color: #F3444F;" class="button">Cerrar sesi&oacute;n</button>
                </form>

        </div>
        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />

    </body>
</html>
