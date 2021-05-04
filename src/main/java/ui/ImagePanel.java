package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

class ImagePanel extends JPanel {

    private Image image;

    ImagePanel(String fileName) {
        try {
            image = read(getClass().getResourceAsStream("/image/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
    }
}
