
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MapGrid::new);

        SoundPlayer.loopSound("sounds/gameMusic.wav");

    }

}
