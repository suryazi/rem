package com.alworoud

class Unit {
    String unitId
    String unitType
    String desc
    String status
    String remarks
    Date dateCreated
    Date lastUpdated
    static constraints = {
        unitId(blank:false)
        unitType(blank:false,inList:(['Villa','Apartment','Shop','Space','Land','Building']))
        desc(nullable: true)
        status(blank:false,inList:(['Vacant','Rent','Lease','Maintenance','Sale','Sold']))
        remarks(nullable:true)
    }
    static belongsTo = [prop : Property]
    
    String toString(){
        "${unitId} (${unitType})"
    }
}