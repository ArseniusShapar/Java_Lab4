package org.oop.lab4;

import java.util.List;
import java.util.Scanner;

public class Presentation {
    public void printSolution(Pair<Polynomial, Polynomial> input, Pair<Polynomial, Polynomial> solution) {
        Polynomial divided = input.first;
        Polynomial divider = input.second;
        Polynomial quotient = solution.first;
        Polynomial remainder = solution.second;

        System.out.println("Divided: " + divided);
        System.out.println("Divider: " + divider);
        System.out.println("Quotient: " + quotient);
        System.out.println("Remainder: " + remainder);
        System.out.printf("Equation: %s = (%s) * (%s) + (%s)%n", divided, divider, quotient, remainder);
    }

    public void printSolutionWithSteps(Pair<Polynomial, Polynomial> input, List<Pair<String, String>> steps) {
        Polynomial divided = input.first;
        Polynomial divider = input.second;

        System.out.println("Start");
        System.out.println("Divided: " + divided);
        System.out.println("Divider: " + divider + "\n");

        for (int i = 1; i < steps.size() - 1; i++) {
            System.out.println("Step " + i);
            System.out.println("Divided: " + steps.get(i).first);
            System.out.println("Divider: " + divider);
            System.out.println("Quotient: " + steps.get(i).second + "\n");
        }

        System.out.println("Result");
        System.out.println("Quotient: " + steps.get(steps.size() - 1).first);
        System.out.println("Remainder: " + steps.get(steps.size() - 1).second);
        System.out.printf("Equation: %s = (%s) * (%s) + (%s)%n", divided, divider, steps.get(steps.size() - 1).first, steps.get(steps.size() - 1).second);
    }

    public Pair<Polynomial, Polynomial> inputData(PolynomialsOperations PO) {
        Scanner in = new Scanner(System.in);

        System.out.print("\nInput first polynomial: ");
        Polynomial divided = new Polynomial(in.nextLine());
        System.out.print("Input second polynomial: ");
        Polynomial divider = new Polynomial(in.nextLine());

        Pair<Polynomial, Polynomial> input = new Pair<>(divided, divider);
        return input;
    }

    public void printData(Pair<Polynomial, Polynomial> input, Repository repository, PolynomialsOperations PO, Scanner in) {
        System.out.print("\nPrint solution with steps or not?(y/n): ");
        String answer = in.nextLine();
        System.out.print("\n");

        if (answer.equals("n")) {
            Pair<Polynomial, Polynomial> solution = PO.dividePolynomials(input.first, input.second);
            repository.saveSolution(input, solution);
            printSolution(input, solution);
        } else {
            List<Pair<String, String>> steps = PO.dividePolynomialsWithSteps(input.first, input.second);
            repository.saveSolutionWithSteps(steps);
            printSolutionWithSteps(input, steps);
        }
    }
}
