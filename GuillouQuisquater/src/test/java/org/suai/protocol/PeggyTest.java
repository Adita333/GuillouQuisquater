package org.suai.protocol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

class PeggyTest {
    Peggy peggy;
    SecureRandom rnd = new SecureRandom();

    @BeforeEach
    void prepareData() {
        // Generar números primos grandes
        BigInteger j = BigInteger.probablePrime(2048, rnd);
        BigInteger v = BigInteger.probablePrime(2048, rnd);
        BigInteger p = BigInteger.probablePrime(2048, rnd);
        BigInteger q = BigInteger.probablePrime(2048, rnd);
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        peggy = new Peggy(j, v, n, phi);
    }

    @Test
    @DisplayName("Prueba 1: Verificar la relación matemática en el método setB()")
    void testSetB() {
        try {
            Assertions.assertEquals(
                peggy.getJ().multiply(peggy.getB().modPow(peggy.getV(), peggy.getN())).mod(peggy.getN()),
                BigInteger.ONE
            );
            System.out.println("Prueba exitosa: La relación matemática en el método setB() es correcta.");
        } catch (AssertionError e) {
            System.out.println("Prueba fallida: La relación matemática en el método setB() no es correcta.");
            Assertions.fail("Prueba fallida: La relación matemática en el método setB() no es correcta.");
        }
    }

     
}