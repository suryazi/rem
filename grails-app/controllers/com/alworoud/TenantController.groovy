package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class TenantController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    static grids ={
        tenantJQGrid {
            dataSourceType 'gorm'
            domainClass Tenant
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Tenant'
                pdf {
                    'border.color' java.awt.Color.BLUE
                }
            }
            columns {
                id {
                    filterClosure {filter ->
                        def val=filter.params.id,op
                        if (val.length() > 1){
                            op=filter.params.id[0]
                            if (op == '='){
                                val=filter.params.id[1..-1]
                                eq('id',"${val}".toLong())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.id[0..1]
                                    if  (op == '>='){
                                        val=filter.params.id[2..-1]
                                        ge('id',"${val}".toLong())
                                    }else{
                                        val=filter.params.id[1..-1]
                                        gt('id',"${val}".toLong())
                                    }
                                }else{
                                    val=filter.params.id[1..-1]
                                    gt('id',"${val}".toLong())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.id[0..1]
                                    if (op == '<='){
                                        val=filter.params.id[2..-1]
                                        le('id',"${val}".toLong())
                                    }else{
                                        val=filter.params.id[1..-1]
                                        lt('id',"${val}".toLong())
                                    }
                                }else{
                                    val=filter.params.id[1..-1]
                                    lt('id',"${val}".toLong())
                                }
                            }
                        }
                    }
                    jqgrid{
                        editable false
                        //this will create a link to the show page
                        formatter 'linkShowId'
                    }
                }
                idNum {
                    jqgrid {
                        editable false
                    }
                }
                name {
                    jqgrid {
                        editable false
                    }
                }
                company {
                    jqgrid {
                        editable false
                    }
                }
                mobNum {
                    jqgrid {
                        editable false
                    }
                }
                email {
                    jqgrid {
                        editable false
                    }
                }
                version {
                    type 'version'
                }
            }
        }
        tenantJQGridSelection {
            dataSourceType 'gorm'
            domainClass Tenant
            gridImpl 'jqgrid'
            inlineEdit false
            jqgrid {
                width '"900"'
            }
            columns {
                id {
                    filterClosure {filter ->
                        def val=filter.params.id,op
                        if (val.length() > 1){
                            op=filter.params.id[0]
                            if (op == '='){
                                val=filter.params.id[1..-1]
                                eq('id',"${val}".toLong())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.id[0..1]
                                    if  (op == '>='){
                                        val=filter.params.id[2..-1]
                                        ge('id',"${val}".toLong())
                                    }else{
                                        val=filter.params.id[1..-1]
                                        gt('id',"${val}".toLong())
                                    }
                                }else{
                                    val=filter.params.id[1..-1]
                                    gt('id',"${val}".toLong())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.id[0..1]
                                    if (op == '<='){
                                        val=filter.params.id[2..-1]
                                        le('id',"${val}".toLong())
                                    }else{
                                        val=filter.params.id[1..-1]
                                        lt('id',"${val}".toLong())
                                    }
                                }else{
                                    val=filter.params.id[1..-1]
                                    lt('id',"${val}".toLong())
                                }
                            }
                        }
                    }
                    jqgrid{
                        editable false
                    }
                }
                idNum {
                    jqgrid {
                        editable false
                    }
                }
                name {
                    jqgrid {
                        editable false
                    }
                }
                company {
                    jqgrid {
                        editable false
                    }
                }
                mobNum {
                    jqgrid {
                        editable false
                    }
                }
                email {
                    jqgrid {
                        editable false
                    }
                }
            }
            autocomplete {
                idProp 'id'
//                labelProp 'name'
                labelValue { val, params ->
                    "${val.idNum} (${val.name} - ${val.mobNum})"
                }
                textBoxFilterClosure { filter ->
                    ilike('idNum', "%${filter.paramValue}%")
                }
                constraintsFilterClosure { filter ->
                    
                }
            }
        }
    }
    
    def grid(){}

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tenantInstanceList: Tenant.list(params), tenantInstanceTotal: Tenant.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[tenantInstance: new Tenant(params)]
			break
		case 'POST':
	        def tenantInstance = new Tenant(params)
	        if (!tenantInstance.save(flush: true)) {
	            render view: 'create', model: [tenantInstance: tenantInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'tenant.label', default: 'Tenant'), tenantInstance.id])
	        redirect action: 'show', id: tenantInstance.id
			break
		}
    }

    def show() {
        def tenantInstance = Tenant.get(params.id)
        if (!tenantInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect action: 'list'
            return
        }

        [tenantInstance: tenantInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def tenantInstance = Tenant.get(params.id)
	        if (!tenantInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [tenantInstance: tenantInstance]
			break
		case 'POST':
	        def tenantInstance = Tenant.get(params.id)
	        if (!tenantInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (tenantInstance.version > version) {
	                tenantInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'tenant.label', default: 'Tenant')] as Object[],
	                          "Another user has updated this Tenant while you were editing")
	                render view: 'edit', model: [tenantInstance: tenantInstance]
	                return
	            }
	        }

	        tenantInstance.properties = params

	        if (!tenantInstance.save(flush: true)) {
	            render view: 'edit', model: [tenantInstance: tenantInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'tenant.label', default: 'Tenant'), tenantInstance.id])
	        redirect action: 'show', id: tenantInstance.id
			break
		}
    }

    def delete() {
        def tenantInstance = Tenant.get(params.id)
        if (!tenantInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect action: 'list'
            return
        }

        try {
            tenantInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
}
