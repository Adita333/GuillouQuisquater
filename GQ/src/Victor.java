import java.math.BigInteger;
import java.security.SecureRandom;

public class Victor {
    private BigInteger T, T_, D;
    private final BigInteger d;

    public Victor(BigInteger v) {
        SecureRandom rnd = new SecureRandom();
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
        this.T_ = D.modPow(peggy.getV(), peggy.getN())
                .multiply(peggy.getJ().modPow(d, peggy.getN()))
                .mod(peggy.getN());
    }
}
