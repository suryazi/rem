package com.alworoud

class Rent {
    Date stDate
    Date dueDate
    BigDecimal rentAmt
    BigDecimal wtrCh
    BigDecimal mntnCh
    BigDecimal totCh
    Date dateCreated
    Date lastUpdated
    static constraints = {
        stDate(blank:false)
        dueDate(blank:false)
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
