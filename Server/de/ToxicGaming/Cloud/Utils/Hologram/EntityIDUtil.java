package de.ToxicGaming.Cloud.Utils.Hologram;

import java.util.Random;

public class EntityIDUtil {
	 private static Random random = new Random(  );
	    public static int getRandomEntityID() {
	        return random.nextInt( 100000 );
	   }
}
