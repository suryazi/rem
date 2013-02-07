package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class UnitController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
    
    static grids ={
        unitJQGrid {
            dataSourceType 'gorm'
            domainClass Unit
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Unit'
                pdf {
                    'border.color' java.awt.Color.BLUE
                }
            }
            columns {
                id {
                    filterClosure {params ->
                        def val=params.id,op
                        if (val.length() > 2){
                            op=params.id[1]
                            if (op == '='){
                                val=params.id[2..-1]
                                op=params.id[0]
                                if (op == '<'){
                                    le('id',"${val}".toLong())
                                }else if (op == '>'){
                                    ge('id',"${val}".toLong())
                                }
                            }
                        } else if (params.id.length() > 1) {
                            val=params.id[1..-1]
                            op=params.id[0]
                            if (op == '<'){
                                lt('id',"${val}".toLong())
                            }else if (op == '>'){
                                gt('id',"${val}".toLong())
                            }else if (op == '='){
                                eq('id',"${val}".toLong())
                            }
                        }
                    }
                    jqgrid{
                        editable false
                        //this will create a link to the show page
                        formatter 'linkShowId'
                    }
                }
                unitNum {
                    jqgrid {
                        editable false
                    }
                }
                unitType {
                    jqgrid {
                        editable false
                    }
                }
                status {
                    jqgrid {
                        editable false
                    }
                }
                remarks {
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
        [unitInstanceList: Unit.list(params), unitInstanceTotal: Unit.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[unitInstance: new Unit(params)]
			break
		case 'POST':
	        def unitInstance = new Unit(params)
	        if (!unitInstance.save(flush: true)) {
	            render view: 'create', model: [unitInstance: unitInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'unit.label', default: 'Unit'), unitInstance.id])
	        redirect action: 'show', id: unitInstance.id
			break
		}
    }

    def show() {
        def unitInstance = Unit.get(params.id)
        if (!unitInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])
            redirect action: 'list'
            return
        }

        [unitInstance: unitInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def unitInstance = Unit.get(params.id)
	        if (!unitInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [unitInstance: unitInstance]
			break
		case 'POST':
	        def unitInstance = Unit.get(params.id)
	        if (!unitInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (unitInstance.version > version) {
	                unitInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'unit.label', default: 'Unit')] as Object[],
	                          "Another user has updated this Unit while you were editing")
	                render view: 'edit', model: [unitInstance: unitInstance]
	                return
	            }
	        }

	        unitInstance.properties = params

	        if (!unitInstance.save(flush: true)) {
	            render view: 'edit', model: [unitInstance: unitInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'unit.label', default: 'Unit'), unitInstance.id])
	        redirect action: 'show', id: unitInstance.id
			break
		}
    }

    def delete() {
        def unitInstance = Unit.get(params.id)
        if (!unitInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])
            redirect action: 'list'
            return
        }

        try {
            unitInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
}
