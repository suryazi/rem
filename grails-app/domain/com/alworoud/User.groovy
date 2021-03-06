package com.alworoud

class User {
    String username
    String passwordHash
    byte[] passwordSalt
    
    static hasMany = [ roles: Role, permissions: String ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
    }
    
    String toString(){
        "${username}"
    }
}
