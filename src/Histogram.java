import java.awt.image.BufferedImage;

class Histogram {
    private int[] histogram;
    private int steps;
    private PictureGreyscale image;

    Histogram(BufferedImage image) {
        this.steps = 256;
        this.histogram = newHistogramInt(steps);
        this.image = new PictureGreyscale(image);
        generateHistogramm();
    }

    Histogram(Histogram histogram) {
        this.steps = 256;
        this.histogram = newHistogramInt(steps);
        setHistogram(histogram.getHistogram());
        this.image = histogram.getImage();
    }

    Histogram(int[] h, PictureGreyscale image) {
        this.histogram = h;
        this.steps = 256;
        this.image = image;
    }


    private void setHistogram(int[] histogram) {
        for (int i = 0; i < histogram.length; i++){
            this.histogram[i] = histogram[i];
        }
    }

    int[] getHistogram() {
        return histogram;
    }

    public PictureGreyscale getImage() {
        return image;
    }

    private int[] newHistogramInt(int steps) {
        int[] histogram = new int[steps];
        for (int i : histogram){
            i = 0;
        }
        return histogram;
    }

    private float[] newHistogram(int steps) {
        float[] histogram = new float[steps];
        for (float i : histogram){
            i = 0.0f;
        }
        return histogram;
    }

    private void generateHistogramm(){
        assert (image.getMaxValue() < steps);
        for(int x = 0 ; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                try {
                    int value = image.getValueAtCoordinate(x, y);
                    histogram[value] += 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public float[] generateNormalisedHoistogram(){
        float[] histogram = newHistogram(steps);
        float number_elements = (float) this.getImage().getHeight() * this.getImage().getWidth();
        for (int i = 0; i < this.histogram.length; i++){
                    float val = (float) this.histogram[i];
                    float normalizedVal = val / number_elements;
                    histogram[i] = normalizedVal;
        }
        return histogram;
    }

    public float[] generateCumulativeHistogram(){
        float[] normalisedHist = this.generateNormalisedHoistogram();
        float[] h = newHistogram(steps);
        this.print();
        float accumulate = 0.0f;
        for (int i = 0; i < h.length; i++){
            accumulate = accumulate + normalisedHist[i];
            h[i] = accumulate;
        }
       return h;
    }

    public void print(){
        for (int i = 0; i< histogram.length; i++){
            System.out.println(i + " : " + histogram[i]);
        }
    }
}
