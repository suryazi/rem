package com.alworoud

import org.joda.time.DateTime

class Tenant {
    static auditable = true
    String idNum
    String name
    String company
    String mobNum
    String email
    DateTime dateCreated
    DateTime lastUpdated
    static constraints = {
        idNum(unique:true, blank:false)
        name(blank:false)
        company(nullable:true)
        mobNum(blank:false)
        email(email:true, nullable:false)
    }
    static hasMany = [units:Unit]
    static belongsTo = [units:Unit]
    String toString(){
        "${idNum} (${name})"
    }
}
