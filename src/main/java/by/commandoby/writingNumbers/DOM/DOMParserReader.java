package by.commandoby.writingNumbers.DOM;

import java.util.List;
import java.util.Map;

public interface DOMParserReader {
    Map<Integer,List<String>> getAllTextList();
    
    List<String> getTextList(int digits, int partOfNumber);
}
