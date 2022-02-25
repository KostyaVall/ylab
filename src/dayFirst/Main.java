package dayFirst;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Ylab!");
        System.out.println("Для типа int доступно вычисление чисел Фибоначчи для n < 48 (для последовательности 0, 1, 1, 2, 3...)");

        long start = System.currentTimeMillis();
        System.out.println("recursion f: " + fibR(47));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + " ms");

        long startL = System.currentTimeMillis();
        System.out.println("loop f: " + fibL(47));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startL) + " ms");

        long startLowMemory = System.currentTimeMillis();
        System.out.println("loop low memory f: " + fibLowMemory(47));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startLowMemory) + " ms");

        long startWithCacheTest1 = System.currentTimeMillis();
        System.out.println("with cache test 1 f: " + FibWithCache.fib(47));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startWithCacheTest1) + " ms");

        long startWithCacheTest2 = System.currentTimeMillis();
        System.out.println("with cache test 2 f: " + FibWithCache.fib(47));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startWithCacheTest2) + " ms");
    }

    //recursion
    static int fibR(int n) {
        if (n <= 1) {
            return 0;
        } if (n == 2) {
            return 1;
        }else {
            return fibR(n - 1) + fibR(n - 2);
        }
    }

    //loop
    static int fibL(int n) {

        if (n <= 1) {
            return n;
        } else if (n == 2) {
            return 1;
        } else {
            int[] fibArray = new int[n];
            fibArray[0] = 0;
            fibArray[1] = 1;
            for (int i = 2; i < fibArray.length; i++) {
                fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
            }
            return fibArray[n - 1];
        }
    }

    //loop low memory
    static int fibLowMemory(int n) {

        if (n <= 1) {
            return n;
        } else if (n == 2) {
            return 1;
        } else {
            int sumPrevPrev = 0;
            int sumPrev = 1;
            int sum = 0;
            for (int i = 2; i < n; i++) {
                sum = sumPrevPrev + sumPrev;
                sumPrev += sumPrevPrev;
                sumPrevPrev = sumPrev - sumPrevPrev;
            }
            return sum;
        }
    }
}

//todo 1. Установить JDK
//todo 2. Установить IDE
//todo 3. Установить Git
//todo 4. Пройти 3 лекции на JavaRush: https://javarush.ipnodns.ru/
//todo 5. реализовать алгоритм числа Фибоначчи
//todo  5.1. Рекурсия
//todo  5.2. В цикле
//todo  5.3. Сокращаем использование памяти, алгоритм работает
//todo  5.4. Добавляем функцию в отдельный класс fib(n) и выделяем кеш в структуру данных
//todo example:
//    int fib(int n) {
//      if(n <= 1) return n;
//      else return fib(n-2) + fib(n - 1)
//      }