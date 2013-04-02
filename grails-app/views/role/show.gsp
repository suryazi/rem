
<%@ page import="com.alworoud.Role" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
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
				
					<g:if test="${roleInstance?.name}">
						<dt><g:message code="role.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${roleInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${roleInstance?.permissions}">
						<dt><g:message code="role.permissions.label" default="Permissions" /></dt>
						
							<dd><g:fieldValue bean="${roleInstance}" field="permissions"/></dd>
						
					</g:if>
				
					<g:if test="${roleInstance?.users}">
						<dt><g:message code="role.users.label" default="Users" /></dt>
						
							<g:each in="${roleInstance.users}" var="u">
							<dd><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${roleInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${roleInstance?.id}">
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
