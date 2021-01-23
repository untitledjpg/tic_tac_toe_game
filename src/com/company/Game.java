package com.company;

import java.util.Scanner;

public class Game {

    private char[][] gameBoard = new char[][]{{' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

    private char playerSymbol = 'x';

    public void printBoard() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public void playerChange() {                            // changes player after each turn
        if (playerSymbol == 'x') {
            playerSymbol = 'o';
        } else {
            playerSymbol = 'x';
        }
    }

    public boolean placeSymbol(int userInput) {            // places symbol in an empty space according to user choice
        int x = 0;
        int y = 0;
        switch (userInput) {
            case 1:
                x = 0;
                y = 0;
                break;
            case 2:
                x = 0;
                y = 2;
                break;
            case 3:
                x = 0;
                y = 4;
                break;
            case 4:
                x = 2;
                y = 0;
                break;
            case 5:
                x = 2;
                y = 2;
                break;
            case 6:
                x = 2;
                y = 4;
                break;
            case 7:
                x = 4;
                y = 0;
                break;
            case 8:
                x = 4;
                y = 2;
                break;
            case 9:
                x = 4;
                y = 4;
                break;
        }
        if (gameBoard[x][y] == ' ') {
            gameBoard[x][y] = playerSymbol;
            return true;
        }
        return false;
    }

    public boolean isLine(char a, char b, char c) {      //checks if all three characters in line are the same
        if ((a != ' ') && (a == b) && (a == c)) {
            return true;
        }
        return false;
    }

    public boolean checkRows() {                        // checks all the rows for a line with equal characters
        for (int i = 0; i < 5; i = i + 2) {
            if (isLine(gameBoard[i][0], gameBoard[i][2], gameBoard[i][4])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkColumns() {                     // checks all the columns for a line with equal characters
        for (int i = 0; i < 5; i = i + 2) {
            if (isLine(gameBoard[0][i], gameBoard[2][i], gameBoard[4][i])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonal() {                    // checks all the diagonals for a line with equal characters
        if (isLine(gameBoard[0][0], gameBoard[2][2], gameBoard[4][4])
                || isLine(gameBoard[0][4], gameBoard[2][2], gameBoard[4][0])) {
            return true;
        }
        return false;
    }

    public boolean checkForVictory() {                  // checks, if there is a winner
        if (checkColumns() || checkRows() || checkDiagonal())
            return true;
        return false;
    }

    public boolean checkIfFull() {                      // checks the board if it is completely full
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public void play() {                               // "engine" of the game
        printBoard();

        int userInput;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter place where you want to place your mark (1-9)");
            userInput = scan.nextInt();

            if (placeSymbol(userInput)) {
                printBoard();
                if (checkForVictory()) {
                    System.out.println("Game is over! Player '" + playerSymbol + "' wins.");
                } else if (checkIfFull()) {
                    System.out.println("It's a tie!");
                }
                playerChange();

            } else {
                System.out.println("The place is taken. Please choose empty one.");
            }
        }
        while (!checkForVictory() || !checkIfFull());       // loop breaks if there is winner or the board is full
    }
}
