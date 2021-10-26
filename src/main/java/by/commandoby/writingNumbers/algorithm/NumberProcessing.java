package by.commandoby.writingNumbers.algorithm;

import java.math.BigInteger;

public interface NumberProcessing {
    boolean recognizeNumber(String number);

    boolean recognizeNegativeNumber(String number);

    BigInteger stringInBigInteger(String number);

    int searchMaxDigits(BigInteger number);

    int getPartNumber(BigInteger fullNumber, int digits);
}
