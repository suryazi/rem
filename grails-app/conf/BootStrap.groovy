import com.alworoud.Role
import com.alworoud.User
import org.apache.shiro.crypto.hash.sha512Hash

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(name: "Administrator")
        adminRole.addToPermissions("*:*")
        adminRole.save()
        
        def userRole = new Role(name: "User")
        userRole.addToPersmissions("Owner:*")
        userRole.save()
        
        def admin = new User(username: "Admin", passwordHash: new sha512Hash("password").toHex())
        admin.addToRoles(adminRole)
        admin.save()
        
        def user = new User(username: "User", passwordHash: new sha512Hash("pass").toHex())
        user.addToRoles(userRole)
        user.save()
    }
    def destroy = {
    }
}
