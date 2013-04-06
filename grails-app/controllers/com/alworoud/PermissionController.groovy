package com.alworoud

import org.springframework.dao.DataIntegrityViolationException

class PermissionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

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
