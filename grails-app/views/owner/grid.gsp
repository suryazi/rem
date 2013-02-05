<%@ page import="com.alworoud.Owner" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
                <g:set var="entityName" value="${message(code: 'owner.label', default: 'Owner')}" />
		<title><g:message code="default.grid.label" args="[entityName]" /></title>
                <r:script>
                    function linkShowId(cellvalue, options, rowObject) {
                        return "<a href='/rem/owner/show/" + cellvalue + "'>" + cellvalue + "</a> ";
                    }
                </r:script>
                <r:require modules="easygrid-jqgrid-dev,export"/>
        </head>
	<body>
		<div class="row-fluid">
			<g:render template="/templates/rem/sidemenu" model="[active: 'grid']"/>
                        <div class="span9">
                          
                                <div class="page-header">
					<h1><g:message code="default.grid.label" args="[entityName]" /></h1>
				</div>
				
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                          
                                <grid:grid id='jqgridinitial' name='ownerJQGrid' />
                                <grid:exportButton name='ownerJQGrid'/>
                        </div>
                </div>
	</body>
</html>
