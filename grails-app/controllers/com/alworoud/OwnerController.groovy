package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class OwnerController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

static grids ={
        ownerJQGrid {
            dataSourceType 'gorm'
            domainClass Owner
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Owner'
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
                name {
                    jqgrid {
                        editable false
                    }
                }
                idNum {
                    jqgrid {
                        editable false
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
        [ownerInstanceList: Owner.list(params), ownerInstanceTotal: Owner.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[ownerInstance: new Owner(params)]
			break
		case 'POST':
	        def ownerInstance = new Owner(params)
	        if (!ownerInstance.save(flush: true)) {
	            render view: 'create', model: [ownerInstance: ownerInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'owner.label', default: 'Owner'), ownerInstance.id])
	        redirect action: 'show', id: ownerInstance.id
			break
		}
    }

    def show() {
        def ownerInstance = Owner.get(params.id)
        if (!ownerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), params.id])
            redirect action: 'list'
            return
        }

        [ownerInstance: ownerInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def ownerInstance = Owner.get(params.id)
	        if (!ownerInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [ownerInstance: ownerInstance]
			break
		case 'POST':
	        def ownerInstance = Owner.get(params.id)
	        if (!ownerInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (ownerInstance.version > version) {
	                ownerInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'owner.label', default: 'Owner')] as Object[],
	                          "Another user has updated this Owner while you were editing")
	                render view: 'edit', model: [ownerInstance: ownerInstance]
	                return
	            }
	        }

	        ownerInstance.properties = params

	        if (!ownerInstance.save(flush: true)) {
	            render view: 'edit', model: [ownerInstance: ownerInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'owner.label', default: 'Owner'), ownerInstance.id])
	        redirect action: 'show', id: ownerInstance.id
			break
		}
    }

    def delete() {
        def ownerInstance = Owner.get(params.id)
        if (!ownerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), params.id])
            redirect action: 'list'
            return
        }

        try {
            ownerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'owner.label', default: 'Owner'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'owner.label', default: 'Owner'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
}
