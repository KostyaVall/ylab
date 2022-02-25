package dayFirst;

import java.util.ArrayList;
import java.util.List;

public class FibWithCache {
    private static final List<Integer> fibList = new ArrayList(47);

    static
    {
        fibList.add(0);
        fibList.add(1);
    }
    public static int fib(int n) {
        if (n <= 1) {
            return fibList.get(n);
        } else if (fibList.size() != (n - 1) ) {
            for (int i = 2; i < n; i++) {
                fibList.add(fibList.get(i - 1) + fibList.get(i - 2));
            }
        }
        return fibList.get(n - 1);
    }
}
