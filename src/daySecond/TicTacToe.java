package daySecond;

import java.util.Arrays;

public class TicTacToe {
    private String playerFirst;
    private String playerSecond;
    private char field[][] = new char[3][3];
    private boolean nextMoveFirst = true;

    public TicTacToe(String playerFirst, String playerSecond) {
        this.playerFirst = playerFirst;
        this.playerSecond = playerSecond;
    }

    public String getPlayerFirst() {
        return playerFirst;
    }

    public String getPlayerSecond() {
        return playerSecond;
    }

    public boolean isNextMoveFirst() {
        return nextMoveFirst;
    }

    public void setNextMove(String line) {
        String[] move = line.split(",");
        if (move.length != 2) {
            System.out.println("Ошибка формата ввода координат. " +
                    "Повторите попытку. " +
                    "Количество координат должно быть 2, например, 0,1. " +
                    "Вы ввели: " + move.length);
        }
        try {
            int xCoordinate = Integer.parseInt(move[0]);
            int yCoordinate = Integer.parseInt(move[1]);

            if ((xCoordinate > 3 || xCoordinate < 1) && (yCoordinate > 3 || yCoordinate < 1)) {
                System.out.println("Координаты указаны вне диапазона 1 <= x,y <= 3. " +
                        "Повторите попытку.");
            } else if (field[field.length - yCoordinate][xCoordinate - 1] != 0) {
                System.out.println("Поле уже занято. Повторите попытку.");
            } else {
                field[field.length - yCoordinate][xCoordinate - 1] = isNextMoveFirst() ? 'X' : 'O';
                nextMoveFirst = !nextMoveFirst;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка формата ввода координат. " +
                    "Повторите попытку. " +
                    "Вы ввели координаты, которые нельзя преобразовать в число. " +
                    "Повторите попытку.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("| ");

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                sb.append(field[i][j] == 0 ? "-" : field[i][j])
                        .append(" | ");
            }
            if (i < 2) {
                sb.append("\n")
                        .append("| ");
            }
        }

        return sb.toString();
    }

    public boolean checkGame(char symbol) {
        int[] sum = new int[8]; //для поля 3x3: 3 строки + 3 столбца + 2 диагонали

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == symbol) {
                    sum[i] = sum[i] + 1;
                    sum[3 + j] = sum[3 + j] + 1;
                }
            }
        }
        if (field[0][0] == symbol) {
            sum[6] = sum[6] + 1;
        }
        if (field[1][1] == symbol) {
            sum[6] = sum[6] + 1;
            sum[7] = sum[7] + 1;
        }
        if (field[2][2] == symbol) {
            sum[6] = sum[6] + 1;
        }
        if (field[2][0] == symbol) {
            sum[7] = sum[7] + 1;
        }
        if (field[0][2] == symbol) {
            sum[7] = sum[7] + 1;
        }

        int max = Arrays.stream(sum).max().getAsInt();

        return max > 2 ? true : false;
    }

    public boolean fieldIsEmpty() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
