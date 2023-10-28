package project.infr.and.tm;

import java.util.Arrays;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.tartarus.snowball.ext.RomanianStemmer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;

public class MyRomanianAnalyzer extends Analyzer {

    private static final CharArraySet ROMANIAN_STOP_WORDS_SET;

    static {
        // Replace with your list of Romanian stop words
        String[] ROMANIAN_STOP_WORDS = {
            "un", "una", "unei", /* ... other stop words ... */
        };
        ROMANIAN_STOP_WORDS_SET = new CharArraySet(Arrays.asList(ROMANIAN_STOP_WORDS), false);
    }
    
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(source);
        result = new ASCIIFoldingFilter(result);  // Remove diacritics
        result = new StopFilter(result, ROMANIAN_STOP_WORDS_SET);  // Use Romanian stop words set
        result = new SnowballFilter(result, new RomanianStemmer());  // Romanian stemming
        return new TokenStreamComponents(source, result);
    }

}

