import com.alworoud.Role
import com.alworoud.User
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.RandomNumberGenerator
import org.apache.shiro.crypto.SecureRandomNumberGenerator

class BootStrap {
   
    def shiroSecurityService
    def bcryptService
   
    def init = { servletContext ->
   
        def adminRole = new Role(name: "Administrator")
        adminRole.addToPermissions("*:*")
        adminRole.save()
       
        def userRole = new Role(name:"User")
        userRole.addToPermissions("Owner:*")
        userRole.save()
        
        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
       
        def admin = new User(username:"Admin",passwordHash: new Sha512Hash("passwordAdmin",passwordSalt,16384).toBase64(),passwordSalt:passwordSalt,passwordBcrypt:bcryptService.hashPassword("Adminpassword"))
        admin.addToRoles(adminRole)
        admin.save()
       
        passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
        def user = new User(username:"User",passwordHash: new Sha512Hash("passwordUser",passwordSalt,16384).toBase64(),passwordSalt:passwordSalt,passwordBcrypt:bcryptService.hashPassword("Userpassword"))
        user.addToRoles(userRole)
        user.save()
              
    }

   
    def destroy = {
    }
}