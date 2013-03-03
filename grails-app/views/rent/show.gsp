
<%@ page import="com.alworoud.Rent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'rent.label', default: 'Rent')}" />
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
				
					<g:if test="${rentInstance?.unit}">
						<dt><g:message code="rent.unit.label" default="Unit" /></dt>
						
							<dd><g:link controller="unit" action="show" id="${rentInstance?.unit?.id}">${rentInstance?.unit?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.stDt}">
						<dt><g:message code="rent.stDt.label" default="St Dt" /></dt>
						
							<dd><g:formatDate date="${rentInstance?.stDt}" /></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.dueDt}">
						<dt><g:message code="rent.dueDt.label" default="Due Dt" /></dt>
						
							<dd><g:formatDate date="${rentInstance?.dueDt}" /></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.rentAmt}">
						<dt><g:message code="rent.rentAmt.label" default="Rent Amt" /></dt>
						
							<dd><g:fieldValue bean="${rentInstance}" field="rentAmt"/></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.wtrCh}">
						<dt><g:message code="rent.wtrCh.label" default="Wtr Ch" /></dt>
						
							<dd><g:fieldValue bean="${rentInstance}" field="wtrCh"/></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.mntnCh}">
						<dt><g:message code="rent.mntnCh.label" default="Mntn Ch" /></dt>
						
							<dd><g:fieldValue bean="${rentInstance}" field="mntnCh"/></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.totCh}">
						<dt><g:message code="rent.totCh.label" default="Tot Ch" /></dt>
						
							<dd><g:fieldValue bean="${rentInstance}" field="totCh"/></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.dateCreated}">
						<dt><g:message code="rent.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${rentInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${rentInstance?.lastUpdated}">
						<dt><g:message code="rent.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${rentInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${rentInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${rentInstance?.id}">
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
