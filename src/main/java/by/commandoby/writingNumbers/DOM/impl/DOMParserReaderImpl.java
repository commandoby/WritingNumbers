package by.commandoby.writingNumbers.DOM.impl;

import by.commandoby.writingNumbers.DOM.DOMParserReader;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DOMParserReaderImpl implements DOMParserReader {
    private static final String FILE_PATH = "src\\main\\resources\\textTemplates\\RU\\TextTemplate.xml";
    private static final Logger log = Logger.getLogger(DOMParserReaderImpl.class);
    private final Map<Integer, List<String>> textList = new HashMap<>();
    private Document document;

    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(FILE_PATH));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.error(e);
        }
        read();
    }

    private void read() {
        NodeList units = document.getElementsByTagName("units");
        readValues(units, 1);

        NodeList teens = document.getElementsByTagName("teens");
        readValues(teens, 11);

        NodeList dozens = document.getElementsByTagName("dozens");
        readValues(dozens, 10);

        NodeList hundreds = document.getElementsByTagName("hundreds");
        readValues(hundreds, 100);

        NodeList thousands = document.getElementsByTagName("thousands");
        readValues(thousands, 0);
    }

    private void readValues(NodeList nodeList, int partOfNumber) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            int digits = Integer.parseInt(
                    nodeList.item(i).getAttributes().getNamedItem("digits").getTextContent());
            List<String> values = new ArrayList();
            NodeList node = nodeList.item(i).getChildNodes();
            for (int j = 0; j < node.getLength(); j++) {
                if (node.item(j).getNodeName().equals("value")) {
                    values.add(node.item(j).getTextContent());
                }
            }
            textList.put(digits * 1000 + partOfNumber, values);
        }
    }

    @Override
    public Map<Integer, List<String>> getAllTextList() {
        return textList;
    }

    @Override
    public List<String> getTextList(int digits, int partOfNumber) {
        return textList.get(digits * 1000 + partOfNumber);
    }
}
