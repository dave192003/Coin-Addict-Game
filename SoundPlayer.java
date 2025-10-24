
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

    private static Clip Offclip;

    public static void playSound(String soundFile) {
        try {
            File soundPath = new File(soundFile
            );
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundPath);
            Clip mainClip = AudioSystem.getClip();

            mainClip.open(audioStream);
            mainClip.start();

            if (soundFile.equals("sounds/gameMusic.wav")) {
                Offclip = mainClip;
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();

        }

    }

    public static void loopSound(String loopSoundFile) {
        try {
            File loopSoundPath = new File(loopSoundFile);
            AudioInputStream loopAudioStream = AudioSystem.getAudioInputStream(loopSoundPath);
            Clip loopclip = AudioSystem.getClip();
            loopclip.open(loopAudioStream);
            loopclip.start();
            loopclip.loop(Clip.LOOP_CONTINUOUSLY);

            if (loopSoundFile.equals("sounds/gameMusic.wav")) {
                Offclip = loopclip;
            }
        } catch (Exception e) {
        }
    }

    public static void offSound() {
        try {

            getOffClip().stop();

        } catch (Exception e) {
        }
    }

    public static Clip getOffClip() {
        return Offclip;
    }

}
