<%@page
	contentType='text/html'
	pageEncoding='UTF-8'
	session='false'
%>
<%@taglib
	uri='http://vdab.be/tags'
	prefix='vdab'
%>
<%@taglib
	prefix='c'
	uri='http://java.sun.com/jsp/jstl/core'
%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='head.jsp'>
	<c:param
		name='title'
		value='Meisjes Jongens'
	/>
</c:import>
</head>
<body class='${cookie.meisjesjongens.value}'>
	<vdab:menu />
	<h1>Meisjes-Jongens</h1>
	<form
		method='post'
		id='meisjesjongensform'
		action="<c:url value='/meisjesjongens.htm'/>"
	>
		<input
			type='submit'
			name='meisjesjongens'
			value='meisjes'
		>
		<input
			type='submit'
			name='meisjesjongens'
			value='jongens'
		>
	</form>
</body>
</html>