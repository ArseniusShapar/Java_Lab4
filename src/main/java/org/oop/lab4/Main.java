package org.oop.lab4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PolynomialsOperations PO = new PolynomialsOperations();
        Presentation presentation = new Presentation();
        Repository repository = new Repository();
        Scanner in = new Scanner(System.in);

        Pair<Polynomial, Polynomial> input = presentation.inputData(PO);
        presentation.printData(input, repository, PO, in);
    }
}
