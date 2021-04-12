package ru.achernyavskiy0n

import ru.achernyavskiy0n.domain.Person
import spock.config.RunnerConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 11.04.2021
 * @author a.chernyavskiy0n
 */
class SpockExamplesTest extends Specification {

    @Shared person = Person.builder()
            .firstName("Anton")
            .lastName("Chernyavskiy")
            .build()

    def "should check any thing"() {
        expect:
        "Anton" == person.firstName
        "Chernyavskiy" == person.lastName
    }
}
