<%@ page import="com.alworoud.Unit" %>



<div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'unitNum', 'error')} required">
	<label for="unitNum">
		<g:message code="unit.unitNum.label" default="Unit Num" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="unitNum" required="" value="${unitInstance?.unitNum}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'unitType', 'error')} required">
	<label for="unitType">
		<g:message code="unit.unitType.label" default="Unit Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unitType" from="${unitInstance.constraints.unitType.inList}" required="" value="${unitInstance?.unitType}" valueMessagePrefix="unit.unitType"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'desc', 'error')} ">
	<label for="desc">
		<g:message code="unit.desc.label" default="Desc" />
		
	</label>
	<g:textField name="desc" value="${unitInstance?.desc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="unit.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${unitInstance.constraints.status.inList}" required="" value="${unitInstance?.status}" valueMessagePrefix="unit.status"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'remarks', 'error')} ">
	<label for="remarks">
		<g:message code="unit.remarks.label" default="Remarks" />
		
	</label>
	<g:textField name="remarks" value="${unitInstance?.remarks}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitInstance, field: 'prop', 'error')} required">
	<label for="prop">
		<g:message code="unit.prop.label" default="Prop" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="prop" name="prop.id" from="${com.alworoud.Property.list()}" optionKey="id" required="" value="${unitInstance?.prop?.id}" class="many-to-one"/>
</div>

