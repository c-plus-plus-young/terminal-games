import java.util.Scanner;

public class gameBoard{

    private int cols;
    private int rows;
    private char[] charBoard; // used for printing out current board
    public upTree board;
    private static final String defaultColor = "\u001B[0m";
    private static final String red = "\u001B[31m";
    private static final String blue = "\u001B[34m";
    private int moves;
    public String winColor;

    gameBoard(int cols, int rows){
        // add four additional nodes for top, bottom, left, and right
        this.board = new upTree((rows * cols) + 4);
        boolean gameWon = false;
        this.cols = cols;
        this.rows = rows;
        this.moves = 0;
        this.winColor = null;
        this.charBoard = new char[(rows * cols)];
        for (int i = 0; i < (rows * cols); i++) {
            charBoard[i] = '0';
        }
    }

    public void printBoard() {
        if (this.winColor != null) {
            System.out.println(defaultColor + winColor + " has won after "
                    + moves + " attempted moves! Here is the final board.");
        }
        int spaces = 0;
        for (int i = 0; i < ((cols * 2) + 2); i++) {
            System.out.print(red + "_");
        }
        System.out.println();
        for (int i = 0; i < this.charBoard.length; i += cols) {
            for (int j = spaces; j > 0; j--) {
                System.out.print(" ");
            }
            System.out.print(blue + "| ");
            for (int k = i; k < (i + cols); k++) {
                if (this.charBoard[k] == '0') {
                    System.out.print(defaultColor + "0 ");
                } else if (this.charBoard[k] == 'B') {
                    System.out.print(blue + "B ");
                } else {
                    System.out.print(red + "R ");
                }
            }
            System.out.print(blue + "|");
            System.out.println(defaultColor);
            spaces += 1;
        }
        for (int i = spaces; i > 0; i--) {
            System.out.print(" ");
        }
        for (int i = 0; i < ((cols * 2) + 2); i++) {
            System.out.print(red + "-");
        }
        System.out.println(defaultColor);
    }

    public void insert (int position, char color){
        this.moves++;
        this.charBoard[position] = color;

        // if not on top row, merge with above neighbors
        if (position >= cols) {
            if (this.charBoard[(position - cols)] == color) {
                this.board.union(position, (position - cols));
            }
            // can't merge with top right neighbor if on right edge
            if (position % cols != (cols - 1) && this.charBoard[((position - cols) + 1)] == color) {
                this.board.union(position, ((position - cols) + 1));
            }
        } else if (color == 'R'){
            // node must be on top, connect to top of board
            this.board.union(position, (cols * rows));
        }

        // if not on bottom row, merge with below neighbors
        if (position < ((cols * rows) - cols)) {
            if (this.charBoard[(position + cols)] ==  color) {
                this.board.union(position, (position + cols));
            }
            // can't merge with bottom left neighbor if on left edge
            if (position % cols != 0 && this.charBoard[(position + (cols - 1))] == color) {
                this.board.union(position, (position + (cols - 1)));
            }
        } else if (color == 'R'){
            // node must be on bottom, connect to bottom of board
            this.board.union(position, ((cols * rows) + 1));
        }

        // if not on left side, merge with left neighbor
        if (position % cols != 0) {
            if (charBoard[(position - 1)] == color) {
                this.board.union(position, (position - 1));
            }
        } else if (color == 'B') {
            // node must be on left side, merge with left side of board
            this.board.union(position, ((cols * rows) + 2));
        }

        // if not on right side, merge with right neighbor
        if (position % cols != (cols - 1)) {
            if (charBoard[(position + 1)] == color) {
                this.board.union(position, (position + 1));
            }
        } else if (color == 'B') {
            // node must be on right side, merge with right side of board
            this.board.union(position, ((cols * rows) + 3));
        }


        // check if game has been won
        // up and down, red wins
        if (this.board.find((cols * rows)) == this.board.find((cols * rows) + 1)) {
            this.winColor = "Red";
        // if left and right are connected, blue wins
        } else if (this.board.find((cols * rows) + 2) == this.board.find((cols * rows) + 3)) {
            this.winColor = "Blue";
        }

    }

