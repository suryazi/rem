
<%@ page import="com.alworoud.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
				
					<g:if test="${signupInstance?.username}">
						<dt><g:message code="user.username.label" default="Username" /></dt>
						
							<dd><g:fieldValue bean="${signupInstance}" field="username"/></dd>
						
					</g:if>
				
					<g:if test="${signupInstance?.permissions}">
						<dt><g:message code="user.permissions.label" default="Permissions" /></dt>
						
							<dd><g:fieldValue bean="${signupInstance}" field="permissions"/></dd>
						
					</g:if>
				
					<g:if test="${signupInstance?.roles}">
						<dt><g:message code="user.roles.label" default="Roles" /></dt>
						
							<g:each in="${signupInstance.roles}" var="r">
							<dd><g:link controller="role" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${signupInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${signupInstance?.id}">
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
