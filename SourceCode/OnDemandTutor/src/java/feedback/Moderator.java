/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedback;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Moderator {
    private static final int[] MODERATOR_IDS = {1, 2, 3, 4, 5};

    public static int getRandomModeratorId() {
        Random random = new Random();
        int index = random.nextInt(MODERATOR_IDS.length);
        return MODERATOR_IDS[index];
    }
}
