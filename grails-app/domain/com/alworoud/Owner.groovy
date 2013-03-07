package com.alworoud

import org.joda.time.LocalDate

class Owner {
    String name
    String idNum
    LocalDate dateCreated
    Date lastUpdated
    static constraints = {
        name(blank:false)
        idNum(unique:true, blank:false)
    }
    static hasMany = [prop:Property]
    
    String toString(){
        "${name} (${idNum})"
    }
}