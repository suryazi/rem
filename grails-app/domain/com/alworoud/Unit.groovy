package com.alworoud

import org.joda.time.LocalDate

class Unit {
    String unitId
    String unitType
    String desc
    String status
    String remarks
    Tenant tenant
    LocalDate dateCreated
    Date lastUpdated
    static constraints = {
        unitId(unique: 'prop', blank:false)
        unitType(blank:false,inList:(['Villa','Apartment','Shop','Space','Land','Building']))
        desc(nullable: true)
        status(blank:false,inList:(['Vacant','Rent','Lease','Maintenance','Sale','Sold']))
        remarks(nullable:true)
        tenant(nullable:true)
    }
    static belongsTo = [prop:Property]
    
    String toString(){
        "${unitId} (${unitType} - ${status})"
    }
}