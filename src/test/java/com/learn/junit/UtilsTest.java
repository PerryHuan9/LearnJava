package com.learn.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    static List<Arguments> testCapitalize() {
        return List.of(
                Arguments.arguments("abc", "Abc"),
                Arguments.arguments("AppLE", "Apple"),
                Arguments.arguments("gddoe", "Gddoe"),
                Arguments.arguments("AAAabc", "Aaaabc")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 34, 4, 5, 56})
    void testAbs(int x) {
        assertEquals(x, Utils.abs(x));
    }

    @ParameterizedTest
    @ValueSource(ints = {-12, -34, -4, -5, -56})
    void testAbs2(int x) {
        assertEquals(-x, Utils.abs(x));
    }

    @ParameterizedTest
    @MethodSource
    void testCapitalize(String input, String result) {
        assertEquals(result, Utils.capitalize(input));
    }

    @ParameterizedTest
    @CsvSource({"abc, Abc", "Apple, Apple", "HELLO, Hello"})
    void testCapitalize2(String input, String result) {
        assertEquals(result, Utils.capitalize(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv")
    void testCapitalize3(String input, String result) {
        assertEquals(result, Utils.capitalize(input));
    }

}
