package org.suai.protocol;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Victor {
    private BigInteger T, T_, D;
    private final BigInteger d;

    public Victor(BigInteger v) {
        SecureRandom rnd = new SecureRandom();
        
        // Generar d como un número aleatorio de tamaño adecuado
        this.d = new BigInteger(v.bitLength(), rnd).mod(v);
    }

    public BigInteger getT() {
        return T;
    }

    public BigInteger getT_() {
        return T_;
    }

    public BigInteger getRandomInt() {
        return d;
    }

    public void setT(BigInteger T) {
        this.T = T;
    }

    public void setD(BigInteger D) {
        this.D = D;
    }

    public void calculateT_(Peggy peggy) {
        // Calcular T' utilizando los valores de Peggy
        this.T_ = D.modPow(peggy.getV(), peggy.getN())
                .multiply(peggy.getJ().modPow(d, peggy.getN()))
                .mod(peggy.getN());
    }
}