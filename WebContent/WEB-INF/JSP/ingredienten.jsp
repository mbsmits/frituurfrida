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
		value='Ingredi&euml;nten'
	/>
</c:import>
</head>
<body>
	<vdab:menu />
	<h1>Ingredi&euml;nten</h1>
	<form>
		<label>
			Ingredi&euml;nt:
			<span>${fouten.ingredient}</span>
			<input
				name='ingredient'
				value='${param.ingredient}'
				autofocus
				required
				type='search'
			>
		</label>
		<input
			type='submit'
			value='Zoeken'
		>
	</form>
	<c:if test='${not empty sauzen}'>
		<ul>
			<c:forEach
				var='saus'
				items='${sauzen}'
			>
				<li>${saus.naam}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test='${empty sauzen and empty fouten}'>
		<div>Geen sauzen gevonden</div>
	</c:if>
</body>
</html>