
import java.awt.*;
import javax.swing.*;

public class LavaCell extends JPanel {

    private final Image wallImage;

    public LavaCell(int row, int col) {
        java.net.URL imageUrl = getClass().getResource("/image/lavaGIF.gif");
        if (imageUrl == null) {
            System.out.println("Could not find: /image/lavaGIF.gif");
            wallImage = null;
        } else {
            wallImage = new ImageIcon(imageUrl).getImage();
            System.out.println("Wall image loaded: " + imageUrl);
        }
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(50, 50));

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

    public boolean setWalkable(boolean isWalkable) {
        return true;
    }
}
