package org.oop.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolynomialsOperations {
    public Polynomial multiplyOnCoef(Polynomial polynomial, double coef) {
        Polynomial result = new Polynomial();
        Map<Integer, Double> monomials = polynomial.getMonomials();
        for (Integer power : monomials.keySet()) result.addMonomial(power, coef * monomials.get(power));
        return result;
    }

    public Polynomial addPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial(polynomial1);
        Map<Integer, Double> monomials = polynomial2.getMonomials();
        for (Integer power : monomials.keySet()) result.addMonomial(power, monomials.get(power));
        return result;
    }

    public Polynomial subtractPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        return addPolynomials(polynomial1, multiplyOnCoef(polynomial2, -1));
    }

    public Polynomial multiplyOnMonomial(Polynomial polynomial, Pair<Integer, Double> monomial) {
        Polynomial result = new Polynomial();
        Map<Integer, Double> monomials = polynomial.getMonomials();
        int power = monomial.first;
        double coef = monomial.second;
        for (Integer key : monomials.keySet()) result.addMonomial(power + key, coef * monomials.get(key));
        return result;
    }

    public Polynomial multiplyPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Pair<Integer, Double> monomial : polynomial2.getArrayOfMonomials()) {
            result = addPolynomials(result, multiplyOnMonomial(polynomial1, monomial));
        }
        return result;
    }

    public Pair<Polynomial, Polynomial> dividePolynomials(Polynomial divided, Polynomial divider) throws RuntimeException {
        Polynomial quotient = new Polynomial();
        Polynomial remainder;

        if (divided.equals(new Polynomial())) {
            remainder = new Polynomial(divider);
        } else if (divider.equals(new Polynomial())) {
            throw new RuntimeException("Empty divider!");
        } else {
            int n1;
            double a1;
            final int n2 = divider.getArrayOfMonomials()[0].first;
            final double a2 = divider.getArrayOfMonomials()[divider.getLength() - 1].second;

            while (divided.getLength() != 0) {
                n1 = divided.getArrayOfMonomials()[0].first;
                a1 = divided.getArrayOfMonomials()[0].second;

                if (n1 < n2) break;

                Pair<Integer, Double> monomial = new Pair<>(n1 - n2, a1 / a2);
                Polynomial subtrahend = multiplyOnMonomial(divider, monomial);
                divided = subtractPolynomials(divided, subtrahend);
                quotient.addMonomial(monomial);
            }
            remainder = new Polynomial(divided);
        }

        return new Pair<>(quotient, remainder);
    }

    public List<Pair<String, String>> dividePolynomialsWithSteps(Polynomial divided, Polynomial divider) throws RuntimeException {
        List<Pair<String, String>> steps = new ArrayList<>();
        steps.add(new Pair<>(divided.toString(), divider.toString()));

        Polynomial quotient = new Polynomial();
        Polynomial remainder;

        if (divided.equals(new Polynomial())) {
            remainder = new Polynomial(divider);
        } else if (divider.equals(new Polynomial())) {
            throw new RuntimeException("Empty divider!");
        } else {
            int n1;
            double a1;
            final int n2 = divider.getArrayOfMonomials()[0].first;
            final double a2 = divider.getArrayOfMonomials()[0].second;

            while (divided.getLength() != 0) {
                steps.add(new Pair<>(divided.toString(), quotient.toString()));

                n1 = divided.getArrayOfMonomials()[0].first;
                a1 = divided.getArrayOfMonomials()[0].second;

                if (n1 < n2) break;

                Pair<Integer, Double> monomial = new Pair<>(n1 - n2, a1 / a2);
                Polynomial subtrahend = multiplyOnMonomial(divider, monomial);
                divided = subtractPolynomials(divided, subtrahend);
                quotient.addMonomial(monomial);
            }
            if (divided.getLength() == 0) steps.add(new Pair<>(divided.toString(), quotient.toString()));
            remainder = new Polynomial(divided);
        }

        steps.add(new Pair<>(quotient.toString(), remainder.toString()));
        return steps;
    }


}
