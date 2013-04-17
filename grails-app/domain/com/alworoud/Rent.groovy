package com.alworoud

import org.joda.time.LocalDate
import org.joda.time.DateTime

class Rent {
    Unit unit
    LocalDate stDt
    LocalDate dueDt
    BigDecimal rentAmt
    BigDecimal wtrCh=0
    BigDecimal otCh=0
    BigDecimal totCh
    DateTime dateCreated
    DateTime lastUpdated
    static constraints = {
        unit(unique:true,blank:false)
        stDt(blank:false)
        dueDt(blank:false)
        rentAmt(blank:false)
        wtrCh(nullable:true)
        mntnCh(nullable:true)
        totCh(blank:false)
    }
    static mapping = {
        totCh formula: 'RENT_AMT+WTR_CH+MNTN_CH'
    }
    static belongsTo = [unit:Unit]
}
