package com.learn.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThrowTest {

    @Test
    void testDoThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            Throws.doThrow(-1);
        });
    }
}
