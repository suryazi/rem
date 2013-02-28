
<%@ page import="com.alworoud.Tenant" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'tenant.label', default: 'Tenant')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<g:render template="/templates/rem/sidemenu"/>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${tenantInstance?.idNum}">
						<dt><g:message code="tenant.idNum.label" default="Id Num" /></dt>
						
							<dd><g:fieldValue bean="${tenantInstance}" field="idNum"/></dd>
						
					</g:if>
				
					<g:if test="${tenantInstance?.name}">
						<dt><g:message code="tenant.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${tenantInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${tenantInstance?.mobNum}">
						<dt><g:message code="tenant.mobNum.label" default="Mob Num" /></dt>
						
							<dd><g:fieldValue bean="${tenantInstance}" field="mobNum"/></dd>
						
					</g:if>
				
					<g:if test="${tenantInstance?.email}">
						<dt><g:message code="tenant.email.label" default="Email" /></dt>
						
							<dd><g:fieldValue bean="${tenantInstance}" field="email"/></dd>
						
					</g:if>
				
					<g:if test="${tenantInstance?.dateCreated}">
						<dt><g:message code="tenant.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${tenantInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${tenantInstance?.lastUpdated}">
						<dt><g:message code="tenant.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${tenantInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${tenantInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${tenantInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete" onclick="return confirm('Are you sure?')">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
