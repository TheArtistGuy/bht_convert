import java.awt.*;
import java.awt.image.BufferedImage;

public class PictureGreyscale {
    private int [] pictureMap;
    private int width;
    private int height;

    public PictureGreyscale(BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pictureMap = new int[width * height];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                try {
                    this.setValueAtCoordinate(x, y, calculateGreyscale(new Color(image.getRGB(x, y))));
                }catch (Exception e){
                    e.printStackTrace();
                }
                }
        }
    }

    private int calculateGreyscale(Color c) {
        return (int) (c.getRed() * 0.3 + c.getBlue() * 0.11 + c.getGreen() * 0.59);
    }

    public int getValueAtCoordinate(int x, int y) throws Exception {
        if (x >= this.width || y >= this.height) throw new Exception("not in range");
        return pictureMap[y*width + x];
    }

    public void setValueAtCoordinate(int x, int y, int value) throws Exception{
        if (x >= this.width || y >= this.height) throw new Exception("not in range");
        pictureMap[y*width + x] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxValue(){
        int max = 0;
        for (int value : pictureMap){
            if (value > max) max = value;
        }
        return max;
    }

    public int getMinValue(){
        int min = Integer.MAX_VALUE;
        for (int value : pictureMap){
            if (value<min) min = value;
        }
        return min;
    }
}
