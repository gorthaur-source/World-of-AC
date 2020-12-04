package org.academiadecodigo.tailormoons.world_of_ac_client;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int counter = 0;

        String index = args[0];
        int maxCounter = Integer.parseInt(index);

        for (int i = 0; i < maxCounter; i++) {
            counter++;
        }

    }

}
