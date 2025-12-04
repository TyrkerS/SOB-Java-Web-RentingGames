<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="deim.urv.cat.homework2.model.Game" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Carrito</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../resources/css/stylesheet.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/layout/menu.jsp" />


        <h1 class="center-title">Carrito</h1>
    
        <br>

        <c:choose>
            <c:when test="${not empty carrito}">
                <c:forEach var="elem" items="${carrito}">

                    <div class="contenedor-columnas-mini">
                        <div style="max-height: 100%;" class="columna-izquierda">
                            <img style="height: 200px; width: auto;" src="../resources/img/${elem.videojuego.imagen}"/>
                        </div>
                        
                        <div style="max-height: 100%;" class="columna-derecha">
                            <h2 class="detail-style" id="detail-bold">${elem.videojuego.titulo} x${elem.cantidad}</h2>

                            <h2 class="detail-style" id="detail-bold">${elem.videojuego.precio * elem.cantidad} &euro; (IVA inclu&iacute;do)</h2>
                            <p style="color: gray;" class="detail-style" id="detail-normal">Tienda: ${elem.tienda.direccion}</p>

                        </div>
                    </div>
                </c:forEach>
                
                <hr>
                
                <div class="centrar-div">
                <h2>Total: ${precioTotal} &euro;</h2>
                <h3 style="color: gray;">Fecha de retorno: ${fechaRetorno}</h3>
                <form id="confirmForm" action="${mvc.uri('addAlquiler')}" method="post">
                    <button style="padding: 15px 50px 15px 50px; background-color:#F3444F;" class="button">Realizar el pago</button>
                </form>
               
                </div>
                    
            </c:when>
            <c:otherwise>
                <div class="centrar-div">
                    <p>Tu carrito est&aacute; vac&iacute;o</p>
                </div>
            </c:otherwise>
        </c:choose>

        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />

    </body>
</html>
