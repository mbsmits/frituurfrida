<%@page
	contentType='text/html'
	pageEncoding='UTF-8'
	session='false'
%>
<%@taglib
	prefix='c'
	uri='http://java.sun.com/jsp/jstl/core'
%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param
		name='title'
		value='Frituur Frida'
	/>
</c:import>
</head>
<body>
	<h1>Frituur Frida!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!</h1>
	<h2>Vandaag zij we ${openGesloten}.</h2>
	<img
		src="images/${openGesloten}.png"
		alt="${openGesloten}"
	>
	<h2>${adres.straat}${adres.huisNr} ${adres.gemeente.postcode}
		${adres.gemeente.naam}</h2>
	<div>
		Helpdesk:
		<a href="tel:+${telefoonnummerHelpdesk.replace(' ','')}">${telefoonnummerHelpdesk}</a>
	</div>
</body>
</html>
