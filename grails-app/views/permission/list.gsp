
<%@ page import="com.alworoud.Permission" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'permission.label', default: 'Permission')}" />
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
						
							<g:sortableColumn property="acl" title="${message(code: 'permission.acl.label', default: 'Acl')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${permissionInstanceList}" var="permissionInstance">
						<tr>
						
							<td>${fieldValue(bean: permissionInstance, field: "acl")}</td>
						
							<td class="link">
								<g:link action="show" id="${permissionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${permissionInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
