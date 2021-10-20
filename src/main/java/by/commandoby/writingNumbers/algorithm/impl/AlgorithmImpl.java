package by.commandoby.writingNumbers.algorithm.impl;

import by.commandoby.writingNumbers.IO.RecipeReader;
import by.commandoby.writingNumbers.IO.impl.RecipeReaderImpl;
import by.commandoby.writingNumbers.algorithm.Algorithm;
import by.commandoby.writingNumbers.exceptions.NotNumberException;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgorithmImpl implements Algorithm {
    private static final Logger log = Logger.getLogger(AlgorithmImpl.class);
    private final RecipeReader recipeReader = new RecipeReaderImpl();

    @Override
    public String translateNumber(String number) {
        String text = null;
        try {
            StringBuilder textBuilder = new StringBuilder();
            long fullNumber = recognizeTheNumber(number.trim());

            if (fullNumber < 0) {
                textBuilder.append("минус");
                text = twentyOneDigitRecipe(textBuilder, fullNumber * (-1)).toString();
            } else {
                text = twentyOneDigitRecipe(textBuilder, fullNumber).toString();
            }
        } catch (NotNumberException e) {
            log.error(e);
        }
        return text;
    }

    private long recognizeTheNumber(String number) throws NotNumberException {
        if (number.matches("(-)?( )*\\d*")) {
            Pattern pattern = Pattern.compile("-");
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                return Long.parseLong(matcher.replaceAll(" ").trim()) * (-1);
            }
            return Long.parseLong(number.trim());
        }
        throw new NotNumberException("\"" + number + "\" - is not a natural number.");
    }

//    ------------------------------------

    private void threeDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber % 1000);

        recipeHundreds(text, number / 100);
        if (number / 10 % 10 == 1) {
            recipeTeens(text, number % 10);
        } else {
            recipeDozens(text, number / 10 % 10);
            recipeUnits(text, number % 10);
        }

    }

    private void sixDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber / 1000 % 1000);

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

    private void nineDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber / 1000000 % 1000);

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

    private void twelveDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber / 1000000000 % 1000);

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

    private void fifteenDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber / 1000000000 / 1000 % 1000);

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

    private void eighteenDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber / 1000000000 / 1000000 % 1000);

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

    private StringBuilder twentyOneDigitRecipe(StringBuilder text, long fullNumber) {
        int number = (int) (fullNumber / 1000000000 / 1000000000);

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
        return text;
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
