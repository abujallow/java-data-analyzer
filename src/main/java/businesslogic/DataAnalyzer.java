package businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataAnalyzer {

    private int count = 0;
    private double mean = 0.0;
    private double sumSquares = 0.0;
    private double m2 = 0.0;

    public void analyzeFile(String filePath) throws IOException {

        count = 0;
        mean = 0.0;
        sumSquares = 0.0;
        m2 = 0.0;

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        double delta;

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            double x = Double.parseDouble(line.trim());

            count++;

            delta = x - mean;
            mean += delta / count;
            m2 += delta * (x - mean);

            sumSquares += x * x;
        }

        reader.close();
    }

    public int getCount() {
        return count;
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        if (count < 2) return 0.0;
        return Math.sqrt(m2 / (count - 1));
    }

    public double getRMS() {
        if (count == 0) return 0.0;
        return Math.sqrt(sumSquares / count);
    }

    public String getFormattedResults() {
        return "Count: " + getCount() + "\n" +
                "Mean: " + getMean() + "\n" +
                "Standard Deviation: " + getStandardDeviation() + "\n" +
                "RMS: " + getRMS();
    }
}