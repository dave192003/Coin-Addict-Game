
import java.awt.*;
import javax.swing.*;

public class LavaCell extends JPanel {

    private final Image wallImage;

    public LavaCell(int row, int col) {
        java.net.URL imageUrl = getClass().getResource("/image/Lava.png");
        if (imageUrl == null) {
            System.out.println("Could not find: /image/Lava.png");
            wallImage = null;
        } else {
            wallImage = new ImageIcon(imageUrl).getImage();
            System.out.println("Wall image loaded: " + imageUrl);
        }
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(40, 40));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (wallImage != null) {
            g.drawImage(wallImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
