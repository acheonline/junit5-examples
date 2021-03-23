package ru.achernyavskiy0n.domain;

import lombok.*;

/**
 * 07.03.2021
 * Base model class for instantiation
 * @author a.chernyavskiy0n
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Person {
    private final String firstName;
    private final String lastName;
}
