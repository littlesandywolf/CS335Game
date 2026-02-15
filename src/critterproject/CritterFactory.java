package critterproject;
import java.util.Random;
public class CritterFactory {
	private static final Random rand = new Random();

    public static int[] generateGenes() {
        int[] genes = new int[10];
        int remaining = 50;

        while (remaining > 0) {
            int idx = rand.nextInt(10);
            if (genes[idx] < 10) {
                genes[idx]++;
                remaining--;
            }
        }
        return genes;
    }
}
