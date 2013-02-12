<%@ page import="com.alworoud.Owner" %>



<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="owner.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${ownerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'idNum', 'error')} required">
	<label for="idNum">
		<g:message code="owner.idNum.label" default="Id Num" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="idNum" required="" value="${ownerInstance?.idNum}"/>
</div>

