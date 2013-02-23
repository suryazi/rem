<%@ page import="com.alworoud.Unit" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'unit.label', default: 'Unit')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
                <r:require modules="easygrid-selection-dev"/>
	</head>
	<body>
		<div class="row-fluid">
			
			<g:render template="/templates/rem/sidemenu" model="[active: 'create']"/>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.create.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${unitInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${unitInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
                                                        <f:with bean="unitInstance">
                                                          <f:field property="unitId"/>
                                                          <f:field property="unitType"/>
                                                          <f:field property="desc"/>
                                                          <f:field property="status"/>
                                                          <f:field property="remarks"/>
                                                        </f:with>
							<!--<f:all bean="unitInstance"/>
                                                        
                                                        <div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'prop', 'error')} required">
                                                                <label for="prop">
                                                                        <g:message code="unit.prop.label" default="Prop" />
                                                                        <span class="required-indicator">*</span>
                                                                </label>
                                                                <grid:selection id="property" title="Select the property"
                                                                        gridName="propertyJQGridSelection" controller="property"
                                                                        name="property.id" value=""
                                                                />
                                                        </div>-->                                                        
                                                        
                                                        <div class="control-group">
                                                                <label class="control-label" for="prop">Property</label>
                                                                <div class="controls">
                                                                <grid:selection id="property" title="Select the property"
                                                                        gridName="propertyJQGridSelection" controller="property"
                                                                        name="property.id" value=""
                                                                />
                                                                </div>
                                                        </div>
                                                        
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
                                                                <g:link class="list" action="list" class="btn btn-warning">
                                                                        <i class="icon-remove icon-white"></i>
                                                                        <g:message code="default.button.cancel.label" default="Cancel" />
                                                                </g:link>
							</div>
						</fieldset>
					</g:form>
				</fieldset>
				
			</div>

		</div>
	</body>
</html>
