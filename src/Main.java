import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try{
            String filename = args[0];

            try{
                File dat = new File(filename);
                BufferedImage img = ImageIO.read(dat);

                int threshold = BHT.calculateBalancedHistogramTreshold(img);


                for (int x = 0; x < img.getWidth(); x++){
                    for (int y = 0; y < img.getHeight(); y ++){
                        boolean b = (greyscale(new Color(img.getRGB(x,y))) >= threshold);
                        img.setRGB(x,y,
                                b? ((255*255*255) + (255 * 255) + 255) : 0);
                    }
                }
                char[] chars = filename.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < chars.length-4; i++){
                    sb.append(chars[i]);
                }
                String outputFilename = sb.toString() + "_with_bht.png";
                System.out.println(outputFilename);
                File output = new File(outputFilename);
                ImageIO.write(img, "png", output);


            } catch (Exception e) {
                System.out.println("couldn't open file");
            }
        }catch (Exception e){
            System.out.println("No filename given");
        }
    }

    private static int greyscale(Color c) {
        return (int) (c.getRed() * 0.3 + c.getBlue() * 0.11 + c.getGreen() * 0.59);
    }
}
