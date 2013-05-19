
<%@ page import="com.alworoud.Rent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'rent.label', default: 'Rent')}" />
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
						
							<th class="header"><g:message code="rent.unit.label" default="Unit" /></th>
						
							<g:sortableColumn property="stDt" title="${message(code: 'rent.stDt.label', default: 'St Dt')}" />
						
							<g:sortableColumn property="dueDt" title="${message(code: 'rent.dueDt.label', default: 'Due Dt')}" />
						
							<g:sortableColumn property="rentAmt" title="${message(code: 'rent.rentAmt.label', default: 'Rent Amt')}" />
						
							<g:sortableColumn property="wtrCh" title="${message(code: 'rent.wtrCh.label', default: 'Wtr Ch')}" />
						
							<g:sortableColumn property="otCh" title="${message(code: 'rent.otCh.label', default: 'Ot Ch')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${rentInstanceList}" var="rentInstance">
						<tr>
						
							<td>${fieldValue(bean: rentInstance, field: "unit")}</td>
						
							<td>${fieldValue(bean: rentInstance, field: "stDt")}</td>
						
							<td>${fieldValue(bean: rentInstance, field: "dueDt")}</td>
						
							<td>${fieldValue(bean: rentInstance, field: "rentAmt")}</td>
						
							<td>${fieldValue(bean: rentInstance, field: "wtrCh")}</td>
						
							<td>${fieldValue(bean: rentInstance, field: "mntnCh")}</td>
						
							<td class="link">
								<g:link action="show" id="${rentInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${rentInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
