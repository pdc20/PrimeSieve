import java.awt.*;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Sieve {
    int limit;
    private final boolean[] isNotPrime;

    public Sieve(int limit) {
        this.limit = limit;
        isNotPrime = new boolean[limit + 1];
    }

    public void construct() {
        // isNotPrime list is initialised to all false
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for (int i = 2;i <= limit; ++i) {
            if (isNotPrime[i]) continue;
            for (int j = i*2;j <= limit; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

    public boolean isPrime(int n) {
        if (n < 0) return false;
        return !isNotPrime[n];
    }

    public static void main(String[] args) throws IOException {
        try (FileWriter fileWriter = new FileWriter("output.csv")) {
            fileWriter.write("n,ms\n");
            for (int i = 50000000; i <= 1050000000; i += 50000000) {
                Sieve s = new Sieve(i);
                long startTime = System.currentTimeMillis();
                s.construct();
                long endTime = System.currentTimeMillis();
                fileWriter.write("" + i + "," + (endTime - startTime) + "\n");
                System.out.println("i = " + i + " done in " + (endTime - startTime) + "ms.");
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
