package com.alworoud

import org.joda.time.DateTime

class Owner {
    static auditable = true
    String name
    String idNum
    DateTime dateCreated
    DateTime lastUpdated
    static constraints = {
        name(blank:false)
        idNum(unique:true, blank:false)
    }
    static hasMany = [prop:Property]
    
    String toString(){
        "${name} (${idNum})"
    }
}