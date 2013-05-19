<%@ page import="com.alworoud.Rent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'rent.label', default: 'Rent')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
                <r:require modules="easygrid-selection-dev"/>
                <r:script type="text/javascript">
                    $(document).ready(function()
                    {
                      $("#stdatepicker").datepicker({dateFormat: 'yy-mm-dd'});
                      $("#duedatepicker").datepicker({dateFormat: 'yy-mm-dd'});
                    })
                </r:script>
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

				<g:hasErrors bean="${rentInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${rentInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
                                                        <div class="control-group">
                                                                <label class="control-label" for="unit">Unit</label>
                                                                <div class="controls">
                                                                  <grid:selection title="Select the unit"
                                                                          gridName="unitJQGridSelection" controller="unit"
                                                                          value="${rentInstance?.unit?.id}" id="unit" name="unit.id"
                                                                  />
                                                                </div>
                                                        </div>
							<f:with bean="rentInstance">
                                                          <f:field property="stDt">
                                                            <g:textField name="stDt" id="stdatepicker" value="${rentInstance?.stDt?.toDateTime()?.toDate()}" formatString="${joda.inputPattern(type: org.joda.time.LocalDate)}" required=""/>
                                                          </f:field>
                                                          <f:field property="dueDt">
                                                            <g:textField name="dueDt" id="duedatepicker" value="${rentInstance?.dueDt?.toDateTime()?.toDate()}" formatString="${joda.inputPattern(type: org.joda.time.LocalDate)}" required=""/>
                                                          </f:field>
                                                          <f:field property="rentAmt"/>
                                                          <f:field property="wtrCh"/>
                                                          <f:field property="otCh"/>
                                                        </f:with>
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
