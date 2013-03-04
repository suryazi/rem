package com.alworoud

import org.joda.time.LocalDate

class Tenant {
    Long idNum
    String name
    Long mobNum
    String email
    LocalDate dateCreated
    LocalDate lastUpdated
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
