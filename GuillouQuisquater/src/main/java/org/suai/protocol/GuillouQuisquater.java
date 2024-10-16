package org.suai.protocol;

import java.math.BigInteger;
import java.security.SecureRandom;

public class GuillouQuisquater {
    public static void main(String[] args) throws IllegalAccessException {
        if (args.length == 0) {
            System.out.println("Los parámetros de entrada están ausentes");
            return;
        }
        if (args[0].equals("/help") || args[0].equals("h")) {
            System.out.println("""
                    El programa debe recibir los siguientes parámetros:
                    \t- Número J (clave pública)
                    \t- 2 números primos p y q""");
            return;
        }
        if (args.length < 3) {
            System.out.println("Se ha pasado un número incorrecto de parámetros");
            return;
        }
        if (args[1].equals(args[2])) {
            System.out.println("Los números p y q deben ser diferentes");
            return;
        }
        BigInteger  v, phi;
        try {
            SecureRandom rnd = new SecureRandom();
            BigInteger p = BigInteger.probablePrime(2048, rnd);
            BigInteger q = BigInteger.probablePrime(2048, rnd);

            // Verifica que p y q sean realmente diferentes
            while (p.equals(q)) {
                q = BigInteger.probablePrime(2048, rnd);
            }

            phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            v = new BigInteger(phi.bitLength(), rnd).mod(phi);
            while (!v.gcd(phi).equals(BigInteger.ONE)) {
                v = v.add(BigInteger.ONE).mod(phi);
                if (v.equals(BigInteger.ZERO)) {
                    v = BigInteger.ONE;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al leer los parámetros de entrada.");
            return;
        }
    }
}