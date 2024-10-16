package org.suai.protocol;

import java.math.BigInteger;

public class GQService {
    public Peggy peggy;
    public Victor victor;

    public GQService(BigInteger j, BigInteger v, BigInteger n, BigInteger phi) throws IllegalAccessException {
        this.peggy = new Peggy(j, v, n, phi);
        System.out.println(peggy);
        this.victor = new Victor(v);
        this.step1();
    }

    private void step1() throws IllegalAccessException {
        BigInteger T = peggy.getR().modPow(peggy.getV(), peggy.getN());
        victor.setT(T);
        System.out.printf("""
                \n--- Paso 1 ---
                Peggy eligió un número aleatorio r = %d
                Calculó T = %d y lo envió a Víctor
                            
                        """, peggy.getR(), T
        );
        this.step2();
    }

    private void step2() throws IllegalAccessException {
        peggy.setD(victor.getRandomInt());
        System.out.printf("""
                \n--- Paso 2 ---
                Víctor eligió un número aleatorio d = %d y se lo envió a Peggy
                            
                        """, victor.getRandomInt()
        );
        this.step3();
    }

    private void step3() throws IllegalAccessException {
        BigInteger D = peggy.calculateD();
        victor.setD(D);
        System.out.printf("""
                \n--- Paso 3 ---
                Peggy calculó D = %d y se lo envió a Víctor
                            
                        """, D
        );
        this.step4();
    }

    private void step4() throws IllegalAccessException {
        victor.calculateT_(peggy);
        if (!victor.getT().equals(victor.getT_())) {
            throw new IllegalAccessException("La autenticidad de Peggy no se ha demostrado, ya que T' != T\nT = " + victor.getT() + ", T' = " + victor.getT_());
        }
        System.out.printf("""
                \n--- Paso 4 ---
                Víctor calculó T' = %d.
                T' = T, por lo tanto, la autenticidad de Peggy se ha demostrado.
                            
                        """, victor.getT_()
        );
    }

    public Victor getVictor() {
        return victor;
    }
}