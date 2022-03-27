import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import java.util.UUID;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {
        try {

            //uuid
            String uuidString = UUID.randomUUID().toString();
            Scanner input = new Scanner(System.in);
            System.out.println("Name of guest: ");
            String nameString = input.nextLine();
            QrCode qrCode = new QrCode("Name: "+nameString+"\n"+"Unique ID: "+uuidString);
            BackgroundImage backgroundImage = new BackgroundImage("src/main/resources/white_square.png");


            Graphics backgroundImageGraphics = backgroundImage.getBackgroundImage().getGraphics();

            backgroundImageGraphics.drawImage(qrCode.getQrCodeImage(), backgroundImage.getCentreWidth() - qrCode.getCentreWidth(), backgroundImage.getCentreHeight() - qrCode.getCentreHeight(), null);
            Font font = new Font("Serif", Font.PLAIN, 12);

            JLabel name = new JLabel(nameString,SwingConstants.CENTER);
            JLabel uuid = new JLabel(uuidString, SwingConstants.CENTER);
            uuid.setForeground(Color.BLACK);
            name.setForeground(Color.BLACK);
            //uuid.setLocation(backgroundImage.getCentreWidth() - uuid.getWidth(), (backgroundImage.getBackgroundImage().getHeight() * 9) / 10);

            JLabel picLabel = new JLabel(new ImageIcon(backgroundImage.getBackgroundImage()));
            picLabel.setLayout(new BorderLayout());
            //JPanel jPanel = new JPanel();
            uuid.setBorder(new EmptyBorder(0,0,backgroundImage.getBackgroundImage().getHeight()/10,0));
            name.setBorder(new EmptyBorder(backgroundImage.getBackgroundImage().getHeight()/10,0,0,0));

            picLabel.add(name,BorderLayout.NORTH);
            picLabel.add(uuid, BorderLayout.SOUTH);
            //jPanel.setLayout(null);
            //jPanel.add(picLabel);
            //jPanel.add(uuid);

            JFrame f = new JFrame();
            f.setLayout(new GridBagLayout()); //Essential
            f.add(picLabel);
            f.setSize(new Dimension(backgroundImage.getBackgroundImage().getWidth(), backgroundImage.getBackgroundImage().getHeight()));
            f.pack();
            f.setDefaultCloseOperation(EXIT_ON_CLOSE);
            f.setVisible(true);

            Container c = f.getContentPane();
            BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            c.paint(im.getGraphics());
            ImageIO.write(im, "PNG", new File("C:\\Users\\tifeb\\Desktop\\lol.png"));

        } catch (Exception ignored) {//Do proper Error checking

        }
    }
}
