<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="deim.urv.cat.homework2.model.Game" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
    <head>
        <title>Detalles del Juego</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../resources/css/stylesheet.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/layout/menu.jsp" />
        <div class="contenedor-columnas">

            <div class="columna-izquierda">
                <img style="width: 50%; height: auto;" src="../resources/img/${videojuego.imagen}"/>
            </div>

            <div class="columna-derecha">
                <h2 class="detail-style" id="detail-bold">${videojuego.titulo}</h2>
                <p class="detail-style" id="detail-normal">Stock: ${videojuego.disponibilidad}</p>

                <h2 class="detail-style" id="detail-bold">${videojuego.precio}&euro; (IVA inclu&iacute;do)</h2>

                <p style="color:gray;" class="detail-style" id="detail-normal">
                    ${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(videojuego.descripcion, 'á', '&aacute;'), 'é', '&eacute;'), 'í', '&iacute;'), 'ó', '&oacute;'), 'ú', '&uacute;')}
                </p>


                <br>
                
                
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>
                
                

                <form id="addCartForm" action="${mvc.uri('addCart')}" method="post">

                    <div id="title-row">
                        
                        <button type="button" class="buttonCantidad" onclick="decrementar()">-</button>
                        <input class="custom-input" id="cantidad" name="cantidad" value="1" min="0" readonly/>
                        <button type="button" class="buttonCantidad" onclick="incrementar()">+</button>
                    </div>

                    <p style="font-size: 17px" class="detail-style" id="detail-normal">Selecciona la tienda:</p>

                    <select class="combos" id="tienda" name="tienda">
                        <c:forEach var="tienda" items="${videojuego.tienda}">
                            <option value="${tienda.id}">
                                ${fn:replace(tienda.direccion, 'ñ', '&ntilde;')}
                            </option>
                        </c:forEach>
                    </select>

                    <input type="hidden" name="juego" id="juego" value="${videojuego.titulo}">
                    <br><br>
                    <button class="button" input="submit">A&ntilde;adir al carrito</button>

                </form>

                <script>

                    function incrementar() {

                        var cantidadStr = document.getElementById("cantidad");
                        var cantidad = parseInt(cantidadStr.value);

                        cantidadStr.value = cantidad + 1;

                    }

                    function decrementar() {
                        var cantidadStr = document.getElementById("cantidad");
                        var cantidad = parseInt(cantidadStr.value);
                        if (cantidad > 0) {
                            cantidadStr.value = cantidad - 1;
                        }
                    }
                </script>

            </div>
        </div>

        <hr>
        <div class="div-padding">
            <p class="detail-style">Tipo de videojuego: ${videojuego.tipo.tipo}</p>
            <p class="detail-style">Consola compatible: ${consola.nombre}</p>

            <p class="detail-style">Juego disponible en:</p>
            <c:forEach var="tienda" items="${videojuego.tienda}">
                <ul>
                    <li>${fn:replace(tienda.direccion, 'ñ', '&ntilde;')}</li>
                </ul>
            </c:forEach>
        </div>

        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />

    </body>
</html>
