import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

public class RandomIMAGE extends JFrame {
    JFrame frame;
    JLabel label;
    JButton nextBUTTON;
    JButton previousBUTTON;
    JButton saveBUTTON;
    JFileChooser jfc;
    int retVal;
    int currentImage;

    ArrayList<Picture> imageLists = new ArrayList<>();

    public RandomIMAGE() throws Exception {

        LinkedList<Object> krasa = new LinkedList<Object>();
        krasa.add(0.3);
        krasa.add(0.3);
        krasa.add(new ColorUIResource(128, 128, 128));
        krasa.add(new ColorUIResource(192, 192, 192));
        krasa.add(new ColorUIResource(220, 220, 220));
        UIManager buttonGRADIENT = new UIManager();
        buttonGRADIENT.put("Button.gradient", krasa);
        try {
            String path = "https://source.unsplash.com/random/640x480";
            System.out.println("Getting random image from - " + path);
            System.out.println("Loading image in to frame...");
            URL url = new URL(path);
            BufferedImage image = ImageIO.read(url);

            System.out.println("Image Loaded successfully!");

            label = new JLabel(new ImageIcon(image));
            frame = new JFrame();
            frame.setPreferredSize(new Dimension(640, 600));
            label.setPreferredSize(new Dimension(300, 300));
            JTextField field = new JTextField();
            field.setText("Made by Adrian, IT1 2018.");
            field.setEditable(false);
            field.setSize(640, 50);
            field.setLocation(0, 520);
            frame.add(field);


            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setLocation(300, 200);
            frame.setAlwaysOnTop(false);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLayout(null);

            previousBUTTON = new JButton("Previous image");
            previousBUTTON.setBounds(10, 30, 50, 200);
            previousBUTTON.setSize(208, 40);
            previousBUTTON.setLocation(0, 0);
            frame.add(previousBUTTON);

            saveBUTTON = new JButton("Save image");
            saveBUTTON.setBounds(10, 30, 50, 200);
            saveBUTTON.setSize(208, 40);
            saveBUTTON.setLocation(208, 0);
            frame.add(saveBUTTON);

            nextBUTTON = new JButton("Next Image");
            nextBUTTON.setBounds(10, 30, 50, 200);
            nextBUTTON.setSize(208, 40);
            nextBUTTON.setLocation(416, 0);
            frame.add(nextBUTTON);

        } catch (Exception e) {
            System.err.println("No connection with the internet!");
        }

        previousBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    currentImage -= 1;
                    ImageIcon image = new ImageIcon(imageLists.get(currentImage).getPicture());
                    label.setIcon(image);
                    frame.getContentPane().add(label);
                    System.out.println("Previous image Loaded!");

                } catch (Exception el) {
                    System.out.println("No image found.");
                }
            }
        });
        saveBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.err.println("---> !!!! REMEMBER TO PUT FILE TYPE .png AT THE END OF THE FILE NAME. --> Example --> Example.png <--");
                    BufferedImage img = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_RGB);
                    label.paint(img.getGraphics());
                    jfc = new JFileChooser();
                    retVal = jfc.showSaveDialog(null);
                    if (retVal == JFileChooser.APPROVE_OPTION) {
                        File f = jfc.getSelectedFile();
                        String test = f.getAbsolutePath();
                        ImageIO.write((img), "png", new File(test));
                        System.out.println("Image saved successfully.");
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        });
        nextBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "https://source.unsplash.com/random/640x480";
                try {
                    Picture pic = getImageFromUnsplash(path);
                    ImageIcon image = new ImageIcon(pic.getPicture());
                    label.setIcon(image);
                    frame.getContentPane().add(label);
                    System.out.println("New image loaded successfully!");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public Picture getImageFromUnsplash(String path) throws IOException {
        URL imageURL = new URL(path);
        URLConnection connection = imageURL.openConnection();
        connection.connect();
        InputStream is = connection.getInputStream();
        BufferedImage picture = ImageIO.read(is);
        Image picture2 = picture;
        System.out.println(connection.getURL());
        Picture something = new Picture(picture2, connection.getURL());
        imageLists.add(something);
        currentImage = imageLists.size() - 1;
        return something;
    }


}
