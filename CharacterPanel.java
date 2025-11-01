
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CharacterPanel extends JPanel {

    private Image characterGIF;
    private int row, column;

    public CharacterPanel(String gifPath) {
        java.net.URL maleGIF = getClass().getResource(gifPath);
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

    public int getRow() {

        return row;
    }

    public int getColumn() {

        return column;
    }

    public void setCharacterGIFMode(String gifPath) {

        GameTimer changeMode = new GameTimer();
        boolean toChange = changeMode.setTime(1000, 1000, false);
        System.out.println("The redult is " + toChange);
        if (toChange == false) {
            this.characterGIF = new ImageIcon(gifPath).getImage();

        } else {
            this.characterGIF = new ImageIcon("image/Male_idle.gif").getImage();
        }

    }

}
