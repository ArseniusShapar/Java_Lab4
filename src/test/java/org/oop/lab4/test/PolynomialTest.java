package org.oop.lab4.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oop.lab4.Pair;
import org.oop.lab4.Polynomial;
import org.oop.lab4.PolynomialsOperations;

public class PolynomialTest {

    @Test
    public void polynomialFromString() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial("0 * x + 0 * x^2");
        Polynomial p3 = new Polynomial("-0 * x - 0 * x^2");
        Polynomial p4 = new Polynomial("-3 + 3 * x^2 - 5 * x");
        Polynomial p5 = new Polynomial("-6 * x + 3 * x^2 + x - 3");
        Polynomial p6 = new Polynomial("-x - 8 * x + 4 * x + 3 * x^2 - 3");


        Assertions.assertEquals(p1, p2);
        Assertions.assertEquals(p1, p3);
        Assertions.assertEquals(p4, p5);
        Assertions.assertEquals(p4, p6);
    }
}
