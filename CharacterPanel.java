
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CharacterPanel extends JPanel {

    private Image characterGIF;

    public CharacterPanel() {
        java.net.URL maleGIF = getClass().getResource("/image/Male_run.gif");
        if (maleGIF == null) {
            System.out.println("Cannot find GIF file ");
            characterGIF = null;
        } else {

            characterGIF = new ImageIcon(maleGIF).getImage();
        }

        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(characterGIF, 0, 0, getWidth(), getHeight(), this);
    }

}
