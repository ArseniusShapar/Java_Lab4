package org.oop.lab4;

import java.util.*;

public class Polynomial {
    private final Map<Integer, Double> monomials;

    public Polynomial() {
        monomials = new HashMap<>();
    }

    public Polynomial(Polynomial polynomial) {
        monomials = new HashMap<>(polynomial.monomials);
        filterMonomials();
    }

    public Polynomial(Map<Integer, Double> monomials) {
        this.monomials = new HashMap<>(monomials);
        filterMonomials();
    }

    public Polynomial(String equation) {
        monomials = new HashMap<>();
        equation = equation.replaceAll("\\s+", "");
        equation = equation.replaceAll("\\+", " +");
        equation = equation.replaceAll("-", " -");
        for (String s : equation.split(" ")) {
            if (!s.equals("")) {
                Pair<Integer, Double> monomial = monomialFromEquation(s);
                addMonomial(monomial);
            }
        }
        filterMonomials();
    }

    private Pair<Integer, Double> monomialFromEquation(String equation) {
        int power;
        double coef;
        equation = equation.replaceAll(" ", "");
        equation = equation.replace(',', '.');

        if (!equation.contains("x")) {
            power = 0;
        } else if (!equation.contains("^")) {
            power = 1;
        } else {
            String s = equation.split("\\^")[1].replace("x^", "");
            power = Integer.parseInt(s);
        }

        if (!equation.contains("*") && equation.contains("x")) {
            if (equation.contains("-")) coef = -1;
            else coef = 1;
        } else {
            String s = equation.split("\\*")[0].replace("x", "").replace("*", "");
            s = s.replaceAll(" ", "");
            coef = Double.parseDouble(s);
        }

        return new Pair<>(power, coef);
    }

    private void filterMonomials() {
        for (Integer key : new HashSet<>(monomials.keySet())) {
            if (Math.abs(monomials.get(key)) < 0.01) monomials.remove(key);
        }
    }

    public Map<Integer, Double> getMonomials() {
        return new HashMap<>(monomials);
    }

    public Pair<Integer, Double>[] getArrayOfMonomials() {
        Pair<Integer, Double>[] arrayOfMonomials = new Pair[monomials.size()];
        List<Integer> sortedKeys = new ArrayList<>(monomials.keySet());
        Collections.sort(sortedKeys);
        Collections.reverse(sortedKeys);
        for (int i = 0; i < sortedKeys.size(); i++) {
            int power = sortedKeys.get(i);
            double coef = monomials.get(power);
            arrayOfMonomials[i] = new Pair<>(power, coef);
        }
        return arrayOfMonomials;
    }

    public int getLength() {
        return monomials.size();
    }

    public Pair<Integer, Double> getMonomial(int power) {
        return new Pair<>(power, monomials.get(power));
    }

    public void addMonomial(int power, double coef) {
        if (Math.abs(coef) < 0.01) {
        } else if (monomials.containsKey(power)) {
            if (Math.abs(monomials.get(power) + coef) < 0.01) monomials.remove(power);
            else monomials.put(power, monomials.get(power) + coef);
        } else monomials.put(power, coef);
    }

    public void addMonomial(Pair<Integer, Double> monomial) {
        addMonomial(monomial.first, monomial.second);
    }

    @Override
    public String toString() {
        if (monomials.size() == 0) return "";

        StringBuilder equation = new StringBuilder();
        String sign = "+";
        for (Pair<Integer, Double> monomial : getArrayOfMonomials()) {
            if (monomial.second < 0) sign = "-";
            equation.append(" " + sign + " ").append(monomialToString(monomial));
        }
        equation.delete(0, 1);
        equation.delete(1, 2);
        if (equation.charAt(0) == '+') equation.delete(0, 1);
        return equation.toString();
    }

    private String monomialToString(int power, double coef) {
        coef = Math.abs(coef);
        if (power == 0) return String.format("%.2f", coef);
        else if (coef == 1 && power == 1) return "x";
        else if (coef == 1) return String.format("x^%d", power);
        else if (power == 1) return String.format("%.2f * x", coef);
        else return String.format("%.2f * x^%d", coef, power);
    }

    private String monomialToString(Pair<Integer, Double> monomial) {
        return monomialToString(monomial.first, monomial.second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial that)) return false;

        return monomials.equals(that.monomials);
    }


}
