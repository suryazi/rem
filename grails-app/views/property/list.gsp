
<%@ page import="com.alworoud.Property" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'property.label', default: 'Property')}" />
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
						
							<g:sortableColumn property="propId" title="${message(code: 'property.propId.label', default: 'Prop Id')}" />
						
							<g:sortableColumn property="propType" title="${message(code: 'property.propType.label', default: 'Prop Type')}" />
						
							<g:sortableColumn property="street" title="${message(code: 'property.street.label', default: 'Street')}" />
						
							<g:sortableColumn property="area" title="${message(code: 'property.area.label', default: 'Area')}" />
						
							<g:sortableColumn property="city" title="${message(code: 'property.city.label', default: 'City')}" />
						
							<g:sortableColumn property="status" title="${message(code: 'property.status.label', default: 'Status')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${propertyInstanceList}" var="propertyInstance">
						<tr>
						
							<td>${fieldValue(bean: propertyInstance, field: "propId")}</td>
						
							<td>${fieldValue(bean: propertyInstance, field: "propType")}</td>
						
							<td>${fieldValue(bean: propertyInstance, field: "street")}</td>
						
							<td>${fieldValue(bean: propertyInstance, field: "area")}</td>
						
							<td>${fieldValue(bean: propertyInstance, field: "city")}</td>
						
							<td>${fieldValue(bean: propertyInstance, field: "status")}</td>
						
							<td class="link">
								<g:link action="show" id="${propertyInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${propertyInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
