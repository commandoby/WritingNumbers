package by.commandoby.writingNumbers.algorithm.impl;

import by.commandoby.writingNumbers.IO.RecipeReader;
import by.commandoby.writingNumbers.IO.impl.RecipeReaderRUImpl;
import by.commandoby.writingNumbers.algorithm.Algorithm;
import by.commandoby.writingNumbers.exceptions.NotNumberException;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgorithmRUImpl implements Algorithm {
    private static final Logger log = Logger.getLogger(AlgorithmRUImpl.class);
    private final RecipeReader recipeReader = new RecipeReaderRUImpl();

    @Override
    public String translateNumber(String number) {
        String text = null;
        try {
            if (recognizeNumber(number)) {
                StringBuilder textBuilder = new StringBuilder();
                boolean isNegative = recognizeNegativeNumber(number);
                BigInteger fullNumber = stringInBigInteger(number.trim());

                if (isNegative && fullNumber.compareTo(new BigInteger("0")) != 0) textBuilder.append("минус");
                if (isBigNumber(fullNumber)) {
                    twentyOneDigitRecipe(textBuilder, fullNumber);
                } else {
                    int numberOfCharacters = searchForTheNumberOfCharacters(fullNumber);
                    largeNumberRecipes(textBuilder, fullNumber, numberOfCharacters);
                    textBuilder.append(" умножить на десять в степени");
                    twelveDigitRecipe(textBuilder, new BigInteger(Integer.toString(numberOfCharacters - 3)));
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

    private boolean isBigNumber(BigInteger number) {
        return number.divide(new BigInteger("1000000000000000000000"))
                .compareTo(new BigInteger("0")) == 0;
    }

    private int searchForTheNumberOfCharacters(BigInteger number) {
        int numberOfCharacters = 0;
        while (true) {
            if (number.compareTo(new BigInteger("10").pow(numberOfCharacters)) > 0) {
                numberOfCharacters++;
            } else {
                break;
            }
        }
        return numberOfCharacters;
    }

//    ------------------------------------

    private void threeDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .mod(new BigInteger("1000"))
                .intValue();

        recipeHundreds(text, number / 100);
        if (number / 10 % 10 == 1) {
            recipeTeens(text, number % 10);
        } else {
            recipeDozens(text, number / 10 % 10);
            recipeUnits(text, number % 10);
        }
    }

    private void sixDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .divide(new BigInteger("1000"))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipeHundreds(text, number / 100);
            if (number / 10 % 10 == 1) {
                recipeTeens(text, number % 10);
                text.append(" ").append(recipeReader.getThousandList().get(0));
            } else {
                recipeDozens(text, number / 10 % 10);
                recipeThousandUnits(text, number % 10);
                recipeThousands(text, number % 10);
            }
            if (number % 100 == 0) text.append(" ").append(recipeReader.getThousandList().get(0));
        }

        threeDigitRecipe(text, fullNumber);
    }

    private void nineDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .divide(new BigInteger("1000000"))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipeHundreds(text, number / 100);
            if (number / 10 % 10 == 1) {
                recipeTeens(text, number % 10);
                text.append(" ").append(recipeReader.getMillionList().get(0));
            } else {
                recipeDozens(text, number / 10 % 10);
                recipeUnits(text, number % 10);
                recipeMillions(text, number % 10);
            }
            if (number % 100 == 0) text.append(" ").append(recipeReader.getMillionList().get(0));
        }

        sixDigitRecipe(text, fullNumber);
    }

    private void twelveDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .divide(new BigInteger("1000000000"))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipeHundreds(text, number / 100);
            if (number / 10 % 10 == 1) {
                recipeTeens(text, number % 10);
                text.append(" ").append(recipeReader.getBillionList().get(0));
            } else {
                recipeDozens(text, number / 10 % 10);
                recipeUnits(text, number % 10);
                recipeBillions(text, number % 10);
            }
            if (number % 100 == 0) text.append(" ").append(recipeReader.getBillionList().get(0));
        }

        nineDigitRecipe(text, fullNumber);
    }

    private void fifteenDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .divide(new BigInteger("1000000000000"))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipeHundreds(text, number / 100);
            if (number / 10 % 10 == 1) {
                recipeTeens(text, number % 10);
                text.append(" ").append(recipeReader.getTrillionList().get(0));
            } else {
                recipeDozens(text, number / 10 % 10);
                recipeUnits(text, number % 10);
                recipeTrillions(text, number % 10);
            }
            if (number % 100 == 0) text.append(" ").append(recipeReader.getTrillionList().get(0));
        }

        twelveDigitRecipe(text, fullNumber);
    }

    private void eighteenDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .divide(new BigInteger("1000000000000000"))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipeHundreds(text, number / 100);
            if (number / 10 % 10 == 1) {
                recipeTeens(text, number % 10);
                text.append(" ").append(recipeReader.getQuadrillionList().get(0));
            } else {
                recipeDozens(text, number / 10 % 10);
                recipeUnits(text, number % 10);
                recipeQuadrillions(text, number % 10);
            }
            if (number % 100 == 0) text.append(" ").append(recipeReader.getQuadrillionList().get(0));
        }

        fifteenDigitRecipe(text, fullNumber);
    }

    private void twentyOneDigitRecipe(StringBuilder text, BigInteger fullNumber) {
        int number = fullNumber
                .divide(new BigInteger("1000000000000000000"))
                .mod(new BigInteger("1000"))
                .intValue();

        if (number != 0) {
            recipeHundreds(text, number / 100);
            if (number / 10 % 10 == 1) {
                recipeTeens(text, number % 10);
                text.append(" ").append(recipeReader.getQuintillionList().get(0));
            } else {
                recipeDozens(text, number / 10 % 10);
                recipeUnits(text, number % 10);
                recipeQuintillions(text, number % 10);
            }
            if (number % 100 == 0) text.append(" ").append(recipeReader.getQuintillionList().get(0));
        }

        eighteenDigitRecipe(text, fullNumber);
    }

    private void largeNumberRecipes(StringBuilder text, BigInteger fullNumber, int numberOfCharacters) {
        int number = fullNumber
                .divide(new BigInteger("10").pow(numberOfCharacters - 3))
                .mod(new BigInteger("1000"))
                .intValue();

        recipeHundreds(text, number / 100);
        if (number / 10 % 10 == 1) {
            recipeTeens(text, number % 10);
        } else {
            recipeDozens(text, number / 10 % 10);
            recipeUnits(text, number % 10);
        }
    }

