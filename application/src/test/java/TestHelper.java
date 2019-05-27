import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestHelper
{
    public static void main(String[] args)
    {
        // Uncomment to create test image
        try {
            createRGBImage();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void createRGBImage() throws IOException {

        int width = 6;

        BufferedImage image = new BufferedImage(6, 4,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            image.setRGB(x, 0, (255<<24) | (255<<16)); // red
            image.setRGB(x, 1, (255<<24) | (255<<8)); // green
            image.setRGB(x, 2, (255<<24) | 255); // blue
            image.setRGB(x, 3, (255<<24) | (255<<16) | (255<<8) | 255); // white
        }

        // Uncomment  to save the image
        ImageIO.write(image, "png", new File("../resources/rgb.png"));

    }
}
