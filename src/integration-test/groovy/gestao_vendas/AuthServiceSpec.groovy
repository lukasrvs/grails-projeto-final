package gestao_vendas

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AuthServiceSpec extends Specification {

    AuthService authService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Auth(...).save(flush: true, failOnError: true)
        //new Auth(...).save(flush: true, failOnError: true)
        //Auth auth = new Auth(...).save(flush: true, failOnError: true)
        //new Auth(...).save(flush: true, failOnError: true)
        //new Auth(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //auth.id
    }

    void "test get"() {
        setupData()

        expect:
        authService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Auth> authList = authService.list(max: 2, offset: 2)

        then:
        authList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        authService.count() == 5
    }

    void "test delete"() {
        Long authId = setupData()

        expect:
        authService.count() == 5

        when:
        authService.delete(authId)
        sessionFactory.currentSession.flush()

        then:
        authService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Auth auth = new Auth()
        authService.save(auth)

        then:
        auth.id != null
    }
}
