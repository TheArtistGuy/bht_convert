import java.awt.image.BufferedImage;

public class BHT {

    //TODO index out of bounds, treshold > 256

    public static int calculateBalancedHistogramTreshold(BufferedImage img){
        Histogram h = new Histogram(img);
        float[] cumulativeHistogram = h.generateCumulativeHistogram();
        int min = findEntryPointMinimum(cumulativeHistogram, 0);
        int max = findEntryPointMaximum(cumulativeHistogram.length -1, cumulativeHistogram);

        int treshold = calculate_threshold(cumulativeHistogram, min, max);

        return treshold;
    }

    private static int calculate_threshold(float[] cumulativeHistogram, int min, int max) {
        while (min < max){
            int t = (min + max) / 2;
            float minVal = cumulativeHistogram[min];
            float centerVal = cumulativeHistogram[t];
            float maxVal = cumulativeHistogram[max];
            if ((centerVal - minVal) < (maxVal - centerVal)){
                max = max - 1;
            } else {
                min = min + 1;
            }
        }
        System.out.println("Treshold = " + max);
        return max;
    }

    private static int findEntryPointMaximum(int max, float[] cumulativeHistogram) {
        while (max > 0 && (cumulativeHistogram[max] == cumulativeHistogram[max - 1])) {
            max = max -1;
        }
        return max;
    }

    private static int findEntryPointMinimum(float[] cumulativeHistogram, int min) {
        while (min < cumulativeHistogram.length -1 && (cumulativeHistogram[min] == cumulativeHistogram[min +1])){
            min = min + 1;
        }
        return min;
    }
}
