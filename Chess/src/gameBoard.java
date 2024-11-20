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

    gameBoard() {
        board = new String[8][8][2];

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
        board[0][5][0] = "R";
        board[0][5][1] = textBlue;
        board[]


        for (int i = 0; i < 8; i++) {
            board[6][i][0] = "P";
            board[6][i][1] = textRed;
        }
    }

    public void printBoard(){
        String backgroundColor = "\u001B[47m";
        for (int i = 0; i < 8; i ++) {
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
}
