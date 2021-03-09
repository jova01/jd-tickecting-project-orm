package com.cybertek;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringToIntTest {


    @DisplayName("Test with various input!")
    @ParameterizedTest
    @CsvSource({
            "-100, -100",
            "5, 5",
            "0, ",
            "0, -"})
    void stringToint(int num, String str) {
        assertEquals(num, StringToInt.stringToint(str));
        assertThrows(ArithmeticException.class, () -> StringToInt.stringToint("200A"));
    }
}