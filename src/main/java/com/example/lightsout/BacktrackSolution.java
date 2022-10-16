package com.example.lightsout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/16/22
 */
public class BacktrackSolution {
    final static String Path = "/Users/mahdi/dev/java/interview/bluerocktms/samples/";
    private static final int N = 8;

    public static void main(String[] args) throws IOException {
        // File path is passed as parameter
//         for (int k = 0; k < 10; k++) {
//            String f = "0" + k + ".txt";
//            System.err.println("********** "+k+" *********");
        File file = new File(Path + "05.txt");
//        File file = new File(Path + "00.txt");//0,1 0,2 1,0


        BufferedReader br = new BufferedReader(new FileReader(file));

        int depth = Integer.valueOf(br.readLine());

        String boardString = br.readLine();
        String[] boardArray = boardString.split(",");//[100, 101, 011]
        String piecesString = br.readLine();
        String[] piecesArr = piecesString.replace(",", "Y").split(" ");//[..X|XXX|X.., X|X|X, .X|XX, XX.|.X.|.XX, XX|X., XX, .XX|XX.]
        br.close();
        long startTime = System.currentTimeMillis() / 1000;

        int[][] board = new int[boardArray.length][boardArray[0].length()];
        for (int i = 0; i < boardArray.length; i++) {
            String row = boardArray[i];
            for (int j = 0; j < row.length(); j++) {
                board[i][j] = Integer.parseInt(Character.toString(row.charAt(j)));
            }
        }
        List<String[][]> listMatrix = getPiecesListMatrix(piecesArr);
        StringBuilder sb = new StringBuilder();
        solution(board, listMatrix, -1, depth, sb);
        System.out.println(sb.reverse());

        System.out.println("# FINISHED AT " + new Date() + "; Elapsed Time: " + (System.currentTimeMillis() / 1000 - startTime) + " Second");
    }


    private static boolean solution(int[][] board, List<String[][]> listMatrix, int indexListMatrix, int depth, StringBuilder result) {

        if (indexListMatrix >= listMatrix.size() - 1) {
            return false;
        }
        indexListMatrix++;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (row + listMatrix.get(indexListMatrix).length > board.length || col + listMatrix.get(indexListMatrix)[0].length > board[0].length)
                    break;//If piece violated the boarder of the board, go for the next item of board.
                int[][] bk = copyBoard(board);//for backtrack. Save the current board.
                placePieceOnBoard(board, listMatrix.get(indexListMatrix), row, col, depth);//Place	a	piece	on	the	board
                if (sumBoardMatrix(board) == 0) {
                    if ((indexListMatrix) == (listMatrix.size() - 1)) {
                        result.append(row).append(",").append(col).append(" ");
                        return true;
                    } else return false;
                }
                if (!solution(board, listMatrix, indexListMatrix, depth, result)) {
                    board = copyBoard(bk);//Revert-> backtrack if the above condition is false
                } else {
                    result.append(row).append(",").append(col).append(" ");
                    return true;
                }
            }
        }
        return false;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] bk = new int[board.length][];
        for (int i = 0; i < board.length; i++)
            bk[i] = board[i].clone();
        return bk;
    }

    private static void placePieceOnBoard(int[][] board, String[][] piece, final int startRow, final int startCol, final int depth) {//OK
        if (startRow + piece.length > board.length || startCol + piece[0].length > board[0].length) return;
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j].charAt(0) == 'X') {
                    board[startRow + i][startCol + j] = ((board[startRow + i][startCol + j] + 1) % depth);
                }
            }
        }
    }

    private static int sumBoardMatrix(final int[][] board) {
        int result = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                result += anInt;
            }
        }
        return result;
    }

    private static void printBoradMatrix(int[][] board) {
        System.out.println(board.length + "*" + board[0].length + "*****************");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printPiecesMatrix(String[][] Ppieces) {
        System.out.println(Ppieces.length + "*" + Ppieces[0].length + "*****************");
        for (int i = 0; i < Ppieces.length; i++) {
            for (int j = 0; j < Ppieces[i].length; j++) {
                System.out.print(Ppieces[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static List<String[][]> getPiecesListMatrix(String[] piecesArr) {
        List<List<String>> listList = new ArrayList<>();
        List<String[][]> listMatrix = new ArrayList<>();

        for (int z = 0; z < piecesArr.length; z++) {
            String[] tempatt = piecesArr[z].split("Y");
            List<String> sub = Arrays.asList(tempatt);//[..X, XXX, X..]
            listList.add(sub);
            //[..X, XXX, X..] -> [.  .  X]
            //  [X  X  X]
            //                   [X  .  .]

            //[X, X, X] ->       [X]
            //                   [X]
            //                   [X]

            String[][] pieces = new String[sub.size()][sub.get(0).length()];
            for (int i = 0; i < sub.size(); i++) {
                String row = sub.get(i);
                for (int j = 0; j < row.length(); j++) {
                    pieces[i][j] = (Character.toString(row.charAt(j)));
                }
            }
            listMatrix.add(pieces);
        }

        return listMatrix;
    }

    private static void printListMatrix(List<String[][]> listMatrix) {
        for (int z = 0; z < listMatrix.size(); z++) {
            String[][] piececs = listMatrix.get(z);
            System.out.println(z + "------------------");
            for (int i = 0; i < piececs.length; i++) {
                for (int j = 0; j < piececs[i].length; j++) {
                    System.out.print(piececs[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}