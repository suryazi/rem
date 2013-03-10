/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alworoud

/**
 *
 * @author falbalwi
 */
class DateParser {
    def static te = { str -> Date.parse( "yyyy-M-d", str ) }
}