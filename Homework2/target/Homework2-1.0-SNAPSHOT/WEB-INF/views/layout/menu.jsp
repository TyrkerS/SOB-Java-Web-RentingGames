<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<header>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/stylesheet.css">

    <div id="nav">
        <menu>
            <ul>
                <li><a href="store">Videojuegos</a></li>
                <li><a href="cart">Carrito</a></li>
                <li>
                    <c:if test="${null == user}">
                        <a style="color: white; background-color:#F3444F;"class="button" href="SignUp">Login</a>
                    </c:if>
                    <c:if test="${null != user}">
                        <a href="profile">Bienvenido/a, ${user.nombre}</a>
                    </c:if>

                </li>
            </ul>
        </menu>
        
        <hr class="hr-deco">
    </div>
</header>