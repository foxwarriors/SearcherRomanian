/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project.infr.and.tm;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import java.io.IOException;

public class App {
    public class Main {
        public static void main(String[] args) throws Exception {
            Indexer indexer = new Indexer("/Users/adrianciuca/Desktop/Project 1 Infr and TM/Index");
            // Assume indexFile is a method to index a single File
            indexer.indexFile(new File("/Users/adrianciuca/Desktop/Project 1 Infr and TM/Camasa.txt")); 
            indexer.close();
    
            Searcher searcher = new Searcher("/Users/adrianciuca/Desktop/Project 1 Infr and TM/Index");
            TopDocs results = searcher.search("camasa");
            // Assume displayResults is a method to display the search results
            displayResults(results, searcher.getIndexSearcher()); 
        }
    
        public static void displayResults(TopDocs results, IndexSearcher searcher) throws IOException {
            System.out.println("Total Hits: " + results.totalHits);
            for (ScoreDoc scoreDoc : results.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                System.out.println("Doc ID: " + scoreDoc.doc + ", Score: " + scoreDoc.score);
                System.out.println("Content: " + doc.get("content"));
            }
        }
        
    }
}