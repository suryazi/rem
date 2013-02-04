package com.alworoud



import grails.test.mixin.*
import org.junit.*

import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Unit)
@Build([Unit,Property])
class UnitTests {
    void testUnit() {
        
        Unit unit= Unit.build(prop:Property.build())
        assertNotNull unit.unitType
        assertNotNull unit.prop
        assertNotNull unit.prop.propType
    }
}
