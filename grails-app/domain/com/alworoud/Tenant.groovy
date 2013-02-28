package com.alworoud

class Tenant {
    Long idNum
    String name
    Long mobNum
    String email
    Date dateCreated
    Date lastUpdated
    static constraints = {
        idNum(unique:true, blank:false)
        name(blank:false)
        mobNum(blank:false)
        email(email:true, nullable:false)
    }
    String toString(){
        "${name} (${idNum})"
    }
}
