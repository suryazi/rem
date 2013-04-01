package com.alworoud

import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.SecurityUtils
import com.alworoud.Role
import com.alworoud.User
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.RandomNumberGenerator
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class SignupController {
    
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
    
    def bcryptService
    def simpleCaptchaService

    static grids ={
        signupJQGrid {
            dataSourceType 'gorm'
            domainClass User
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'User'
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
                username {
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
        [signupInstanceList: User.list(params), signupInstanceTotal: User.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[signupInstance: new User(params)]
			break
		case 'POST':
                    
                def signupInstance = new User(params)
                def user = User.findByUsername(params.username)
                if (user) {
                    flash.message = "User already exists with the username '${params.username}'"
                    render view: 'create', model: [signupInstance: signupInstance]
                    return
                }

                // User doesn't exist with username. Let's create one
                else {

                    // Make sure the passwords match
                    if (params.password != params.confirmPassword) {
                        flash.message = "Passwords do not match"
                        render view: 'create', model: [signupInstance: signupInstance]
                        return
                    } else if (params.password.length()<6){
                        flash.message = "Password less than 6 characters"
                        render view: 'create', model: [signupInstance: signupInstance]
                        return
                    } else if (!simpleCaptchaService.validateCaptcha(params.captcha)){
                        flash.message = "Enter correct captcha"
                        render view: 'create', model: [signupInstance: signupInstance]
                        return
                    }

                    // Passwords match. Let's attempt to save the user
                    else {
                        // Create user
                        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
                        signupInstance = new User(username:params.username,passwordHash: new Sha512Hash(params.password+params.username,passwordSalt,16384).toBase64(),passwordSalt:passwordSalt,passwordBcrypt:bcryptService.hashPassword(params.username+params.password))

                        if (signupInstance.save()) {

                            // Add USER role to new user
                            def userRole =  Role.findByName('User')
                            signupInstance.addToRoles(userRole)
                            signupInstance.save()

                            // Login user
                            //def authToken = new UsernamePasswordToken(signupInstance.username,params.password+params.username)
                            //This is used to login with newly created credentials.
                            //SecurityUtils.subject.login(authToken)
                            
                            //This will provide the newly created username
                            //SecurityUtils.subject?.principal

                            flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), signupInstance.id])
                            redirect action: 'show', id: signupInstance.id
                                    break
                        }
                        else {
                            render view: 'create', model: [signupInstance: signupInstance]
                            return
                        }
                    }
                }
	}
    }

    def show() {
        def signupInstance = User.get(params.id)
        if (!signupInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }

        [signupInstance: signupInstance]
    }
    
    def display() {
        def signupInstance = User.findByUsername(SecurityUtils.subject?.principal)
        if (!signupInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }
        [signupInstance: signupInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def signupInstance = User.get(params.id)
	        if (!signupInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [signupInstance: signupInstance]
			break
		case 'POST':
	        def signupInstance = User.get(params.id)
	        if (!signupInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (signupInstance.version > version) {
	                signupInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'user.label', default: 'User')] as Object[],
	                          "Another user has updated this User while you were editing")
	                render view: 'edit', model: [signupInstance: signupInstance]
	                return
	            }
	        }
                
                if (params.password != params.confirmPassword) {
                        flash.message = "Passwords do not match"
                        render view: 'edit', model: [signupInstance: signupInstance]
                        return
                    } else if (params.password.length()<6){
                        flash.message = "Password less than 6 characters"
                        render view: 'edit', model: [signupInstance: signupInstance]
                        return
                    } else if (!simpleCaptchaService.validateCaptcha(params.captcha)){
                        flash.message = "Enter correct captcha"
                        render view: 'edit', model: [signupInstance: signupInstance]
                        return
                    }

                    // Passwords match. Let's attempt to save the user
                    else {
                        // Create user
                        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
                        signupInstance.passwordHash = new Sha512Hash(params.password+signupInstance.username,passwordSalt,16384).toBase64()
                        signupInstance.passwordSalt = passwordSalt

                        if (signupInstance.save()) {

                            // Add USER role to new user
                            def userRole =  Role.findByName('User')
                            signupInstance.addToRoles(userRole)
                            signupInstance.save()

                            // Login user
                            //def authToken = new UsernamePasswordToken(signupInstance.username,params.password+params.username)
                            //This is used to login with newly created credentials.
                            //SecurityUtils.subject.login(authToken)
                            
                            //This will provide the newly created username
                            //SecurityUtils.subject?.principal

                            flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), signupInstance.id])
                            redirect action: 'show', id: signupInstance.id
                                    break
                        } else {
                            render view: 'edit', model: [signupInstance: signupInstance]
                            return
                        }
		}
                }
    }
    
    def reset() {
		switch (request.method) {
		case 'GET':
	        def signupInstance = User.findByUsername(SecurityUtils.subject?.principal)
	        if (!signupInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [signupInstance: signupInstance]
			break
		case 'POST':
	        def signupInstance = User.findByUsername(SecurityUtils.subject?.principal)
	        if (!signupInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (signupInstance.version > version) {
	                signupInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'user.label', default: 'User')] as Object[],
	                          "Another user has updated this User while you were editing")
	                render view: 'edit', model: [signupInstance: signupInstance]
	                return
	            }
	        }
                
                if (params.password != params.confirmPassword) {
                        flash.message = "Passwords do not match"
                        render view: 'edit', model: [signupInstance: signupInstance]
                        return
                    } else if (params.password.length()<6){
                        flash.message = "Password less than 6 characters"
                        render view: 'edit', model: [signupInstance: signupInstance]
                        return
                    } else if (!simpleCaptchaService.validateCaptcha(params.captcha)){
                        flash.message = "Enter correct captcha"
                        render view: 'edit', model: [signupInstance: signupInstance]
                        return
                    }

                    // Passwords match. Let's attempt to save the user
                    else {
                        // Create user
                        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
                        signupInstance.passwordHash = new Sha512Hash(params.password+signupInstance.username,passwordSalt,16384).toBase64()
                        signupInstance.passwordSalt = passwordSalt

                        if (signupInstance.save()) {


                            // Login user
                            //def authToken = new UsernamePasswordToken(signupInstance.username,params.password+params.username)
                            //This is used to login with newly created credentials.
                            //SecurityUtils.subject.login(authToken)
                            
                            //This will provide the newly created username
                            //SecurityUtils.subject?.principal

                            flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), signupInstance.id])
                            redirect action: 'display', id: signupInstance.id
                                    break
                        } else {
                            flash.message = message('Update failed', args: [message(code: 'user.label', default: 'User'), signupInstance.id])
                            redirect action: 'display', id: signupInstance.id
                            return
                        }
		}
                }
    }

    def delete() {
        def signupInstance = User.get(params.id)
        if (!signupInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }

        try {
            signupInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}