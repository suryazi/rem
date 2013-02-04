<div class="span3">
        <div class="well">
                <ul class="nav nav-list">
                        <li class="nav-header">${entityName}</li>
                          <g:if test="${pageScope?.getProperty('active')=='list'}">
                            <li class="active">
                          </g:if>
                          <g:else>
                            <li>
                          </g:else>
                                    <g:link class="list" action="list">
                                            <i class="icon-list"></i>
                                            <g:message code="default.list.label" args="[entityName]" />
                                    </g:link>
                            </li>
                          <g:if test="${pageScope?.getProperty('active')=='create'}">
                            <li class="active">
                          </g:if>
                          <g:else>
                            <li>
                          </g:else>
                                    <g:link class="create" action="create">
                                            <i class="icon-plus"></i>
                                            <g:message code="default.create.label" args="[entityName]" />
                                    </g:link>
                            </li>
                          <g:if test="${pageScope?.getProperty('active')=='grid'}">
                            <li class="active">
                          </g:if>
                          <g:else>
                            <li>
                          </g:else>
                                    <g:link class="grid" action="grid">
                                            <i class="icon-th"></i>
                                            <g:message code="default.grid.label" args="[entityName]" />
                                    </g:link>
                            </li>
                </ul>
        </div>
</div>