    public void dumbInsert (int position, char color){
        this.moves++;
        this.charBoard[position] = color;

        // if not on top row, merge with above neighbors
        if (position >= cols) {
            if (this.charBoard[(position - cols)] == color) {
                this.board.dumbUnion(position, (position - cols));
            }
            // can't merge with top right neighbor if on right edge
            if (position % cols != (cols - 1) && this.charBoard[((position - cols) + 1)] == color) {
                this.board.dumbUnion(position, ((position - cols) + 1));
            }
        } else if (color == 'R'){
            // node must be on top, connect to top of board
            this.board.dumbUnion(position, (cols * rows));
        }

        // if not on bottom row, merge with below neighbors
        if (position < ((cols * rows) - cols)) {
            if (this.charBoard[(position + cols)] ==  color) {
                this.board.dumbUnion(position, (position + cols));
            }
            // can't merge with bottom left neighbor if on left edge
            if (position % cols != 0 && this.charBoard[(position + (cols - 1))] == color) {
                this.board.dumbUnion(position, (position + (cols - 1)));
            }
        } else if (color == 'R'){
            // node must be on bottom, connect to bottom of board
            this.board.dumbUnion(position, ((cols * rows) + 1));
        }

        // if not on left side, merge with left neighbor
        if (position % cols != 0) {
            if (charBoard[(position - 1)] == color) {
                this.board.dumbUnion(position, (position - 1));
            }
        } else if (color == 'B') {
            // node must be on left side, merge with left side of board
            this.board.dumbUnion(position, ((cols * rows) + 2));
        }

        // if not on right side, merge with right neighbor
        if (position % cols != (cols - 1)) {
            if (charBoard[(position + 1)] == color) {
                this.board.dumbUnion(position, (position + 1));
            }
        } else if (color == 'B') {
            // node must be on right side, merge with right side of board
            this.board.dumbUnion(position, ((cols * rows) + 3));
        }


        // check if game has been won
        // up and down, red wins
        if (this.board.dumbFind((cols * rows)) == this.board.dumbFind((cols * rows) + 1)) {
            this.winColor = "Red";
            // if left and right are connected, blue wins
        } else if (this.board.dumbFind((cols * rows) + 2) == this.board.dumbFind((cols * rows) + 3)) {
            this.winColor = "Blue";
        }

    }

    public static void playableGame(){
        moveMaker mm = new moveMaker();
        int size = mm.getBoardSize();
        gameBoard board = new gameBoard(size, size);

        int turn = 1;
        board.printBoard();
        while (board.winColor == null) {
            if (turn % 2 == 0) {
                board.insert(mm.makeMove(), 'R');
            } else {
                board.insert(mm.makeMove(), 'B');
            }
            board.printBoard();
            turn++;
        }

    }

    public static class moveMaker {
        Scanner scanner;
        boolean[] alreadyPlayed;

        moveMaker() {
            scanner = new Scanner(System.in);
        }

        public int getBoardSize() {
            System.out.println("What size would you like the board?");
            int size = 0;
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                alreadyPlayed = new boolean[(size * size)];
                return size;
            } else {
                System.out.println("Please input a number");
                scanner.next();
                return getBoardSize();
            }
        }
        public int makeMove() {
            System.out.println("Where would you like to play?");
            int suggestedMove = 0;
            if (scanner.hasNextInt()) {
                suggestedMove = scanner.nextInt();
            } else {
                System.out.println("Please input a number");
                scanner.next();
                return makeMove();
            }
            if (suggestedMove >= alreadyPlayed.length || alreadyPlayed[suggestedMove] != false) {
                System.out.println("Invalid move");
                scanner.next();
                scanner.next();
                return makeMove();
            } else {
                alreadyPlayed[suggestedMove] = true;
                return suggestedMove;
            }
        }
    }

}