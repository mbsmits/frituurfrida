<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='head.jsp'>
	<c:param name='title' value='Statistiek2' />
</c:import>
<style>
td:last-child {
	text-align: right;
}
</style>
</head>
<body>
	<vdab:menu />
	<h1>Statistiek2</h1>
	<c:if test="${not empty statistiek2}">
		<table>
			<thead>
				<tr>
					<th>URL</th>
					<th>aantal requests</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var='entry' items='${statistiek2}'>
					<tr>
						<td>${entry.key}</td>
						<td>${entry.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>