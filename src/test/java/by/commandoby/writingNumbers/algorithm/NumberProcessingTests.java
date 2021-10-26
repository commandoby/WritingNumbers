package by.commandoby.writingNumbers.algorithm;

import by.commandoby.writingNumbers.algorithm.impl.NumberProcessingImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigInteger;

public class NumberProcessingTests {
    private static NumberProcessing numberProcessing;

    @BeforeAll
    public static void setUp() {
        numberProcessing = new NumberProcessingImpl();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/RecognizeNumber_Test.csv", numLinesToSkip = 1)
    public void recognizeNumber_Test(String input, boolean expected) {
        boolean actualValue = numberProcessing.recognizeNumber(input);
        Assertions.assertEquals(expected, actualValue);
    }

    @Test
    public void recognizeNegativeNumber_TrueTest() {
        boolean actualValue = numberProcessing.recognizeNegativeNumber("-12");
        Assertions.assertTrue(actualValue);
    }

    @Test
    public void recognizeNegativeNumber_FalseTest() {
        boolean actualValue = numberProcessing.recognizeNegativeNumber("48");
        Assertions.assertFalse(actualValue);
    }

    @Test
    public void stringInBigInteger_Test() {
        BigInteger actualValue = numberProcessing.stringInBigInteger("-985");
        Assertions.assertEquals(new BigInteger("985"), actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/SearchMaxDigits_Test.csv", numLinesToSkip = 1)
    public void searchMaxDigits_Test(String input, int expected) {
        int actualValue = numberProcessing.searchMaxDigits(new BigInteger(input));
        Assertions.assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/GetPartNumber_Test.csv", numLinesToSkip = 1)
    public void getPartNumber_Test(String number, int digits, int expected) {
        int actualValue = numberProcessing.getPartNumber(new BigInteger(number), digits);
        Assertions.assertEquals(expected, actualValue);
    }
}
