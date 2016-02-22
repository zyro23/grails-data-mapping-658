package uniquetest

import grails.test.mixin.TestMixin
import grails.test.mixin.gorm.Domain
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification

@Domain(Dummy)
@TestMixin(HibernateTestMixin)
class DummyUniqueSpec extends Specification {

	def "test unique contraint"() {
		new Dummy(name: "dummy").save flush: true

		when: "second dummy with existing name is used"
		def dummy2 = new Dummy(name: "dummy")

		then: "validate on second dummy should fail because of unique constraint"
		!dummy2.validate()
		"unique" == dummy2.errors["name"].code
	}

}
