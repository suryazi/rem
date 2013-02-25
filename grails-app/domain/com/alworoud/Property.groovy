package com.alworoud

class Property {
    String propId
    String propType
    String street
    String area
    String city
    String status
    String remarks
    Date dateCreated
    Date lastUpdated
    static constraints = {
        propId(unique:true, blank:false)
        propType(blank:false, inList:(['Building','Compound','Land']))
        street(nullable:true)
        area(nullable:true)
        city(nullable:true)
        status(blank:false,inList:(['Vacant','Rent','Lease','Maintenance','Sale','Sold']))
        remarks(nullable:true)
    }
    static hasMany = [units:Unit]
    
    String toString(){
        "${propId} (${area} - ${city})"
    }
}