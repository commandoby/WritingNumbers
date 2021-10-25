package by.commandoby.writingNumbers.algorithm.impl;

import by.commandoby.writingNumbers.DOM.DOMParserReader;
import by.commandoby.writingNumbers.DOM.impl.DOMParserReaderImpl;
import by.commandoby.writingNumbers.algorithm.Algorithm;
import by.commandoby.writingNumbers.exceptions.NotNumberException;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgorithmRUImpl implements Algorithm {
    private static final Logger log = Logger.getLogger(AlgorithmRUImpl.class);
    private final DOMParserReader parserReader = new DOMParserReaderImpl();

    @Override
    public String translateNumber(String number) {
        String text = null;
        try {
            if (recognizeNumber(number)) {
                StringBuilder textBuilder = new StringBuilder();
                boolean isNegative = recognizeNegativeNumber(number);
                BigInteger fullNumber = stringInBigInteger(number.trim());

                if (isNegative && fullNumber.compareTo(new BigInteger("0")) != 0) textBuilder.append("минус");
                for (int i = 0; i <= searchMaxDigits(fullNumber) / 3; i++) {
                    threeDigitRecipe(textBuilder, fullNumber, searchMaxDigits(fullNumber) - i * 3);
                }
                text = textBuilder.toString();
            }
        } catch (NotNumberException e) {
            log.error(e);
        }
        return text;
    }

    private boolean recognizeNumber(String number) throws NotNumberException {
        if (number.matches("(-)?( )*\\d*")) {
            return true;
        }
        throw new NotNumberException("\"" + number + "\" - is not a natural number.");
    }

    private boolean recognizeNegativeNumber(String number) {
        Pattern pattern = Pattern.compile("-");
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

    private BigInteger stringInBigInteger(String number) {
        return new BigInteger(number.replaceAll("-", "").trim());
    }

    private int searchMaxDigits(BigInteger number) {
        int numberOfCharacters = number.toString().length() - 1;
        numberOfCharacters /= 3;
        numberOfCharacters *= 3;
        return numberOfCharacters;
    }

    private void threeDigitRecipe(StringBuilder textBuilder, BigInteger fullNumber, int digits) {
        int number = fullNumber
                .divide(new BigInteger(String.valueOf(Math.round(Math.pow(10, digits)))))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipePartOfNumber(textBuilder, number / 100, digits, 100);
            if (number / 10 % 10 == 1) {
                recipePartOfNumber(textBuilder, number % 10, digits, 11);
                recipeThousands(textBuilder, 0, digits);
            } else {
                recipePartOfNumber(textBuilder, number / 10 % 10, digits, 10);
                recipePartOfNumber(textBuilder, number % 10, digits, 1);
                recipeThousands(textBuilder, number % 10, digits);
            }
        } else {
            if (textBuilder.isEmpty() && digits == 0) {
                textBuilder.append(parserReader.getTextList(0, 1).get(0));
            }
        }
    }

    private void recipePartOfNumber(StringBuilder textBuilder, int number, int digits, int partOfNumber) {
        if (number != 0) {
            if (!textBuilder.isEmpty()) textBuilder.append(" ");
            if (parserReader.getTextList(digits, partOfNumber) != null
                    && !parserReader.getTextList(digits, partOfNumber).isEmpty()) {
                textBuilder.append(parserReader.getTextList(digits, partOfNumber).get(number));
            } else {
                textBuilder.append(parserReader.getTextList(0, partOfNumber).get(number));
            }
        }
    }

    private void recipeThousands(StringBuilder textBuilder, int number, int digits) {
        if (digits != 0) {
            if (!textBuilder.isEmpty()) textBuilder.append(" ");
            try {
                textBuilder.append(parserReader.getTextList(digits, 0).get(number));
            } catch (NullPointerException e) {
                log.error("The library does not contain thousands of numbers with more than "
                        + digits + " characters.", e);
            }
        }
    }
}
