import com.alworoud.Role
import com.alworoud.User
import com.alworoud.Permission
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.RandomNumberGenerator
import org.apache.shiro.crypto.SecureRandomNumberGenerator
/*import org.joda.time.*
import org.joda.time.chrono.* */

class BootStrap {
   
    def shiroSecurityService
    def bcryptService
   
    def init = { servletContext ->
        
        //Testing Gregorian to Hijri date conversion and vice versa
        
        /*Chronology iso = ISOChronology.getInstanceUTC()
        Chronology hijri = IslamicChronology.getInstanceUTC()
        
        LocalDate todayIso = new LocalDate(2013, 4 , 16, iso)
        LocalDate todayHijri =  new LocalDate(todayIso.toDate(),hijri)
        println todayIso
        println todayHijri
        
        // setup date object for midday on May Day 2004 (ISO year 2004)
        DateTime dtISO = new DateTime(2004, 5, 1, 12, 0, 0, 0)

        // find out what the same instant is using the Islamic Chronology
        DateTime dtIslamic = dtISO.withChronology(IslamicChronology.getInstance())
        
        println dtIslamic
        println dtISO*/
   
        if (!User.findByUsername("Admin") ){
            def permissions = new Permission(acl: "*:*")
            permissions.save()

            def adminRole = new Role(name: "Administrator",permissions:permissions)
            adminRole.save()

            /*permissions = new Permission(acl:"Owner:*")
            permissions.save()

            def userRole = new Role(name:"User",permissions:permissions)
            userRole.save()

            permissions = new Permission(acl:"Signup:display,reset")
            permissions.save()

            userRole.addToPermissions(permissions)
            userRole.save()*/

            def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()

            def admin = new User(username:"Admin",passwordHash: new Sha512Hash("passwordAdmin",passwordSalt,16384).toBase64(),passwordSalt:passwordSalt,passwordBcrypt:bcryptService.hashPassword("Adminpassword"))
            admin.addToRoles(adminRole)
            admin.save()

            /*passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
            def user = new User(username:"User",passwordHash: new Sha512Hash("passwordUser",passwordSalt,16384).toBase64(),passwordSalt:passwordSalt,passwordBcrypt:bcryptService.hashPassword("Userpassword"))
            user.addToRoles(userRole)
            user.save()*/
        }
              
    }

   
    def destroy = {
    }
}