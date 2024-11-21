import java.util.Scanner;
public class gameBoard {
    String[][][] board;

    String defaultBackgroundColor = "\u001B[0m";
    String backgroundWhite = "\u001B[47m";
    String backgroundBlack = "\u001B[40m";
    String backgroundBlue = "\u001B[44m";
    String backgroundRed = "\u001B[41m";

    String textWhite = "\u001B[37m";
    String textBlack = "\u001B[37m";
    String textBlue = "\u001B[34m";
    String textRed = "\u001B[31m";
//BLACK 	\u001B[30m 	BLACK_BACKGROUND 	\u001B[40m
//RED 	\u001B[31m 	RED_BACKGROUND 	\u001B[41m
//GREEN 	\u001B[32m 	GREEN_BACKGROUND 	\u001B[42m
//YELLOW 	\u001B[33m 	YELLOW_BACKGROUND 	\u001B[43m
//BLUE 	\u001B[34m 	BLUE_BACKGROUND 	\u001B[44m
//PURPLE 	\u001B[35m 	PURPLE_BACKGROUND 	\u001B[45m
//CYAN 	\u001B[36m 	CYAN_BACKGROUND 	\u001B[46m
//WHITE 	\u001B[37m 	WHITE_BACKGROUND 	\u001B[47m


    String winColor = null;
    Scanner scanner;
    gameBoard() {
        board = new String[8][8][2];
        scanner = new Scanner(System.in);

        //fill pawn rows
        for (int i = 0; i < 8; i++) {
            board[1][i][0] = "P";
            board[1][i][1] = textBlue;
        }
        board[0][0][0] = "R";
        board[0][0][1] = textBlue;
        board[0][7][0] = "R";
        board[0][7][1] = textBlue;
        board[0][1][0] = "N";
        board[0][1][1] = textBlue;
        board[0][6][0] = "N";
        board[0][6][1] = textBlue;
        board[0][2][0] = "B";
        board[0][2][1] = textBlue;
        board[0][5][0] = "B";
        board[0][5][1] = textBlue;
        board[0][3][0] = "Q";
        board[0][3][1] = textBlue;
        board[0][4][0] = "K";
        board[0][4][1] = textBlue;

        for (int i = 0; i < 8; i++) {
            board[6][i][0] = "P";
            board[6][i][1] = textRed;
        }

        board[7][0][0] = "R";
        board[7][0][1] = textRed;
        board[7][7][0] = "R";
        board[7][7][1] = textRed;
        board[7][1][0] = "N";
        board[7][1][1] = textRed;
        board[7][6][0] = "N";
        board[7][6][1] = textRed;
        board[7][2][0] = "B";
        board[7][2][1] = textRed;
        board[7][5][0] = "B";
        board[7][5][1] = textRed;
        board[7][3][0] = "Q";
        board[7][3][1] = textRed;
        board[7][4][0] = "K";
        board[7][4][1] = textRed;
    }

    public void printBoard(){
        String backgroundColor = defaultBackgroundColor;
        System.out.println(backgroundColor + "   a  b  c  d  e  f  g  h");
        for (int i = 0; i < 8; i ++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0 ^ j % 2 != 0) {
                    backgroundColor = backgroundBlack;
                } else {
                    backgroundColor = backgroundWhite;
                }

                if (board[i][j][0] == null) {
                    System.out.print(backgroundColor + "   ");
                } else {
                    System.out.print(backgroundColor + " " + board[i][j][1] + board[i][j][0] + " ");
                }
            }
            System.out.println(defaultBackgroundColor);
        }
    }

    public void playGame() {

        while (winColor == null) {
            takeTurn("White");
            takeTurn("Black");
        }
        printBoard();
        if (winColor == "Stalemate") {
            System.out.println(winColor);
        } else {
            System.out.println(winColor + " wins.");
        }
    }

    private void takeTurn(String color) {
        printBoard();
        System.out.println(color + " to move.");
        if (scanner.hasNext()) {

        } else {
            System.out.println("Invalid move");
        }
    }

//    moves are in long algebraic notation: ex e2e4 for common starting move, white pawn to e4
//    0-0 (for kingside castling) and 0-0-0 (queenside castling).
//    O-O and O-O-O (letter O rather than digit 0) are also commonly used
//    = is a draw offer

//    store all moves in a file
//    put x on file when piece is captured
//    + means check
//    =Q for pawn promoting to queen
//    # for checkmate
//
//    The notation 1–0 at the completion of moves indicates that White won, 0–1 indicates that
//    Black won, and ½–½ indicates a draw. In case of forfeit, the scores 0–0, ½–0, and 0–½ are
//    also possible.[7][8] If player(s) lost by default, results are +/−, −/+, or −/−.
//    (1/2 listed above is only possible if a checkmate is impossible by one player, ie they
//    don't have enough material to capture)
}
