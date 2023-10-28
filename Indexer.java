package project.infr.and.tm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

//import org.apache.lucene.analysis.ro.RomanianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Field;

public class Indexer {
    private IndexWriter writer;
    private TextExtractor textExtractor = new TextExtractor();

    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        MyRomanianAnalyzer analyzer = new MyRomanianAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(dir, config);
    }

    public void indexFile(File file) throws Exception {
        
        String textContent = textExtractor.extractText(file);
        Document doc = new Document();
        doc.add(new TextField("content", textContent, Field.Store.YES));
        writer.addDocument(doc);
        System.out.println("text content IS : ===> " + textContent);
    }

    public void close() throws IOException {
        writer.close();
    }
}
