
<%@ page import="com.alworoud.Owner" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'owner.label', default: 'Owner')}" />
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
				
					<g:if test="${ownerInstance?.name}">
						<dt><g:message code="owner.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${ownerInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${ownerInstance?.idNum}">
						<dt><g:message code="owner.idNum.label" default="Id Num" /></dt>
						
							<dd><g:fieldValue bean="${ownerInstance}" field="idNum"/></dd>
						
					</g:if>
				
					<g:if test="${ownerInstance?.dateCreated}">
						<dt><g:message code="owner.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:fieldValue bean="${ownerInstance}" field="dateCreated"/></dd>
						
					</g:if>
				
					<g:if test="${ownerInstance?.lastUpdated}">
						<dt><g:message code="owner.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:fieldValue bean="${ownerInstance}" field="lastUpdated"/></dd>
						
					</g:if>
				
					<g:if test="${ownerInstance?.prop}">
						<dt><g:message code="owner.prop.label" default="Prop" /></dt>
						
							<g:each in="${ownerInstance.prop}" var="p">
							<dd><g:link controller="property" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${ownerInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${ownerInstance?.id}">
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
