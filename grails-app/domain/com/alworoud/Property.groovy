package com.alworoud

import org.joda.time.DateTime

class Property {
    static auditable = true
    String propId
    String propType
    String street
    String area
    String city
    String status
    String remarks
    DateTime dateCreated
    DateTime lastUpdated
    static constraints = {
        propId(unique:true, blank:false)
        propType(blank:false, inList:(['Building','Compound','Land']))
        street(nullable:true)
        area(nullable:true)
        city(nullable:true)
        status(blank:false,inList:(['Vacant','Rent','Lease','Maintenance','Sale','Sold']))
        remarks(nullable:true)
    }
    static hasMany = [owners:Owner,units:Unit]
    static belongsTo = Owner
    
    String toString(){
        "${propId} (${area} - ${city})"
    }
}