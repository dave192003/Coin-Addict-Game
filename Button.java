
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {

    private URL imageURL;
    private Image image;
    private int width, height;

    public Button() {
        setBackground(new Color(0, 0, 0, 0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setVisible(true);

    }

    public void setButtonSizeAndLocation(int x, int y, int buttonWidth, int buttonHeight) {
        setBounds(x, y, buttonWidth, buttonHeight);
        width = buttonWidth;
        height = buttonHeight;
    }

    public void setBackgroundButton(String imageUrl) {
        imageURL = getClass().getResource(imageUrl);
        if (imageURL != null) {
            System.err.println("Image  found: " + imageUrl);

            ImageIcon icon = new ImageIcon(imageURL);
            image = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
            setIcon(new ImageIcon(image));
            repaint();
        } else {
            System.err.println("Image not found: " + imageUrl);
        }
    }

}
