<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
	<c:forEach items="${ errors }" var="err">
		<p>${ err }</p>
	</c:forEach>
	<img class="dog-img" src="/images/dax.jpg" alt="dax" />
	<form:form action="/dogs" method="post" modelAttribute="dog" enctype="multipart/form-data">
		<form:input type="hidden" path="owner" value="${ userId }"/>
		<div class="form-group">
			<form:label path="name">Name</form:label>
			<form:errors path="name"/>
			<form:input class="form-control" path="name"/>
		</div>
		<div class="form-group">
			<form:label path="breed">Breed</form:label>
			<form:errors path="breed"/>
			<form:input class="form-control" path="breed"/>
		</div>
		<div class="form-group">
			<form:label path="description">Description</form:label>
			<form:errors path="description"/>
			<form:input class="form-control" path="description"/>
		</div>
		<div class="form-group">
			<form:label path="photo">Photo</form:label>
			<form:errors path="photo"/>
			<input type="file" class="form-control" name="pic"/>
		</div>
		<button>Submit</button>
	</form:form>
	
</t:wrapper>