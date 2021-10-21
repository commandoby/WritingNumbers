package by.commandoby.writingNumbers.main;

import by.commandoby.writingNumbers.algorithm.Algorithm;
import by.commandoby.writingNumbers.algorithm.impl.AlgorithmRUImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Algorithm algorithm = new AlgorithmRUImpl();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Введите число: ");
            input = scanner.nextLine();

            if (input.equals("exit")) return;

            String result = algorithm.translateNumber(input);
            System.out.println("Результат: " + result);
        }
    }
}
