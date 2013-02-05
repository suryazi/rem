package com.alworoud

import org.grails.plugin.easygrid.Easygrid
import org.springframework.dao.DataIntegrityViolationException

@Easygrid
class UnitController {

    static grids ={
        unitJQGrid {
            dataSourceType 'gorm'
            domainClass Owner
            gridImpl 'jqgrid'
            inlineEdit true
            jqgrid {
                width '"750"'
            }
            export {
                export_title 'Unit'
                pdf {
                    'border.color' java.awt.Color.BLUE
                }
            }
            columns {
                id {
                    filterClosure {params ->
                        def val=params.id,op
                        if (val.length() > 2){
                            op=params.id[1]
                            if (op == '='){
                                val=params.id[2..-1]
                                op=params.id[0]
                                if (op == '<'){
                                    le('id',"${val}".toLong())
                                }else if (op == '>'){
                                    ge('id',"${val}".toLong())
                                }
                            }
                        } else if (params.id.length() > 1) {
                            val=params.id[1..-1]
                            op=params.id[0]
                            if (op == '<'){
                                lt('id',"${val}".toLong())
                            }else if (op == '>'){
                                gt('id',"${val}".toLong())
                            }else if (op == '='){
                                eq('id',"${val}".toLong())
                            }
                        }
                    }
                    jqgrid{
                        editable false
                        //this will create a link to the show page
                        formatter 'linkShowId'
                    }
                }
                name {
                    jqgrid {
                        editable false
                    }
                }
                idNum {
                    jqgrid {
                        editable false
                    }
                }
                version {
                    type 'version'
                }
            }
        }
    }
    
    def grid(){}
    static scaffold = true
}
