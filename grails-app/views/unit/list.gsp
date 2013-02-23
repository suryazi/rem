
<%@ page import="com.alworoud.Unit" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'unit.label', default: 'Unit')}" />
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
						
							<g:sortableColumn property="unitId" title="${message(code: 'unit.unitId.label', default: 'Unit Id')}" />
						
							<g:sortableColumn property="unitType" title="${message(code: 'unit.unitType.label', default: 'Unit Type')}" />
						
							<g:sortableColumn property="desc" title="${message(code: 'unit.desc.label', default: 'Desc')}" />
						
							<g:sortableColumn property="status" title="${message(code: 'unit.status.label', default: 'Status')}" />
						
							<g:sortableColumn property="remarks" title="${message(code: 'unit.remarks.label', default: 'Remarks')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'unit.dateCreated.label', default: 'Date Created')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${unitInstanceList}" var="unitInstance">
						<tr>
						
							<td>${fieldValue(bean: unitInstance, field: "unitId")}</td>
						
							<td>${fieldValue(bean: unitInstance, field: "unitType")}</td>
						
							<td>${fieldValue(bean: unitInstance, field: "desc")}</td>
						
							<td>${fieldValue(bean: unitInstance, field: "status")}</td>
						
							<td>${fieldValue(bean: unitInstance, field: "remarks")}</td>
						
							<td><g:formatDate date="${unitInstance.dateCreated}" /></td>
						
							<td class="link">
								<g:link action="show" id="${unitInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${unitInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
