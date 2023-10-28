package project.infr.and.tm;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.ParseException;

public class Searcher {
    private IndexSearcher searcher;

    public Searcher(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        searcher = new IndexSearcher(reader);
    }

    public TopDocs search(String queryStr) throws IOException, ParseException {
        QueryParser parser = new QueryParser("content", new MyRomanianAnalyzer());
        Query query = parser.parse(queryStr);
        return searcher.search(query, 10);
    }

    public IndexSearcher getIndexSearcher() {
        return searcher;
    } 
}
