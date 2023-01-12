package org.oop.lab4.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oop.lab4.Pair;
import org.oop.lab4.Polynomial;
import org.oop.lab4.PolynomialsOperations;

public class PolynomialOperationsTest {
    private final PolynomialsOperations PO = new PolynomialsOperations();

    @Test
    public void multiplyOnCoef() {
        Polynomial polynomial1 = new Polynomial("");
        Polynomial polynomial2 = new Polynomial("x + 2,01 * x^2 + 2,03345 * x^5");
        Polynomial polynomial3 = new Polynomial("2 * x + 4,02 * x^2 + 4.0669 * x^5");

        Assertions.assertEquals(polynomial1, PO.multiplyOnCoef(polynomial1, 2));
        Assertions.assertEquals(polynomial2, PO.multiplyOnCoef(polynomial2, 1));
        Assertions.assertEquals(polynomial1, PO.multiplyOnCoef(polynomial2, 0));
        Assertions.assertEquals(polynomial3, PO.multiplyOnCoef(polynomial2, 2));
    }

    @Test
    public void addPolynomials() {
        Polynomial polynomial1 = new Polynomial("");
        Polynomial polynomial2 = new Polynomial("x + 2,01 * x^2 + 2,03345 * x^5");
        Polynomial polynomial3 = new Polynomial("x + x^3");
        Polynomial polynomial4 = new Polynomial("2 * x + 2,01 * x^2 + 2,03345 * x^5 + x^3");

        Assertions.assertEquals(polynomial2, PO.addPolynomials(polynomial1, polynomial2));
        Assertions.assertEquals(polynomial1, PO.addPolynomials(polynomial1, polynomial1));
        Assertions.assertEquals(polynomial4, PO.addPolynomials(polynomial2, polynomial3));
    }

    @Test
    public void multiplyOnMonomial() {
        Polynomial polynomial1 = new Polynomial("");
        Polynomial polynomial2 = new Polynomial("x + 2,01 * x^2 + 2,03345 * x^5");
        Polynomial polynomial3 = new Polynomial("x ^ 2 + 2,01 * x^3 + 2,03345 * x^6");
        Pair<Integer, Double> monomial1 = new Pair<>(1, 1.0);
        Pair<Integer, Double> monomial2 = new Pair<>(0, 1.0);
        Pair<Integer, Double> monomial3 = new Pair<>(2, 0.0);

        Assertions.assertEquals(polynomial1, PO.multiplyOnMonomial(polynomial1, monomial1));
        Assertions.assertEquals(polynomial1, PO.multiplyOnMonomial(polynomial1, monomial2));
        Assertions.assertEquals(polynomial1, PO.multiplyOnMonomial(polynomial1, monomial3));
        Assertions.assertEquals(polynomial1, PO.multiplyOnMonomial(polynomial2, monomial3));
        Assertions.assertEquals(polynomial2, PO.multiplyOnMonomial(polynomial2, monomial2));
        Assertions.assertEquals(polynomial3, PO.multiplyOnMonomial(polynomial2, monomial1));
    }

    @Test
    public void multiplyPolynomials() {
        Polynomial polynomial1 = new Polynomial("");
        Polynomial polynomial2 = new Polynomial("x + 2 * x^2 + 3 * x^3");
        Polynomial polynomial3 = new Polynomial("0.5 * x + x^2");
        Polynomial polynomial4 = new Polynomial("0.5 * x^2 + 2 * x^3 + 3.5 * x^4 + 3 * x^5");
        Polynomial polynomial5 = new Polynomial("x");
        Polynomial polynomial6 = new Polynomial("x^2 + 2 * x^3 + 3 * x^4");

        Assertions.assertEquals(polynomial1, PO.multiplyPolynomials(polynomial1, polynomial2));
        Assertions.assertEquals(polynomial4, PO.multiplyPolynomials(polynomial2, polynomial3));
        Assertions.assertEquals(polynomial6, PO.multiplyPolynomials(polynomial2, polynomial5));
    }

    @Test
    public void dividePolynomials() {
        Polynomial polynomial1 = new Polynomial("");
        Polynomial polynomial2 = new Polynomial("x^3 + x^2 + x + 1");
        Polynomial polynomial3 = new Polynomial("x + 1");
        Polynomial polynomial4 = new Polynomial("x^2 + 1");
        Polynomial polynomial5 = new Polynomial("2 * x^2 + 3");
        Polynomial polynomial6 = new Polynomial("6 * x^3 + 3 * x + 2 * x^2 + 4");
        Polynomial polynomial7 = new Polynomial("-6 * x + 1");
        Polynomial polynomial8 = new Polynomial("3 * x + 1");

        Pair<Polynomial, Polynomial> result1 = PO.dividePolynomials(polynomial2, polynomial3);
        Pair<Polynomial, Polynomial> result2 = PO.dividePolynomials(polynomial6, polynomial5);

        Assertions.assertEquals(polynomial4, result1.first);
        Assertions.assertEquals(polynomial1, result1.second);
        Assertions.assertEquals(polynomial8, result2.first);
        Assertions.assertEquals(polynomial7, result2.second);
        Assertions.assertEquals(polynomial6, PO.addPolynomials(polynomial7, PO.multiplyPolynomials(polynomial5, polynomial8)));
    }

    @Test
    public void shouldThrowException() {
        try {
            PO.dividePolynomials(new Polynomial("x"), new Polynomial());
        } catch (RuntimeException e) {
            String exceptedMessage = "Empty divider!";
            String actualMessage = e.getMessage();
            Assertions.assertEquals(exceptedMessage, actualMessage);
        }
    }

}
