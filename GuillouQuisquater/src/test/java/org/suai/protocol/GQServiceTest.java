package org.suai.protocol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;


class GQServiceTest {
    SecureRandom rnd = new SecureRandom();

    @Test
    @DisplayName("Prueba 1: Verificar que se lance una excepción")
    public void unitTest1() {
        // Usar números primos grandes para las pruebas
        BigInteger j = BigInteger.probablePrime(2048, rnd);
        BigInteger v = BigInteger.probablePrime(2048, rnd);
        BigInteger n = BigInteger.probablePrime(2048, rnd);
        BigInteger phi = BigInteger.probablePrime(2048, rnd);

        // Verifica que se lance una excepción
        try {
            new GQService(j, v, n, phi);
            Assertions.fail("Se esperaba una excepción en new GQService(...), pero no se produjo.");
        } catch (IllegalAccessException e) {
            System.out.println("Excepción capturada correctamente: " + e.getMessage());
            Assertions.assertTrue(true, "Excepción capturada correctamente: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Prueba 2: Verificar que T_ es igual a T")
    public void unitTest2() {
        // Usar números primos grandes para las pruebas
        BigInteger j = BigInteger.probablePrime(2048, rnd);
        BigInteger v = BigInteger.probablePrime(2048, rnd);
        BigInteger p = BigInteger.probablePrime(2048, rnd);
        BigInteger q = BigInteger.probablePrime(2048, rnd);
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        try {
            GQService service = new GQService(j, v, n, phi);
            Victor victor = service.getVictor();
            Assertions.assertEquals(victor.getT_(), victor.getT(), "T_ no es igual a T");
            System.out.println("Prueba exitosa: T_ es igual a T");
            Assertions.assertTrue(true, "Prueba exitosa: T_ es igual a T");
        } catch (IllegalAccessException e) {
            System.out.println("Se produjo una excepción inesperada: " + e.getMessage());
            Assertions.fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }
}