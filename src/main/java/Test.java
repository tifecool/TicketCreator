import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test {

    public static void main(String[] args) {
        try {
            QrCode qrCode = new QrCode("C:\\Users\\tifeb\\Desktop\\Work Related\\Ticket_Creator\\src\\main\\resources\\smaller_black_square.png");
            BackgroundImage backgroundImage = new BackgroundImage("C:\\Users\\tifeb\\Desktop\\Work Related\\Ticket_Creator\\src\\main\\resources\\white_square.png");


            Graphics backgroundImageGraphics = backgroundImage.getBackgroundImage().getGraphics();

            backgroundImageGraphics.drawImage(qrCode.getQrCodeImage(), backgroundImage.getCentreWidth()- qrCode.getCentreWidth(), backgroundImage.getCentreHeight()- qrCode.getCentreHeight(), null);

            JLabel picLabel = new JLabel(new ImageIcon(backgroundImage.getBackgroundImage()));
            JPanel jPanel = new JPanel();
            jPanel.add(picLabel);

            JFrame f = new JFrame();
            f.setSize(new Dimension(backgroundImage.getBackgroundImage().getWidth(), backgroundImage.getBackgroundImage().getHeight()));
            f.add(jPanel);
            f.setVisible(true);

        } catch (Exception ignored) {//Do proper Error checking

        }
    }
}

