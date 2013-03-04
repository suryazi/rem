package com.alworoud

import org.joda.time.LocalDate

class Rent {
    Unit unit
    LocalDate stDt
    LocalDate dueDt
    BigDecimal rentAmt
    BigDecimal wtrCh
    BigDecimal mntnCh
    BigDecimal totCh
    LocalDate dateCreated
    LocalDate lastUpdated
    static constraints = {
        unit(blank:false)
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
