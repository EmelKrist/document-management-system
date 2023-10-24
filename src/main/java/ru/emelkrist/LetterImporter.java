package ru.emelkrist;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static ru.emelkrist.Attributes.*;

public class LetterImporter implements Importer{
    public static final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, String::isEmpty, BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
}
