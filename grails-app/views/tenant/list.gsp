
<%@ page import="com.alworoud.Tenant" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'tenant.label', default: 'Tenant')}" />
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
						
							<g:sortableColumn property="idNum" title="${message(code: 'tenant.idNum.label', default: 'Id Num')}" />
						
							<g:sortableColumn property="name" title="${message(code: 'tenant.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="mobNum" title="${message(code: 'tenant.mobNum.label', default: 'Mob Num')}" />
						
							<g:sortableColumn property="email" title="${message(code: 'tenant.email.label', default: 'Email')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'tenant.dateCreated.label', default: 'Date Created')}" />
						
							<g:sortableColumn property="lastUpdated" title="${message(code: 'tenant.lastUpdated.label', default: 'Last Updated')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${tenantInstanceList}" var="tenantInstance">
						<tr>
						
							<td>${fieldValue(bean: tenantInstance, field: "idNum")}</td>
						
							<td>${fieldValue(bean: tenantInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: tenantInstance, field: "mobNum")}</td>
						
							<td>${fieldValue(bean: tenantInstance, field: "email")}</td>
						
							<td><g:formatDate date="${tenantInstance.dateCreated}" /></td>
						
							<td><g:formatDate date="${tenantInstance.lastUpdated}" /></td>
						
							<td class="link">
								<g:link action="show" id="${tenantInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${tenantInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
