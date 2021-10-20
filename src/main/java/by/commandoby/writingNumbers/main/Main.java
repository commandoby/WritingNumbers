package by.commandoby.writingNumbers.main;

import by.commandoby.writingNumbers.algorithm.Algorithm;
import by.commandoby.writingNumbers.algorithm.impl.AlgorithmImpl;

public class Main {
    public static void main(String[] args) {
        Algorithm algorithm = new AlgorithmImpl();
        System.out.println(algorithm.translateNumber("  - 8203405000008430000  "));
    }
}
