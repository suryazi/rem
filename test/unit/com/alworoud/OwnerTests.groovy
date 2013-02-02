package com.alworoud



import grails.test.mixin.*
import org.junit.*

import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */

@TestFor(Owner)
@Build(Owner)
class OwnerTests {
    
    void testOwner() {
        Owner owner=Owner.build()
        assertNotNull owner.name
        assertNotNull owner.idNum
    }
}
