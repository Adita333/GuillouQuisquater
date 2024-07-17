import java.math.BigInteger;
import java.util.Random;

public class GuillouQuisquater {
    public static void main(String[] args) throws IllegalAccessException {
        BigInteger j, v, n, phi;

        // Generar valores aleatorios para J, p y q
        Random rnd = new Random();
        j = BigInteger.probablePrime(64, rnd); // Genera un número primo de aproximadamente 64 bits
        BigInteger p = BigInteger.probablePrime(128, rnd); // Genera un número primo de aproximadamente 128 bits
        BigInteger q = BigInteger.probablePrime(128, rnd); // Genera otro número primo de aproximadamente 128 bits

        // Verificar que p y q sean diferentes
        while (p.equals(q)) {
            q = BigInteger.probablePrime(128, rnd);
        }

        try {
            phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            v = new BigInteger(phi.bitLength(), rnd).mod(phi);
            while (!v.gcd(phi).equals(BigInteger.ONE)) {
                v = v.add(BigInteger.ONE).mod(phi);
                if (v.equals(BigInteger.ZERO)) {
                    v = BigInteger.ONE;
                }
            }
            n = p.multiply(q);
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la generación de parámetros.");
            return;
        }

        System.out.println("Parámetros generados automáticamente:");
        System.out.println("J = " + j);
        System.out.println("p = " + p);
        System.out.println("q = " + q);

        GQService service = new GQService(j, v, n, phi);

        // Ejecutar pruebas manuales
        runManualTests(j, v, n);
    }

    // Método para ejecutar las pruebas unitarias manualmente con los parámetros
    // generados
    private static void runManualTests(BigInteger j, BigInteger v, BigInteger n) {
        Peggy peggy = new Peggy(j, v, n, BigInteger.ONE); // Puedes ajustar phi según sea necesario

        // Test 1: toString()
        System.out.println("Ejecutando testToString()...");
        if (peggy.toString().startsWith("""
                Información pública de Peggy: J = """)) {
            System.out.println("Test testToString() pasado correctamente.");
        } else {
            System.out.println("Error: testToString() falló.");
        }

        // Test 2: testSetB()
        System.out.println("Ejecutando testSetB()...");
        BigInteger expected = BigInteger.ONE;
        BigInteger actual = peggy.getJ().multiply(peggy.getB().modPow(peggy.getV(), peggy.getN())).mod(peggy.getN());
        if (actual.equals(expected)) {
            System.out.println("Test testSetB() pasado correctamente.");
        } else {
            System.out.println("Error: testSetB() falló.");
        }
    }
}
