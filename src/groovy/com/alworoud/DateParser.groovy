/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alworoud

import org.joda.time.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

/**
 *
 * @author falbalwi
 */
class DateParser {
    def static te = { str -> Date.parse( "yyyy-M-d", str ) }
    def static frmt ={ val ->
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd")
        DateTime dt = fmt.parseDateTime(val)
    }
}
