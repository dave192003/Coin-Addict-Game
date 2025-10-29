
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameTimer extends JPanel {

    private JLabel timerLabel;
    private int timeLeft = 30;
    private Timer timer;
    private boolean isCountingUpTimer;

    public GameTimer() {

        setLayout(new FlowLayout());

        timerLabel = new JLabel("Time: 0 s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 15));
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

}
