package com.alworoud



import org.junit.*
import grails.test.mixin.*

@TestFor(RentController)
@Mock(Rent)
class RentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rentInstanceList.size() == 0
        assert model.rentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rentInstance != null
        assert view == '/rent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rent/show/1'
        assert controller.flash.message != null
        assert Rent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rent/list'

        populateValidParams(params)
        def rent = new Rent(params)

        assert rent.save() != null

        params.id = rent.id

        def model = controller.show()

        assert model.rentInstance == rent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rent/list'

        populateValidParams(params)
        def rent = new Rent(params)

        assert rent.save() != null

        params.id = rent.id

        def model = controller.edit()

        assert model.rentInstance == rent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rent/list'

        response.reset()

        populateValidParams(params)
        def rent = new Rent(params)

        assert rent.save() != null

        // test invalid parameters in update
        params.id = rent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rent/edit"
        assert model.rentInstance != null

        rent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rent/show/$rent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rent.clearErrors()

        populateValidParams(params)
        params.id = rent.id
        params.version = -1
        controller.update()

        assert view == "/rent/edit"
        assert model.rentInstance != null
        assert model.rentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rent/list'

        response.reset()

        populateValidParams(params)
        def rent = new Rent(params)

        assert rent.save() != null
        assert Rent.count() == 1

        params.id = rent.id

        controller.delete()

        assert Rent.count() == 0
        assert Rent.get(rent.id) == null
        assert response.redirectedUrl == '/rent/list'
    }
}
