package com.alworoud

class Role {
    String name

    static hasMany = [ users: User, permissions: Permission ]
    static belongsTo = User

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
    
    String toString(){
        "${name}"
    }
}
