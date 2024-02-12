package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        /* create guitar strings, for 110 and 880 */
        int len = 37;
        GuitarString[] GuitarStrings = new GuitarString[len];
        for (int i = 0; i < len; i += 1) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            GuitarStrings[i] = new GuitarString(frequency);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    continue;
                }

                GuitarStrings[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString s : GuitarStrings) {
                sample += s.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString s : GuitarStrings) {
                s.tic();
            }
        }
    }
}


