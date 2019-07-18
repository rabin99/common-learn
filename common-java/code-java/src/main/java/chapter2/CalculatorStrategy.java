package chapter2;

/**
 * @auther : lin
 * @date : 2019/4/25 17:35
 * @desc :
 */
@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary,double bonus);
}
