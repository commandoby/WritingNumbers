package by.commandoby.writingNumbers.algorithm;

import by.commandoby.writingNumbers.algorithm.impl.AlgorithmRUImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AlgorithmRUTests {
    private static Algorithm algorithm;

    @BeforeAll
    public static void setUp() {
        algorithm = new AlgorithmRUImpl();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/TranslateNumberRU_Test.csv", numLinesToSkip = 1)
    public void translateNumber_Test(String input, String expected) {
        String actualValue = algorithm.translateNumber(input);
        Assertions.assertEquals(expected, actualValue);
    }

    @Test
    public void translateNumber_MaxLongValueTest() {
        String actualValue = algorithm.translateNumber(String.valueOf(Long.MAX_VALUE));
        Assertions.assertEquals("девять квинтиллионов двести двадцать три квадриллиона " +
                "триста семьдесят два триллиона тридцать шесть миллиардов восемьсот пятьдесят четыре миллиона " +
                "семьсот семьдесят пять тысяч восемьсот семь", actualValue);
    }
}