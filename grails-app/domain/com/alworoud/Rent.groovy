package com.alworoud

import org.joda.time.Months
import org.joda.time.LocalDate
import org.joda.time.DateTime

class Rent {
    static auditable = true
    Unit unit
    LocalDate stDt
    LocalDate dueDt
    LocalDate hStDt
    LocalDate hDueDt
    int dur=6
    BigDecimal rentAmt
    BigDecimal wtrCh=0
    BigDecimal otCh=0
    //BigDecimal totCh
    String rem
    DateTime dateCreated
    DateTime lastUpdated
    static constraints = {
        unit(unique:true,blank:false)
        stDt(blank:false)
        dueDt(blank:false)
        hStDt(blank:false)
        hDueDt(blank:false)
        dur(blank:false)
        rentAmt(blank:false)
        wtrCh(nullable:true)
        otCh(nullable:true)
        //totCh(blank:false)
        rem(nullable:true)
    }
    /*static mapping = {  
        totCh formula: 'RENT_AMT+WTR_CH+OT_CH'
    }*/
    static belongsTo = [unit:Unit]
}
