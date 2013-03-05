
<%@ page import="com.alworoud.Property" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'property.label', default: 'Property')}" />
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
				
					<g:if test="${propertyInstance?.propId}">
						<dt><g:message code="property.propId.label" default="Prop Id" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="propId"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.propType}">
						<dt><g:message code="property.propType.label" default="Prop Type" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="propType"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.street}">
						<dt><g:message code="property.street.label" default="Street" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="street"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.area}">
						<dt><g:message code="property.area.label" default="Area" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="area"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.city}">
						<dt><g:message code="property.city.label" default="City" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="city"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.status}">
						<dt><g:message code="property.status.label" default="Status" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="status"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.remarks}">
						<dt><g:message code="property.remarks.label" default="Remarks" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="remarks"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.dateCreated}">
						<dt><g:message code="property.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="dateCreated"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.lastUpdated}">
						<dt><g:message code="property.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:fieldValue bean="${propertyInstance}" field="lastUpdated"/></dd>
						
					</g:if>
				
					<g:if test="${propertyInstance?.owners}">
						<dt><g:message code="property.owners.label" default="Owners" /></dt>
						
							<g:each in="${propertyInstance.owners}" var="o">
							<dd><g:link controller="owner" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${propertyInstance?.units}">
						<dt><g:message code="property.units.label" default="Units" /></dt>
						
							<g:each in="${propertyInstance.units}" var="u">
							<dd><g:link controller="unit" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${propertyInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${propertyInstance?.id}">
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
