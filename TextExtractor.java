package project.infr.and.tm;

import java.io.File;

import org.apache.tika.Tika;

public class TextExtractor {
    public String extractText(File file) throws Exception {
        Tika tika = new Tika();
        String text = tika.parseToString(file);
        System.out.println("The file that needs to be extracted : =====> " + file);
        System.out.println("The extracted text is : =====> " + text);
        return text;
    }
}
