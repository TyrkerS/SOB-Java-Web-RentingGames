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


        <h1 class="center-title">El ID de su recibo es: ${numPedido}</h1>
        <h1 class="center-title">El ID de su recibo es: ${fecha}</h1>

        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />

    </body>
</html>
