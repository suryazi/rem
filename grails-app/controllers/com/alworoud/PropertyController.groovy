package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class PropertyController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
    
    static grids ={
        propertyJQGrid {
            dataSourceType 'gorm'
            domainClass Property
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Property'
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
                propNum {
                    jqgrid {
                        editable false
                    }
                }
                propType {
                    jqgrid {
                        editable false
                    }
                }
                area {
                    jqgrid {
                        editable false
                    }
                }
                city {
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
        
        propertyJQGridSelection {
            dataSourceType 'gorm'
            domainClass Property
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
                propNum {
                    jqgrid {
                        editable false
                    }
                }
                propType {
                    jqgrid {
                        editable false
                    }
                }
                area {
                    jqgrid {
                        editable false
                    }
                }
                city {
                    jqgrid {
                        editable false
                    }
                }
            }
            autocomplete {
                idProp 'id'
//                labelProp 'name'
                labelValue { val, params ->
                    "${val.propNum} (${val.area} ${val.city})"
                }
                textBoxFilterClosure { filter ->
                    ilike('propNum', "%${filter.paramValue}%")
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
        [propertyInstanceList: Property.list(params), propertyInstanceTotal: Property.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[propertyInstance: new Property(params)]
			break
		case 'POST':
	        def propertyInstance = new Property(params)
	        if (!propertyInstance.save(flush: true)) {
	            render view: 'create', model: [propertyInstance: propertyInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'property.label', default: 'Property'), propertyInstance.id])
	        redirect action: 'show', id: propertyInstance.id
			break
		}
    }

    def show() {
        def propertyInstance = Property.get(params.id)
        if (!propertyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'property.label', default: 'Property'), params.id])
            redirect action: 'list'
            return
        }

        [propertyInstance: propertyInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def propertyInstance = Property.get(params.id)
	        if (!propertyInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'property.label', default: 'Property'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [propertyInstance: propertyInstance]
			break
		case 'POST':
	        def propertyInstance = Property.get(params.id)
	        if (!propertyInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'property.label', default: 'Property'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (propertyInstance.version > version) {
	                propertyInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'property.label', default: 'Property')] as Object[],
	                          "Another user has updated this Property while you were editing")
	                render view: 'edit', model: [propertyInstance: propertyInstance]
	                return
	            }
	        }

	        propertyInstance.properties = params

	        if (!propertyInstance.save(flush: true)) {
	            render view: 'edit', model: [propertyInstance: propertyInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'property.label', default: 'Property'), propertyInstance.id])
	        redirect action: 'show', id: propertyInstance.id
			break
		}
    }

    def delete() {
        def propertyInstance = Property.get(params.id)
        if (!propertyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'property.label', default: 'Property'), params.id])
            redirect action: 'list'
            return
        }

        try {
            propertyInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'property.label', default: 'Property'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'property.label', default: 'Property'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
}
