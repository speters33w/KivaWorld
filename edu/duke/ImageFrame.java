package edu.duke;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * This utility class implements a panel for displaying an image.
 * 
 * @author Duke Software Team
 * 
 *         This software is licensed with an Apache 2 license, see
 *         http://www.apache.org/licenses/LICENSE-2.0 for details.
 */
class ImageFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private ImagePanel myPanel;

    /**
     * Creates an ImageFrame.
     *
     * @param fileName The fileName to title the JFrame
     * @param image The image to be shown in the JFrame
     */
    public ImageFrame (String fileName, Image image) {
        setTitle(fileName);
        // setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Displays an image in this frame.
     *
     * @param image The image to be displayed.
     */
    void show (Image image) {
        if (myPanel == null) {
            myPanel = new ImagePanel(image, image.getWidth(this), image.getHeight(this));
            Container c = getContentPane();
            c.setBackground(Color.WHITE);
            c.add(myPanel);
            pack();
        } else {
            myPanel.setImage(image, image.getWidth(this), image.getHeight(this));
        }
        setVisible(true);
    }


    // This class implements a panel for displaying an image.
    static class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image myImage;

        /**
         * Creates an ImagePanel.
         *
         * @param image the image to be displayed
         * @param width integer width of the image
         * @param height integer height of the image
         */
        public ImagePanel (Image image, int width, int height) {
            setBackground(Color.white);
            setPreferredSize(new Dimension(width, height));
            myImage = image;
        }

        /**
         * Resets the image and redraws it in the panel.
         *
         * @param image the image to be displayed
         * @param width integer width of the image
         * @param height integer height of the image
         */
        public void setImage (Image image, int width, int height) {
            this.myImage = image;
            setPreferredSize(new Dimension(width, height));
            repaint();
        }

        /**
         * Draws the image in the panel if the image is not null.
         */
        @Override
        public void paintComponent (Graphics g) {
            super.paintComponent(g);
            if (myImage != null) {
                g.drawImage(myImage, 0, 0, Color.white, this);
            }
        }
    }
}
