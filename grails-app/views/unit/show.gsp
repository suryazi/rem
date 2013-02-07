
<%@ page import="com.alworoud.Unit" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'unit.label', default: 'Unit')}" />
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
				
					<g:if test="${unitInstance?.unitNum}">
						<dt><g:message code="unit.unitNum.label" default="Unit Num" /></dt>
						
							<dd><g:fieldValue bean="${unitInstance}" field="unitNum"/></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.unitType}">
						<dt><g:message code="unit.unitType.label" default="Unit Type" /></dt>
						
							<dd><g:fieldValue bean="${unitInstance}" field="unitType"/></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.desc}">
						<dt><g:message code="unit.desc.label" default="Desc" /></dt>
						
							<dd><g:fieldValue bean="${unitInstance}" field="desc"/></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.status}">
						<dt><g:message code="unit.status.label" default="Status" /></dt>
						
							<dd><g:fieldValue bean="${unitInstance}" field="status"/></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.remarks}">
						<dt><g:message code="unit.remarks.label" default="Remarks" /></dt>
						
							<dd><g:fieldValue bean="${unitInstance}" field="remarks"/></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.dateCreated}">
						<dt><g:message code="unit.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${unitInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.lastUpdated}">
						<dt><g:message code="unit.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${unitInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${unitInstance?.prop}">
						<dt><g:message code="unit.prop.label" default="Prop" /></dt>
						
							<dd><g:link controller="property" action="show" id="${unitInstance?.prop?.id}">${unitInstance?.prop?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${unitInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${unitInstance?.id}">
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
