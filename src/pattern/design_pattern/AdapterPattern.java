package pattern.design_pattern;

/**
 * 适配器模式
 * 参考：
 * http://www.runoob.com/design-pattern/adapter-pattern.html
 */
public class AdapterPattern {

    public interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    public interface AdvancedMediaPlayer {
        void playVlc(String fileName);

        void playMp4(String fileName);
    }

    public static class VlcPlayer implements AdvancedMediaPlayer {

        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }

        @Override
        public void playMp4(String fileName) {

        }
    }

    public static class Mp4Player implements AdvancedMediaPlayer {

        @Override
        public void playVlc(String fileName) {

        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    public static class MediaAdapter implements MediaPlayer {

        AdvancedMediaPlayer mediaPlayer;

        public MediaAdapter(String audioType) {
            if (audioType.equals("Vlc")) {
                mediaPlayer = new VlcPlayer();
            } else if (audioType.equals("Mp4")) {
                mediaPlayer = new Mp4Player();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equals("Vlc")) {
                mediaPlayer.playVlc(fileName);
            } else if (audioType.equals("Mp4")) {
                mediaPlayer.playMp4(fileName);
            }
        }
    }

    public static class AudioPlayer implements MediaPlayer {

        MediaAdapter adapter;

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equals("mp3")) {
                System.out.println("Playing mp3 file. Name: " + fileName);
            } else if (audioType.equals("Vlc") || audioType.equals("Mp4")) {
                adapter = new MediaAdapter(audioType);
                adapter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media. " + audioType + " format not supported");
            }
        }
    }

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("Mp4", "alone.mp4");
        audioPlayer.play("Vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