//    ---------------------------

    private void recipeUnits(StringBuilder text, int number) {
        if (number != 0) {
            if (!text.isEmpty()) text.append(" ");
            text.append(recipeReader.getUnitList().get(number));
        }
        if (text.isEmpty() && number == 0) {
            text.append(recipeReader.getUnitList().get(0));
        }
    }

    private void recipeTeens(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getTeenList().get(number));
    }

    private void recipeDozens(StringBuilder text, int number) {
        if (number != 0) {
            if (!text.isEmpty()) text.append(" ");
            text.append(recipeReader.getDozenList().get(number));
        }
    }

    private void recipeHundreds(StringBuilder text, int number) {
        if (number != 0) {
            if (!text.isEmpty()) text.append(" ");
            text.append(recipeReader.getHundredList().get(number));
        }
    }

    private void recipeThousandUnits(StringBuilder text, int number) {
        if (number != 0) {
            if (!text.isEmpty()) text.append(" ");
            text.append(recipeReader.getThousandList().get(number + 10));
        }
    }

    private void recipeThousands(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getThousandList().get(number));
    }

    private void recipeMillions(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getMillionList().get(number));
    }

    private void recipeBillions(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getBillionList().get(number));
    }

    private void recipeTrillions(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getTrillionList().get(number));
    }

    private void recipeQuadrillions(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getQuadrillionList().get(number));
    }

    private void recipeQuintillions(StringBuilder text, int number) {
        if (!text.isEmpty()) text.append(" ");
        text.append(recipeReader.getQuintillionList().get(number));
    }
}
