import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QrCode {

    private BufferedImage qrCode;
    private int centreHeight;
    private int centreWidth;

    /**
     *
     * @param filepath absolute filepath of image
     */
    public QrCode(String filepath){
        try {
            qrCode = ImageIO.read(new File(filepath));

            centreHeight = qrCode.getHeight()/2;
            centreWidth = qrCode.getWidth()/2;
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

    public BufferedImage getQrCodeImage() {
        return qrCode;
    }
}
