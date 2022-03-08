package daySecond;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, Ylab!");
        System.out.println("Tic-tac-toe game.");

        boolean gameIsStart = false;
        TicTacToe ticTacToe = null;

        try (Scanner scanner = new Scanner(System.in);
             OutputStream  output = Files.newOutputStream(Path.of("c:\\tmp\\TicTacToeGame.txt"));) {

            output.write("Начало игры.\n".getBytes());
            while (true) {
                if (!gameIsStart) {
                    System.out.println("Завершить игру у/n?");
                    if (scanner.nextLine().equals("y")) {
                        System.exit(0);
                    }
                    System.out.println("Введите имя первого игрока.");
                    String firstPlayer = scanner.nextLine();
                    output.write(("Игрок 1: " + firstPlayer + "\n").getBytes());

                    System.out.println("Введите имя второго игрока.");
                    String secondPlayer = scanner.nextLine();
                    output.write(("Игрок 1: " + secondPlayer + "\n").getBytes());

                    ticTacToe = new TicTacToe(firstPlayer, secondPlayer);
                    gameIsStart = true;
                }
                System.out.println("Ход игрока "
                        + (ticTacToe.isNextMoveFirst() ? ticTacToe.getPlayerFirst() : ticTacToe.getPlayerSecond())
                        + ". Введите координаты поля в формате x,y. Где 1 <= x,y <= 3.");
                System.out.println(ticTacToe.toString());
                ticTacToe.setNextMove(scanner.nextLine());
                System.out.println(ticTacToe.toString());
                if (ticTacToe.checkGame('X')) {
                    String text = "Победил игрок 1: " + ticTacToe.getPlayerFirst() + "!\n";
                    System.out.println(text);
                    gameIsStart = false;
                    output.write(text.getBytes());
                }
                if (ticTacToe.checkGame('O')) {
                    String text = "Победил игрок 2: " + ticTacToe.getPlayerSecond() + "!\n";
                    System.out.println(text);
                    gameIsStart = false;
                    output.write(text.getBytes());
                }
                if (gameIsStart && !ticTacToe.fieldIsEmpty()) {
                    String text = "Ничья!\n";
                    System.out.println(text);
                    gameIsStart = false;
                    output.write(text.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
