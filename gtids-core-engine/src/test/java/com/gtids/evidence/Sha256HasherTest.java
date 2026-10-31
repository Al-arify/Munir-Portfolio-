package com.gtids.evidence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Sha256HasherTest {
    @Test
    void hashesUtf8InputDeterministically() {
        assertEquals(
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
                new Sha256Hasher().hash("test"));
    }
}
