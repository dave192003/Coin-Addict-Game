// ✅ Cell class

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Cell extends JPanel {

    private int row, column;
    private boolean isWall;
    private boolean visited;
    private Image rockWithCoinImage;

    public Cell(int row, int column) {
        java.net.URL imageURL = getClass().getResource("/Image/coin.gif");
        if (imageURL == null) {
            System.out.println("Could not find: /image/Lava.png");
            rockWithCoinImage = null;
        } else {
            rockWithCoinImage = new ImageIcon(imageURL).getImage();
            System.out.println(" Wall image loaded: " + imageURL);
        }

        this.row = row;
        this.column = column;
        this.isWall = false;
        this.visited = false;
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(40, 40));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(rockWithCoinImage, 0, 0, getWidth(), getHeight(), this);

    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getLocationRow() {
        return row + column;
    }

    public void setImageURL(URL imageURL) {
        URL newImageURL = imageURL;
        Image rockImage = new ImageIcon(newImageURL).getImage();
        this.rockWithCoinImage = rockImage;
        repaint();

    }
}
