package by.commandoby.writingNumbers.algorithm.impl;

import by.commandoby.writingNumbers.algorithm.NumberProcessing;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberProcessingImpl implements NumberProcessing {
    @Override
    public boolean recognizeNumber(String number) {
        return number.matches("(-)?( )*\\d*");
    }

    @Override
    public boolean recognizeNegativeNumber(String number) {
        Pattern pattern = Pattern.compile("-");
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

    @Override
    public BigInteger stringInBigInteger(String number) {
        return new BigInteger(number.replaceAll("-", "").trim());
    }

    @Override
    public int searchMaxDigits(BigInteger number) {
        int numberOfCharacters = number.toString().length() - 1;
        numberOfCharacters /= 3;
        numberOfCharacters *= 3;
        return numberOfCharacters;
    }

    @Override
    public int getPartNumber(BigInteger fullNumber, int digits) {
        BigInteger partNumberBigInteger = fullNumber;
        if (digits != 0) {
            BigInteger divideBigInteger = new BigInteger("1000");
            for (int i = 0; i < digits / 3 - 1; i++) {
                divideBigInteger = divideBigInteger.multiply(new BigInteger("1000"));
            }
            partNumberBigInteger = partNumberBigInteger.divide(divideBigInteger);
        }
        return partNumberBigInteger.mod(new BigInteger("1000")).intValue();
    }
}
