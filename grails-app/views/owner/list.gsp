
<%@ page import="com.alworoud.Owner" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'owner.label', default: 'Owner')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<g:render template="/templates/rem/sidemenu" model="[active: 'list']"/>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="name" title="${message(code: 'owner.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="idNum" title="${message(code: 'owner.idNum.label', default: 'Id Num')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'owner.dateCreated.label', default: 'Date Created')}" />
						
							<g:sortableColumn property="lastUpdated" title="${message(code: 'owner.lastUpdated.label', default: 'Last Updated')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${ownerInstanceList}" var="ownerInstance">
						<tr>
						
							<td>${fieldValue(bean: ownerInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: ownerInstance, field: "idNum")}</td>
						
							<td><g:formatDate date="${ownerInstance.dateCreated}" /></td>
						
							<td><g:formatDate date="${ownerInstance.lastUpdated}" /></td>
						
							<td class="link">
								<g:link action="show" id="${ownerInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${ownerInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
