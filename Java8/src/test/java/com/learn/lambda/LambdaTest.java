package com.learn.lambda;

import com.learn.inter.CalculateInterface;
import org.junit.Test;

public class LambdaTest {

    @Test
    public void TestCalculate() {
//        calculate(1, 5);
        System.out.println(calculate(1, 5, new CalculateInterface() {
            @Override
            public int calculateNumber(int x, int y) {
                return x + y;
            }
        }));
    }

    @Test
    public void testLam() {
        System.out.println(calculate(1, 5, (int x, int y) -> {
            return x + y;
        }));
    }

    @Test
    public void testLamUltima() {
        System.out.println(calculate(1, 5, Integer::sum));
    }

    public int calculate(int a, int b, CalculateInterface calculateInterface) {
        int result = calculateInterface.calculateNumber(a, b);
        return result;
    }
}
