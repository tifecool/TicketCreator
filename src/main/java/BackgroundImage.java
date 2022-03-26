import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage {

    private BufferedImage backgroundImage;
    private int centreHeight;
    private int centreWidth;

    /**
     *
     * @param filepath absolute filepath of image
     */
    public BackgroundImage(String filepath){
        try {
            backgroundImage = ImageIO.read(new File(filepath));

            centreHeight = backgroundImage.getHeight()/2;
            centreWidth = backgroundImage.getWidth()/2;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCentreHeight() {
        return centreHeight;
    }

    public int getCentreWidth() {
        return centreWidth;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
}
