package by.gurinovich.travelcompanionsearch.util;

import java.util.Random;

public class RandomCodeGenerator {

    public static String generateFourNumberCode(){
        Random random = new Random();
        return String.valueOf(random.nextInt(1000, 8999));
    }
}
