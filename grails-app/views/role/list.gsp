
<%@ page import="com.alworoud.Role" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
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
						
							<g:sortableColumn property="name" title="${message(code: 'role.name.label', default: 'Name')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${roleInstanceList}" var="roleInstance">
						<tr>
						
							<td>${fieldValue(bean: roleInstance, field: "name")}</td>
						
							<td class="link">
								<g:link action="show" id="${roleInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${roleInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
