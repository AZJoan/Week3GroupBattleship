package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int TARGETS = 5;
    private static int GAME_WIDTH = 6;
    private static int GAME_HEIGHT = 5;

    private static char[][] grid = new char[GAME_WIDTH][GAME_HEIGHT];
    private static char[][] targets = new char[GAME_WIDTH][GAME_HEIGHT];
    private static int hitsLeft = TARGETS;
    private static int guesses = 0;

    public static void main(String[] args) {
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_HEIGHT; j++) {
                grid[i][j] = '0';
            }
        }

        assignHits(TARGETS);
        printMap();

        while (hitsLeft > 0) {
            promptUser();
            printMap();
        }

        System.out.println("You win!");
        System.out.println("Total Guesses: " + guesses);
    }

    public static void assignHits(int numHits) {
        Random random = new Random();
        for (int i = 0; i < numHits; i++) {
            int randomX = random.nextInt(GAME_WIDTH);
            int randomY = random.nextInt(GAME_HEIGHT);
            targets[randomX][randomY] = 'Y';
        }
    }

    public static void printMap() {
        for (int i = 0; i < GAME_HEIGHT; i++) { //Y positions
            for (int j = 0; j < GAME_WIDTH; j++) { //X positions
                System.out.print(grid[j][i] + " "); //Print the row
            }
            System.out.println(""); //Move to next column
        }
    }

    public static void promptUser() {
        int xGuess;
        int yGuess;
        try {
            xGuess = Integer.parseInt(getInput("Enter an X guess: "));
        } catch (Exception err) {
            xGuess = -1;
        }

        try {
            yGuess = Integer.parseInt(getInput("Enter an Y guess: "));
        } catch (Exception err) {
            yGuess = -1;
        }

        try {
            if (targets[xGuess][yGuess] == 'Y') {
                grid[xGuess][yGuess] = 'H';
                targets[xGuess][yGuess] = 'O';
                System.out.println("Hit!");
                hitsLeft--;
                guesses++;
            } else if (grid[xGuess][yGuess] == 'M') {
                System.out.println("You already guessed that space! Guess again.");
            } else {
                grid[xGuess][yGuess] = 'M';
                System.out.println("Miss!");
                guesses++;
            }
        } catch (Exception err) {
            System.out.println("You entered a position that does not exist! Try again.");
        }

        System.out.println("Targets left: " + hitsLeft + " Total Guesses: " + guesses);
    }

    public static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }
}
