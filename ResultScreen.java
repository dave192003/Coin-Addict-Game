
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ResultScreen extends JPanel {

    private Image image;
    private Button retryButton = new Button();

    public ResultScreen() {
        setSize(745, 785);
        setLayout(null);

        setVisible(true);
        setBackground(new Color(0, 0, 0, 200));
        setOpaque(true);

        //Retry button
        retryButton.setButtonSizeAndLocation(260, 480, 200, 60);
        retryButton.setBackgroundButton("/image/Retry (2).png");
        add(retryButton);

        //retry button action listner
        retryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MapGrid newMapGrid = new MapGrid();

            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

    }

    public void setImageURL(String URL) {
        URL imageURL = getClass().getResource(URL);
        image = new ImageIcon(imageURL).getImage();

    }

    public void setImageCenter(int layeredPaneWidth, int layeredPaneHeight, int panelWidth, int panelHeight) {
        int x = (layeredPaneWidth - panelWidth) / 2;
        int y = (layeredPaneHeight - panelHeight) / 2;

        setLocation(x, y);

    }

}
