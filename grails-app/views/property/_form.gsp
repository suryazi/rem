<%@ page import="com.alworoud.Property" %>



<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'propNum', 'error')} required">
	<label for="propNum">
		<g:message code="property.propNum.label" default="Prop Num" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="propNum" required="" value="${propertyInstance?.propNum}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'propType', 'error')} required">
	<label for="propType">
		<g:message code="property.propType.label" default="Prop Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="propType" from="${propertyInstance.constraints.propType.inList}" required="" value="${propertyInstance?.propType}" valueMessagePrefix="property.propType"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'street', 'error')} ">
	<label for="street">
		<g:message code="property.street.label" default="Street" />
		
	</label>
	<g:textField name="street" value="${propertyInstance?.street}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'area', 'error')} ">
	<label for="area">
		<g:message code="property.area.label" default="Area" />
		
	</label>
	<g:textField name="area" value="${propertyInstance?.area}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="property.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${propertyInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="property.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${propertyInstance.constraints.status.inList}" required="" value="${propertyInstance?.status}" valueMessagePrefix="property.status"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'remarks', 'error')} ">
	<label for="remarks">
		<g:message code="property.remarks.label" default="Remarks" />
		
	</label>
	<g:textField name="remarks" value="${propertyInstance?.remarks}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'units', 'error')} ">
	<label for="units">
		<g:message code="property.units.label" default="Units" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${propertyInstance?.units?}" var="u">
    <li><g:link controller="unit" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="unit" action="create" params="['property.id': propertyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'unit.label', default: 'Unit')])}</g:link>
</li>
</ul>

</div>

