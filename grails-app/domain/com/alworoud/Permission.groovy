package com.alworoud

class Permission {
    
    String acl

    static constraints = {
        acl(nullable: false, blank: false, unique: true)
    }
    
    static hasMany = [ roles: Role ]
    
    static belongsTo = Role
    
    String toString(){
        "${acl}"
    }
}
