import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static final int VARIANT_ONE = 331;
    public static final int VARIANT_TWO = 840;

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            //Pick variant
            int variant;
            while(true){
                System.out.println("Variant type (1 or 2): ");
                variant = Integer.parseInt(input.nextLine());

                if(variant == 1){
                    variant = VARIANT_ONE;
                    break;
                }else if(variant == 2){
                    variant = VARIANT_TWO;
                    break;
                }

                System.out.println("Enter either 1 or 2.");
            }

            //uuid
            String uuidString = UUID.randomUUID().toString();
            System.out.println("Name of guest: ");
            String nameString = input.nextLine();
            QrCode qrCode = new QrCode("Name: " + nameString + "\n" + "Unique ID: " + uuidString);
            BackgroundImage backgroundImage = new BackgroundImage(variant == VARIANT_ONE ? "src/main/resources/background1.jpg": "src/main/resources/background2.png");


            Graphics backgroundImageGraphics = backgroundImage.getBackgroundImage().getGraphics();

            backgroundImageGraphics.drawImage(qrCode.getQrCodeImage(), backgroundImage.getCentreWidth() - qrCode.getCentreWidth(), backgroundImage.getCentreHeight() - qrCode.getCentreHeight(), null);

            JLabel name = new JLabel(nameString, SwingConstants.CENTER);
            JLabel uuid = new JLabel(uuidString, SwingConstants.CENTER);
            uuid.setForeground(Color.BLACK);
            uuid.setFont(uuid.getFont().deriveFont(14f));
            name.setForeground(Color.BLACK);
            name.setFont(name.getFont().deriveFont(14f));

            JLabel picLabel = new JLabel(new ImageIcon(backgroundImage.getBackgroundImage()));
            picLabel.setLayout(new BorderLayout());
            uuid.setBorder(new EmptyBorder(0, 0, backgroundImage.getBackgroundImage().getHeight() / 10, 0));
            name.setBorder(new EmptyBorder(backgroundImage.getBackgroundImage().getHeight() / 10, 0, 0, 0));

            picLabel.add(name, BorderLayout.NORTH);
            picLabel.add(uuid, BorderLayout.SOUTH);

            JFrame f = new JFrame();
            f.setLayout(new GridBagLayout());
            f.add(picLabel);
            f.setSize(new Dimension(backgroundImage.getBackgroundImage().getWidth(), backgroundImage.getBackgroundImage().getHeight()));
            f.setResizable(false);
            f.pack();
            f.setDefaultCloseOperation(EXIT_ON_CLOSE);
            f.setVisible(true);

            Container c = f.getContentPane();
            BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            c.print(im.getGraphics());
            //Todo: Allow selection of directory and save location in memory
            ImageIO.write(im, "PNG", new File("C:\\Users\\tifeb\\Desktop\\Tickets\\" + nameString + " Ticket.png"));

            try(FileWriter fw = new FileWriter(new File("C:\\Users\\tifeb\\Desktop\\Tickets\\","list.txt"),true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(nameString + ": " + uuidString);
            } catch (Exception e) {
                //Todo: Do proper error checking
            }

        } catch (Exception ignored) {//Todo: Do proper Error checking

        }
    }
}

