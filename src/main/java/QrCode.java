import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class QrCode {

    private BufferedImage qrCode;
    private int centreHeight;
    private int centreWidth;

    /**
     * @param qrCodeDetails Details of QrCode
     */
    public QrCode(String qrCodeDetails) {
        try {
            ByteArrayOutputStream stream = QRCode
                    .from(qrCodeDetails)
                    .withSize(200, 200)
                    .withColor(Color.BLACK.getRGB(),Color.TRANSLUCENT)
                    .stream();
            ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

            BufferedImage qrBackground = ImageIO.read(new File("src/main/resources/qr_small_white.png"));
            BufferedImage qrCode = ImageIO.read(bis);

            Graphics g = qrBackground.getGraphics();
            g.drawImage(
                    qrCode,
                    qrBackground.getWidth()/2 - qrCode.getWidth()/2,
                    qrBackground.getHeight()/2 - qrCode.getWidth()/2,
                    null
            );

            this.qrCode = qrBackground;
            centreHeight = qrBackground.getHeight() / 2;
            centreWidth = qrBackground.getWidth() / 2;


        } catch (Exception e) {
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
