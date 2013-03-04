package com.alworoud

import org.joda.time.LocalDate

class Owner {
    String name
    Long idNum
    LocalDate dateCreated
    LocalDate lastUpdated
    static constraints = {
        name(blank:false)
        idNum(unique:true, blank:false)
    }
    static hasMany = [prop:Property]
    
    String toString(){
        "${name} (${idNum})"
    }
}