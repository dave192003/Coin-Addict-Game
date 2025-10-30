
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class GameTimer extends JPanel {

    private JLabel timerLabel;
    private int timeLeft = 30;
    private Timer timer;
    private boolean isCountingUpTimer;
    private Image image;

    public GameTimer() {

        setLayout(new FlowLayout());
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        timerLabel = new JLabel("Time: 0 s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setOpaque(false);
        timerLabel.setBackground(new Color(0, 0, 0, 0));
        add(timerLabel);

        setVisible(true);
    }

    public void makeTimer(int seconds) {
        timer = new Timer(seconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft + " s");

                if (timeLeft <= 0) {
                    System.exit(0);
                    timer.stop();
                }
            }
        });

    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void addTime(int addedTime) {
        timeLeft += addedTime;
    }

    public void setTime(int delayInMillis, int endTime, boolean isCountingUpTimer) {
        if (isCountingUpTimer == true) {
            timer = new Timer(delayInMillis, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft++;
                    timerLabel.setText("Time: " + timeLeft + " s");

                    if (timeLeft == endTime) {
                        timer.stop();
                        System.exit(0);
                    }
                }
            });

        } else {
            timer = new Timer(delayInMillis, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft--;
                    timerLabel.setText("Time: " + timeLeft + " s");

                    if (timeLeft <= 0) {
                        timer.stop();
                    }
                }
            });

        }
    }

    //set background image
    public void setBackgroundImage(String filename) {
        URL bgPath = getClass().getResource(filename);
        if (bgPath == null) {
            System.out.println("Cannot find the image " + filename);
            image = null;

        } else {
            ImageIcon icon = new ImageIcon(bgPath);
            image = new ImageIcon(bgPath).getImage();

        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}
