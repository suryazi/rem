package com.alworoud



import grails.test.mixin.*
import org.junit.*

import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Property)
@Build(Property)
class PropertyTests {
    
    void testProperty() {
        Property property=Property.build()
        assertNotNull property.propId
    }
}
