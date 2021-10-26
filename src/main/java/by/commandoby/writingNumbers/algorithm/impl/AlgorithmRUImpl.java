package by.commandoby.writingNumbers.algorithm.impl;

import by.commandoby.writingNumbers.DOM.DOMParserReader;
import by.commandoby.writingNumbers.DOM.impl.DOMParserReaderImpl;
import by.commandoby.writingNumbers.algorithm.Algorithm;
import by.commandoby.writingNumbers.algorithm.NumberProcessing;
import by.commandoby.writingNumbers.exceptions.NotNumberException;
import org.apache.log4j.Logger;

import java.math.BigInteger;

public class AlgorithmRUImpl implements Algorithm {
    private static final Logger log = Logger.getLogger(AlgorithmRUImpl.class);
    private final DOMParserReader parserReader = new DOMParserReaderImpl();
    private final NumberProcessing numberProcessing = new NumberProcessingImpl();

    @Override
    public String translateNumber(String number) {
        String text = null;
        try {
            if (numberProcessing.recognizeNumber(number)) {
                StringBuilder textBuilder = new StringBuilder();
                boolean isNegative = numberProcessing.recognizeNegativeNumber(number);
                BigInteger fullNumber = numberProcessing.stringInBigInteger(number.trim());
                int digits = numberProcessing.searchMaxDigits(fullNumber);

                if (isNegative && fullNumber.compareTo(new BigInteger("0")) != 0) textBuilder.append("минус");
                for (int i = 0; i <= digits / 3; i++) {
                    int partNumber = numberProcessing.getPartNumber(fullNumber, digits - i * 3);
                    threeDigitRecipe(textBuilder, partNumber, digits - i * 3);
                }
                text = textBuilder.toString();
            } else {
                throw new NotNumberException("\"" + number + "\" - is not a natural number.");
            }
        } catch (NotNumberException e) {
            log.error(e);
        }
        return text;
    }

    private void threeDigitRecipe(StringBuilder textBuilder, int partNumber, int digits) {
        if (partNumber != 0) {
            recipePartOfNumber(textBuilder, partNumber / 100, digits, 100);
            if (partNumber / 10 % 10 == 1) {
                recipePartOfNumber(textBuilder, partNumber % 10, digits, 11);
                recipeThousands(textBuilder, 0, digits);
            } else {
                recipePartOfNumber(textBuilder, partNumber / 10 % 10, digits, 10);
                recipePartOfNumber(textBuilder, partNumber % 10, digits, 1);
                recipeThousands(textBuilder, partNumber % 10, digits);
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
