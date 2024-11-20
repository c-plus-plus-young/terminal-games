import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class runTests {
    public static void runAllTests() {
        long smartTestBefore = System.nanoTime();
        tests();
        long smartTestAfter = System.nanoTime();
        System.out.println();

        long dumbTestBefore = System.nanoTime();
        dumbTests();
        long dumbTestAfter = System.nanoTime();
        System.out.println();

        long smartGameBefore = System.nanoTime();
        playGame();
        long smartGameAfter = System.nanoTime();
        System.out.println();

        long dumbGameBefore = System.nanoTime();
        playDumbGame();
        long dumbGameAfter = System.nanoTime();
        System.out.println();

        playRandomGame();
        long rigorousTestBefore = System.nanoTime();
        rigorousTest();
        long rigorousTestAfter = System.nanoTime();

        long dumbRigorousTestBefore = System.nanoTime();
        dumbRigorousTest();
        long dumbRigorousTestAfter = System.nanoTime();

        long rigorousGameBefore = System.nanoTime();
        rigorousGame();
        long rigorousGameAfter = System.nanoTime();

        long dumbRigorousGameBefore = System.nanoTime();
        dumbRigorousGame();
        long dumbRigorousGameAfter = System.nanoTime();

        System.out.println("Tests with smart Union/Find took " + ((smartTestAfter - smartTestBefore) / 1000000000.0) + " seconds.");
        System.out.println("Tests with dumb Union/Find took " + ((dumbTestAfter - dumbTestBefore) / 1000000000.0) + " seconds.");
        System.out.println("Game with smart Union/Find took " + ((smartGameAfter - smartGameBefore) / 1000000000.0) + " seconds.");
        System.out.println("Game with dumb Union/Find took " + ((dumbGameAfter - dumbGameBefore) / 1000000000.0) + " seconds.");
        System.out.println("Rigorous Tests with smart Union/Find took " + ((rigorousTestAfter - rigorousTestBefore) / 1000000000.0) + " seconds.");
        System.out.println("Rigorous Tests with dumb Union/Find took " + ((dumbRigorousTestAfter - dumbRigorousTestBefore) / 1000000000.0) + " seconds.");
        System.out.println("Rigorous game with smart Union/Find took " + ((rigorousGameAfter - rigorousGameBefore) / 1000000000.0) + " seconds.");
        System.out.println("Rigorous game with dumb Union/Find took " + ((dumbRigorousGameAfter - dumbRigorousGameBefore) / 1000000000.0) + " seconds.");
    }

    public static void tests() {
        //testing to ensure upTree is working.
        upTree trees = new upTree(100);
        for (int i = 0; i < 50; i++) {
            trees.union(1, i);
        }
        trees.union(50, 51);
        trees.union(51, 52);
        trees.union(52, 23);
        for (int i = 0; i < 50; i++) {
            trees.find(i);
        }
        trees.printTree();
    }

    public static void dumbTests() {
        //testing to ensure upTree is working.
        upTree trees = new upTree(100);
        for (int i = 0; i < 50; i++) {
            trees.dumbUnion(1, i);
        }
        trees.dumbUnion(50, 51);
        trees.dumbUnion(51, 52);
        trees.dumbUnion(52, 23);
        for (int i = 0; i < 50; i++) {
            trees.dumbFind(i);
        }
        trees.printTree();
    }

    // a test with a larger data set to see if the smart union/find is really all that useful
    public static void rigorousTest() {
        upTree trees = new upTree(100000000);
        for (int i = 0; i < (trees.size - 1); i++) {
            trees.union((int)(Math.random() * 99999999), i);
        }
        for (int i = 0; i < 100000000; i++) {
            trees.find((int)(Math.random() * 99999999));
        }
    }

    public static void dumbRigorousTest() {
        upTree trees = new upTree(100000000);
        for (int i = 0; i < (trees.size - 1); i++) {
            trees.dumbUnion((int)(Math.random() * 99999999), i);
        }
        for (int i = 0; i < 100000000; i++) {
            trees.dumbFind((int)(Math.random() * 99999999));
        }
    }

    public static void rigorousGame() {
        gameBoard board = new gameBoard(1000, 1000);
        int turn = 1;
        while (board.winColor == null) {
            if (turn % 2 == 0) {
                board.insert((int)(Math.random() * 999999), 'R');
            } else {
                board.insert((int)(Math.random() * 999999), 'B');
            }
            turn++;
        }
        board.printBoard();
    }

    public static void dumbRigorousGame() {
        gameBoard board = new gameBoard(1000, 1000);
        int turn = 1;
        while (board.winColor == null) {
            if (turn % 2 == 0) {
                board.dumbInsert((int)(Math.random() * 999999), 'R');
            } else {
                board.dumbInsert((int)(Math.random() * 999999), 'B');
            }
            turn++;
        }
        board.printBoard();
    }

    public static void playGame() {
        gameBoard board = new gameBoard(11, 11);
        File file = new File("moves.txt");
        try {
            Scanner scanner = new Scanner(file);
            int turn = 1;
            while (scanner.hasNextLine() && board.winColor == null) {
                if (turn % 2 == 0) {
                    board.insert((scanner.nextInt() - 1), 'R');
                } else {
                    board.insert((scanner.nextInt() - 1), 'B');
                }
                Thread.sleep(0);
                board.printBoard();
                turn++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playDumbGame() {
        gameBoard board = new gameBoard(11, 11);
        File file = new File("moves.txt");
        try {
            Scanner scanner = new Scanner(file);
            int turn = 1;
            while (scanner.hasNextLine() && board.winColor == null) {
                if (turn % 2 == 0) {
                    board.dumbInsert((scanner.nextInt() - 1), 'R');
                } else {
                    board.dumbInsert((scanner.nextInt() - 1), 'B');
                }
                Thread.sleep(0);
                board.printBoard();
                turn++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playRandomGame() {
        int rows = (int)((Math.random() * 15) + 1);
        int cols = (int)((Math.random() * 15) + 1);
        gameBoard board = new gameBoard(cols, rows);
        boolean[] alreadyPlayed = new boolean[(rows * cols)];
        int turn = 1;
        while (board.winColor == null) {
            if (turn % 2 == 0) {
                board.insert(pickRandomMove(alreadyPlayed, rows, cols), 'R');
            } else {
                board.insert(pickRandomMove(alreadyPlayed, rows, cols), 'B');
            }
            board.printBoard();
//            board.board.printTree();
            turn++;
        }
    }

    public static int pickRandomMove(boolean[] alreadyPlayed, int rows, int cols) {
        int potentialMove = (int)(Math.random() * (rows * cols));
        if (alreadyPlayed[potentialMove]) {
            return pickRandomMove(alreadyPlayed, rows, cols);
        } else {
            alreadyPlayed[potentialMove] = true;
            return potentialMove;
        }
    }
}
