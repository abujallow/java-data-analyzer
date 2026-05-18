package businesslogic;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataAnalyzerTest {

    private File createTestFile(String content) throws IOException {
        File tempFile = File.createTempFile("testData", ".txt");

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }

        return tempFile;
    }

    @Test
    void testAnalyzeFile_basicDataset() throws IOException {
        File file = createTestFile("1\n2\n3\n4\n");

        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.analyzeFile(file.getAbsolutePath());

        assertEquals(4, analyzer.getCount());
        assertEquals(2.5, analyzer.getMean(), 0.0001);
        assertEquals(1.29099, analyzer.getStandardDeviation(), 0.0001);
        assertEquals(2.73861, analyzer.getRMS(), 0.0001);
    }

    @Test
    void testAnalyzeFile_singleValue() throws IOException {
        File file = createTestFile("5\n");

        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.analyzeFile(file.getAbsolutePath());

        assertEquals(1, analyzer.getCount());
        assertEquals(5.0, analyzer.getMean(), 0.0001);
        assertEquals(0.0, analyzer.getStandardDeviation());
        assertEquals(5.0, analyzer.getRMS(), 0.0001);
    }

    @Test
    void testAnalyzeFile_negativeValues() throws IOException {
        File file = createTestFile("-2\n-4\n-6\n");

        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.analyzeFile(file.getAbsolutePath());

        assertEquals(3, analyzer.getCount());
        assertEquals(-4.0, analyzer.getMean(), 0.0001);
        assertEquals(2.0, analyzer.getStandardDeviation(), 0.0001);
    }
}