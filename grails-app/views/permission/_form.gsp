<%@ page import="com.alworoud.Permission" %>



<div class="fieldcontain ${hasErrors(bean: permissionInstance, field: 'acl', 'error')} required">
	<label for="acl">
		<g:message code="permission.acl.label" default="Acl" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="acl" required="" value="${permissionInstance?.acl}"/>
</div>

