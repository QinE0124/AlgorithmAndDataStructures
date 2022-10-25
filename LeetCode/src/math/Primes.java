package math;

import java.util.Arrays;

/**
 * @author QinE
 * @create 2022-06-09 21:12
 */
public class Primes {

    //Sieve of Eratosthenes
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++)
            if (isPrime[i])
                for (int j = i * i; j < n; j += i)
                    isPrime[j] = false;
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime[i])
                count++;

            return count;
    }
}
