<%@ page import="com.alworoud.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">

			<g:render template="/templates/rem/sidemenu"/>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${signupInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${signupInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="editRole" id="${signupInstance?.id}"  enctype="multipart/form-data">
						<g:hiddenField name="version" value="${signupInstance?.version}" />
						<fieldset>
                                                        <f:field bean="signupInstance" property="roles" />
                                                        <div class="control-group">
                                                          <div class="controls">
                                                            <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}" class="img-polaroid"/>
                                                          </div>
                                                        </div>
                                                        <div class="control-group">
                                                          <div class="controls">
                                                            <g:textField name="captcha" required="" placeholder="Enter the above text"/>
                                                          </div>
                                                        </div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.update.label" default="Update" />
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
