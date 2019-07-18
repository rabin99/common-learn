package chapter2;

/**
 * @auther : lin
 * @date : 2019/4/25 17:38
 * @desc :
 */
public class TaxCalculatorMain {
    public static void main(String[] args) {
        TaxCalaculator taxCalaculator = new TaxCalaculator(1000d, 2000d, (s, b) -> s * 0.1 + b * 0.15);
        System.out.println(taxCalaculator.calculate());
    }
}
