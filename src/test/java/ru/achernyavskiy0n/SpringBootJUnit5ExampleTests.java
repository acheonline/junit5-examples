package ru.achernyavskiy0n;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import ru.achernyavskiy0n.domain.JUnit5ExampleConfiguration;
import ru.achernyavskiy0n.domain.Person;
import ru.achernyavskiy0n.domain.PersonResolver;

import java.util.Arrays;
import java.util.Collection;

/**
 * 23.03.2021
 * Class with examples of most important experimental annotations and concepts in test cases
 * @author a.chernyavskiy0n
 */
@SpringBootTest(classes = JUnit5ExampleConfiguration.class)
@ExtendWith(PersonResolver.class) // add od custom resolver to inject Person instances in test methods
public class SpringBootJUnit5ExampleTests {

    @DisplayName("Should start all SV_NAME test method calls with appropriate request_file") // naming of displaying test method
    @ParameterizedTest(name = "Test for {0} and request {1}, and {2} num") // naming of subtest while parametrized test executes
    @CsvSource({ // parameters to inject into test method
            "Srvncome, renc.xml, 1",
            "STIST, st_q.xml, 2",
            "SAR, sar.xml, 3",
            "OD_RO, od_ro.xml, 4"
    })
    void test(String data, String file, int num) {
        System.out.println(data + " " + file + " " + num); //dummy implementation
    }

    @DisplayName("Should test Person as an Object")
    @Test
    void personTest(Person person) { // inject object instance into test method to operate with it inside
        Assertions.assertSame("Anton", person.getFirstName()); // dummy getters of instance
        Assertions.assertSame("Chernyavskiy", person.getLastName());
    }

    @TestFactory // implementation of serial tests via dynamic logic based upon Executable abstract class
    @DisplayName("Dynamic tests for Person instance")
    Collection<DynamicTest> dynamicTests(Person person) {
        return Arrays.asList(
                DynamicTest.dynamicTest("check not null array", // name of particular test case
                        () -> Assertions.assertNotNull(person)), // assertion via lambda
                DynamicTest.dynamicTest("check first name",
                        () -> Assertions.assertSame("Anton", person.getFirstName(), "Check not completed "+ person.getFirstName())),
                DynamicTest.dynamicTest("check last name",
                        () -> Assertions.assertSame("Chernyavskiy", person.getLastName(), "Check not completed "+ person.getFirstName())));
    }
}
