package com.alworoud

class Owner {

    String name
    String idNum
    Date dateCreated
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