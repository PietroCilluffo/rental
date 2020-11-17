<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home</title>
<style type="text/css">
body {
	text-align: center;
}

table {
	margin-left: 15%;
	min-width: 70%;
	border: 1px solid #CCC;
	border-collapse: collapse;
}

table tr {
	line-height: 30px;
}

table tr th {
	background: #000033;
	color: #FFF;
}

table tr td {
	border: 1px solid #CCC;
	margin: 5px;
}

input[type=text], input[type=email], input[type=tel] {
	min-width: 60%;
}

input[type=submit], a {
	background: green;
	padding: 5px;
	margin: 5px;
	color: #FFF;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<% String superU = null;
 
    
    %>

	<c:if test="${sessionScope.isSuper == 0}">
		<h1>SONO utente</h1>

           <form action="<c:url value="/reservation/add"/>" method="get">
                        
                        <input type="submit" value="Aggiungi prenotazioni">
                    </form>
	
		<h1>Lista delle prenotazioni</h1>

		<table>
			<tr>
				<th>Targa</th>
				<th>Modello</th>
				<th>Data Inizio</th>
				<th>Data fine</th>
				<th>Approvazione</th>

			</tr>

				<c:forEach items="${reservationList}" var="reservation" varStatus = "status">
				<tr>

					<td>${vehicleList[status.index].targa}</td>
					<td>${vehicleList[status.index].modello}</td>
					<td>${reservation.data_inizio}</td>
					<td>${reservation.data_fine}</td>
					<td>${reservation.approvazione}</td>
					
				

				</tr>
			</c:forEach>
		</table>

	</c:if>

	<c:if test="${sessionScope.isSuper == 1}">

		<h1>SONO SUPER USER</h1>


		<a href="addUser.jsp"> Aggiungi utente</a>
		 <form action="<c:url value="/vehicle/add"/>" method="get">
                        
                        <input type="submit" value="Parco Auto">
                    </form>
		<h1>Lista degli utenti</h1>

		<table>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>email</th>

			</tr>

			<c:forEach items="${userList}" var="user">
				<tr>

					<td>${user.id}</td>
					<td>${user.nome}</td>
					<td>${user.cognome}</td>
					<td>${user.email}</td>
				 <td>
                    <form action="<c:url value="/user/update"/>" method="post">
                        <input type="hidden" name="custId" value="${user.id}">
                        <input type="submit" value="gestisci">
                    </form>

				<td>
					<form action="<c:url value="/user/delete"/>" method="post">
						<input type="hidden" name="custId" value="${user.id}"> <input
							style="background: #F00;" type="submit" value="Delete">
					</form>
				</td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
</body>
</body>
</html>