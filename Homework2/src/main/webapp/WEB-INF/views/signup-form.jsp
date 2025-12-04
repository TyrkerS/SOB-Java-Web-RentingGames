<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous"/>
    
    <link rel="stylesheet" href="../resources/css/stylesheet.css">
</head>
<body>
    
    <jsp:include page="/WEB-INF/views/layout/menu.jsp" />
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<div class="panel panel-info">
                                <div class="panel-heading" style="text-align: center;">
                                    <div class="panel-title" style="font-size: 24px; font-weight: bold;">Login</div>
                                </div>  

				<div class="panel-body">
					<form action="${mvc.uri('sign-up')}" class="form-horizontal" method="POST">
                                                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
						<div class="form-group">
							<label for="nombre" class="col-md-3 control-label">Usuario</label>
							<div class="col-md-9">
                                                            <input type="text" name="nombre" value="${user.nombre}" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="clave" class="col-md-3 control-label">Clave</label>
							<div class="col-md-9">
                                                            <input type="text" name="clave" value="${user.clave}" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<input type="submit" value="Submit" />
							</div>
						</div>
					</form>
                                        <c:if test="${not empty message}">
                                            <div class="alert alert-danger" role="alert">
                                                ${message}        
                                            </div>
                                        </c:if>

                                        <jsp:include page="/WEB-INF/views/layout/alert.jsp" />
                                </div>
			</div>
		</div>
	</div>
        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>