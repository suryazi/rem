
<%@ page import="com.alworoud.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
						
							<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${signupInstanceList}" var="signupInstance">
						<tr>
						
							<td>${fieldValue(bean: signupInstance, field: "username")}</td>
						
							<td class="link">
								<g:link action="show" id="${signupInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${signupInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
