package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class PermissionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
    
    static grids ={
        permissionJQGrid {
            dataSourceType 'gorm'
            domainClass Permission
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Permission'
                pdf {
                    'border.color' java.awt.Color.BLUE
                }
            }
            columns {
                actions {
                    type 'actions'
                }
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
                acl {
                    jqgrid {
                        editable true
                    }
                }
                version {
                    type 'version'
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
        [permissionInstanceList: Permission.list(params), permissionInstanceTotal: Permission.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[permissionInstance: new Permission(params)]
			break
		case 'POST':
	        def permissionInstance = new Permission(params)
	        if (!permissionInstance.save(flush: true)) {
	            render view: 'create', model: [permissionInstance: permissionInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'permission.label', default: 'Permission'), permissionInstance.id])
	        redirect action: 'show', id: permissionInstance.id
			break
		}
    }

    def show() {
        def permissionInstance = Permission.get(params.id)
        if (!permissionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
            redirect action: 'list'
            return
        }

        [permissionInstance: permissionInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def permissionInstance = Permission.get(params.id)
	        if (!permissionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [permissionInstance: permissionInstance]
			break
		case 'POST':
	        def permissionInstance = Permission.get(params.id)
	        if (!permissionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (permissionInstance.version > version) {
	                permissionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'permission.label', default: 'Permission')] as Object[],
	                          "Another user has updated this Permission while you were editing")
	                render view: 'edit', model: [permissionInstance: permissionInstance]
	                return
	            }
	        }

	        permissionInstance.properties = params

	        if (!permissionInstance.save(flush: true)) {
	            render view: 'edit', model: [permissionInstance: permissionInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'permission.label', default: 'Permission'), permissionInstance.id])
	        redirect action: 'show', id: permissionInstance.id
			break
		}
    }

    def delete() {
        def permissionInstance = Permission.get(params.id)
        if (!permissionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
            redirect action: 'list'
            return
        }

        try {
            permissionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
}
