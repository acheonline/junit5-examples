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
 *
 * @author a.chernyavskiy0n
 */
@SpringBootTest(classes = JUnit5ExampleConfiguration.class)
@ExtendWith(PersonResolver.class)
public class SpringBootJUnit5ExampleTests {

    @DisplayName("Should start all SV_NAME test method calls with appropriate request_file")
    @ParameterizedTest(name = "Test for {0} and request {1}, and {2} num")
    @CsvSource({
            "Srvncome, renc.xml, 1",
            "STIST, st_q.xml, 2",
            "SAR, sar.xml, 3",
            "OD_RO, od_ro.xml, 4"
    })
    void test(String data, String file, int num) {
        System.out.println(data + " " + file + " " + num);
    }

    @DisplayName("Should test Person as an Object")
    @Test
    void personTest(Person person) {
        Assertions.assertSame("Anton", person.getFirstName());
        Assertions.assertSame("Chernyavskiy", person.getLastName());
    }

    @TestFactory
    @DisplayName("Dynamic tests for Person instance")
    Collection<DynamicTest> dynamicTests(Person person) {
        return Arrays.asList(
                DynamicTest.dynamicTest("check not null array",
                        () -> Assertions.assertNotNull(person)),
                DynamicTest.dynamicTest("check first name",
                        () -> Assertions.assertSame("Anton", person.getFirstName(), "Check not completed "+ person.getFirstName())),
                DynamicTest.dynamicTest("check last name",
                        () -> Assertions.assertSame("Chernyavskiy", person.getLastName(), "Check not completed "+ person.getFirstName())));
    }
}
