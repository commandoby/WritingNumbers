package by.commandoby.writingNumbers.IO.impl;

import static by.commandoby.writingNumbers.IO.FilePathEnum.*;

import by.commandoby.writingNumbers.IO.RecipeReader;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeReaderImpl implements RecipeReader {
    private static Logger log = Logger.getLogger(RecipeReaderImpl.class);

    private List<String> unitList;
    private List<String> teenList;
    private List<String> dozenList;
    private List<String> hundredList;
    private List<String> thousandList;
    private List<String> millionList;
    private List<String> billionList;
    private List<String> trillionList;
    private List<String> quadrillionList;
    private List<String> quintillionList;

    {
        unitList = readTheRecipeForNumbers(UNIT_FILE_PATH.getValue());
        teenList = readTheRecipeForNumbers(TEEN_FILE_PATH.getValue());
        dozenList = readTheRecipeForNumbers(DOZEN_FILE_PATH.getValue());
        hundredList = readTheRecipeForNumbers(HUNDRED_FILE_PATH.getValue());
        thousandList = readTheRecipeForNumbers(THOUSAND_FILE_PATH.getValue());
        millionList = readTheRecipeForNumbers(MILLION_FILE_PATH.getValue());
        billionList = readTheRecipeForNumbers(BILLION_FILE_PATH.getValue());
        trillionList = readTheRecipeForNumbers(TRILLION_FILE_PATH.getValue());
        quadrillionList = readTheRecipeForNumbers(QUADRILLION_FILE_PATH.getValue());
        quintillionList = readTheRecipeForNumbers(QUINTILLION_FILE_PATH.getValue());
    }

    @Override
    public List<String> getUnitList() {
        return unitList;
    }

    @Override
    public List<String> getTeenList() {
        return teenList;
    }

    @Override
    public List<String> getDozenList() {
        return dozenList;
    }

    @Override
    public List<String> getHundredList() {
        return hundredList;
    }

    @Override
    public List<String> getThousandList() {
        return thousandList;
    }

    @Override
    public List<String> getMillionList() {
        return millionList;
    }

    @Override
    public List<String> getBillionList() {
        return billionList;
    }

    @Override
    public List<String> getTrillionList() {
        return trillionList;
    }

    @Override
    public List<String> getQuadrillionList() {
        return quadrillionList;
    }

    @Override
    public List<String> getQuintillionList() {
        return quintillionList;
    }

    private List<String> readTheRecipeForNumbers(String filePath) {
        List<String> recipeForNumbersList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(
                new FileReader("src\\main\\resources\\textTemplates\\RU\\" + filePath))) {
            String text;
            while ((text = reader.readLine()) != null) recipeForNumbersList.add(text);
        } catch (IOException e) {
            log.error(e);
        }

        return recipeForNumbersList;
    }
}
