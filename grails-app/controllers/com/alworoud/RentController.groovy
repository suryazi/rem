package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException
import com.alworoud.DateParser as d
import org.joda.time.LocalDate

@Easygrid
class RentController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
    
    static grids ={
        rentJQGrid {
            dataSourceType 'gorm'
            domainClass Rent
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Rent'
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
                unit {
                    filterClosure {filter ->
                        def val=filter.params?.unit?.id,op
                        if (val.length() > 1){
                            op=filter.params.unit.id[0]
                            if (op == '='){
                                val=filter.params.unit.id[1..-1]
                                eq('unit.id',"${val}".toLong())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.unit.id[0..1]
                                    if  (op == '>='){
                                        val=filter.params.unit.id[2..-1]
                                        ge('unit.id',"${val}".toLong())
                                    }else{
                                        val=filter.params.unit.id[1..-1]
                                        gt('unit.id',"${val}".toLong())
                                    }
                                }else{
                                    val=filter.params.unit.id[1..-1]
                                    gt('unit.id',"${val}".toLong())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.unit.id[0..1]
                                    if (op == '<='){
                                        val=filter.params.unit.id[2..-1]
                                        le('unit.id',"${val}".toLong())
                                    }else{
                                        val=filter.params.unit.id[1..-1]
                                        lt('unit.id',"${val}".toLong())
                                    }
                                }else{
                                    val=filter.params.unit.id[1..-1]
                                    lt('unit.id',"${val}".toLong())
                                }
                            }
                        }
                    }
                    jqgrid{
                        editable false
                        //this will create a link to the Unit show page
                        formatter 'linkUnitId'
                    }
                }
                stDt {
                    filterClosure {filter ->
                        def val=filter.params.stDt,op
                        if (val.length() > 1){
                            op=filter.params.stDt[0]
                            if (op == '='){
                                val=filter.params.stDt[1..-1]
                                eq('stDt',new LocalDate(val))
                            }
                            else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.stDt[0..1]
                                    if  (op == '>='){
                                        val=filter.params.stDt[2..-1]
                                        ge('stDt',new LocalDate(val))
                                    }else{
                                        val=filter.params.stDt[1..-1]
                                        gt('stDt',new LocalDate(val))
                                    }
                                }else{
                                    val=filter.params.stDt[1..-1]
                                    gt('stDt',new LocalDate(val))
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.stDt[0..1]
                                    if (op == '<='){
                                        val=filter.params.stDt[2..-1]
                                        le('stDt',new LocalDate(val))
                                    }else{
                                        val=filter.params.stDt[1..-1]
                                        lt('stDt',new LocalDate(val))
                                    }
                                }else{
                                    val=filter.params.stDt[1..-1]
                                    lt('stDt',new LocalDate(val))
                                }
                            }
                        }
                    }
                    jqgrid {
                        editable false
                    }
                }
                dueDt {
                    filterClosure {filter ->
                        def val=filter.params.dueDt,op
                        if (val.length() > 1){
                            op=filter.params.dueDt[0]
                            if (op == '='){
                                val=filter.params.dueDt[1..-1]
                                eq('dueDt',new LocalDate(val))
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.dueDt[0..1]
                                    if  (op == '>='){
                                        val=filter.params.dueDt[2..-1]
                                        ge('dueDt',new LocalDate(val))
                                    }else{
                                        val=filter.params.dueDt[1..-1]
                                        gt('dueDt',new LocalDate(val))
                                    }
                                }else{
                                    val=filter.params.dueDt[1..-1]
                                    gt('dueDt',new LocalDate(val))
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.dueDt[0..1]
                                    if (op == '<='){
                                        val=filter.params.dueDt[2..-1]
                                        le('dueDt',new LocalDate(val))
                                    }else{
                                        val=filter.params.dueDt[1..-1]
                                        lt('dueDt',new LocalDate(val))
                                    }
                                }else{
                                    val=filter.params.dueDt[1..-1]
                                    lt('dueDt',new LocalDate(val))
                                }
                            }
                        }
                    }
                    jqgrid {
                        editable false
                    }
                }
                rentAmt {
                    filterClosure {filter ->
                        def val=filter.params.rentAmt,op
                        if (val.length() > 1){
                            op=filter.params.rentAmt[0]
                            if (op == '='){
                                val=filter.params.rentAmt[1..-1]
                                eq('rentAmt',"${val}".toBigDecimal())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.rentAmt[0..1]
                                    if  (op == '>='){
                                        val=filter.params.rentAmt[2..-1]
                                        ge('rentAmt',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.rentAmt[1..-1]
                                        gt('rentAmt',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.rentAmt[1..-1]
                                    gt('rentAmt',"${val}".toBigDecimal())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.rentAmt[0..1]
                                    if (op == '<='){
                                        val=filter.params.rentAmt[2..-1]
                                        le('rentAmt',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.rentAmt[1..-1]
                                        lt('rentAmt',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.rentAmt[1..-1]
                                    lt('rentAmt',"${val}".toBigDecimal())
                                }
                            }
                        }
                    }
                    jqgrid {
                        editable false
                    }
                }
                wtrCh {
                    filterClosure {filter ->
                        def val=filter.params.wtrCh,op
                        if (val.length() > 1){
                            op=filter.params.wtrCh[0]
                            if (op == '='){
                                val=filter.params.wtrCh[1..-1]
                                eq('wtrCh',"${val}".toBigDecimal())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.wtrCh[0..1]
                                    if  (op == '>='){
                                        val=filter.params.wtrCh[2..-1]
                                        ge('wtrCh',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.wtrCh[1..-1]
                                        gt('wtrCh',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.wtrCh[1..-1]
                                    gt('wtrCh',"${val}".toBigDecimal())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.wtrCh[0..1]
                                    if (op == '<='){
                                        val=filter.params.wtrCh[2..-1]
                                        le('wtrCh',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.wtrCh[1..-1]
                                        lt('wtrCh',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.wtrCh[1..-1]
                                    lt('wtrCh',"${val}".toBigDecimal())
                                }
                            }
                        }
                    }
                    jqgrid {
                        editable false
                    }
                }
                otCh {
                    filterClosure {filter ->
                        def val=filter.params.otCh,op
                        if (val.length() > 1){
                            op=filter.params.otCh[0]
                            if (op == '='){
                                val=filter.params.otCh[1..-1]
                                eq('otCh',"${val}".toBigDecimal())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.otCh[0..1]
                                    if  (op == '>='){
                                        val=filter.params.otCh[2..-1]
                                        ge('otCh',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.otCh[1..-1]
                                        gt('otCh',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.otCh[1..-1]
                                    gt('otCh',"${val}".toBigDecimal())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.otCh[0..1]
                                    if (op == '<='){
                                        val=filter.params.otCh[2..-1]
                                        le('otCh',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.otCh[1..-1]
                                        lt('otCh',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.otCh[1..-1]
                                    lt('otCh',"${val}".toBigDecimal())
                                }
                            }
                        }
                    }
                    jqgrid {
                        editable false
                    }
                }
                totCh {
                    filterClosure {filter ->
                        def val=filter.params.totCh,op
                        if (val.length() > 1){
                            op=filter.params.totCh[0]
                            if (op == '='){
                                val=filter.params.totCh[1..-1]
                                eq('totCh',"${val}".toBigDecimal())
                            }else if (op == '>'){
                                if (val.length() > 2){
                                    op=filter.params.totCh[0..1]
                                    if  (op == '>='){
                                        val=filter.params.totCh[2..-1]
                                        ge('totCh',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.totCh[1..-1]
                                        gt('totCh',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.totCh[1..-1]
                                    gt('totCh',"${val}".toBigDecimal())
                                }
                            }else if (op == '<'){
                                if (val.length() > 2){
                                    op=filter.params.totCh[0..1]
                                    if (op == '<='){
                                        val=filter.params.totCh[2..-1]
                                        le('totCh',"${val}".toBigDecimal())
                                    }else{
                                        val=filter.params.totCh[1..-1]
                                        lt('totCh',"${val}".toBigDecimal())
                                    }
                                }else{
                                    val=filter.params.totCh[1..-1]
                                    lt('totCh',"${val}".toBigDecimal())
                                }
                            }
                        }
                    }
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
        [rentInstanceList: Rent.list(params), rentInstanceTotal: Rent.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[rentInstance: new Rent(params)]
			break
		case 'POST':
	        def rentInstance = new Rent(params)
	        if (!rentInstance.save(flush: true)) {
	            render view: 'create', model: [rentInstance: rentInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'rent.label', default: 'Rent'), rentInstance.id])
	        redirect action: 'show', id: rentInstance.id
			break
		}
    }

    def show() {
        def rentInstance = Rent.get(params.id)
        if (!rentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'rent.label', default: 'Rent'), params.id])
            redirect action: 'list'
            return
        }

        [rentInstance: rentInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def rentInstance = Rent.get(params.id)
	        if (!rentInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rent.label', default: 'Rent'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [rentInstance: rentInstance]
			break
		case 'POST':
	        def rentInstance = Rent.get(params.id)
	        if (!rentInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rent.label', default: 'Rent'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (rentInstance.version > version) {
	                rentInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'rent.label', default: 'Rent')] as Object[],
	                          "Another user has updated this Rent while you were editing")
	                render view: 'edit', model: [rentInstance: rentInstance]
	                return
	            }
	        }

	        rentInstance.properties = params

	        if (!rentInstance.save(flush: true)) {
	            render view: 'edit', model: [rentInstance: rentInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'rent.label', default: 'Rent'), rentInstance.id])
	        redirect action: 'show', id: rentInstance.id
			break
		}
    }

    def delete() {
        def rentInstance = Rent.get(params.id)
        if (!rentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'rent.label', default: 'Rent'), params.id])
            redirect action: 'list'
            return
        }

        try {
            rentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'rent.label', default: 'Rent'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rent.label', default: 'Rent'), params.id])
            redirect action: 'show', id: params.id
        }
    }
    
}
