package com.example.lightsout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/17/22
 */
class BacktrackSolutionTest {

    BacktrackSolution solution=new BacktrackSolution();
    int[][] board;
    List<String[][]> listMatrix;
    int depth;

    @BeforeEach
    void loadSample() throws IOException {
        File file = new File("./samples/" + "00.txt"); //

        BufferedReader br = new BufferedReader(new FileReader(file));
        depth = Integer.valueOf(br.readLine());

        String boardString = br.readLine();
        String[] boardArray = boardString.split(",");//[100, 101, 011]
        String piecesString = br.readLine();
        String[] piecesArr = piecesString.replace(",", "Y").split(" ");//[..X|XXX|X.., X|X|X, .X|XX, XX.|.X.|.XX, XX|X., XX, .XX|XX.]
        br.close();

        board =  BacktrackSolution.toBoardMatrix(boardArray); // create board
        listMatrix = BacktrackSolution.toPiecesListMatrix(piecesArr); // create list of pieces
    }

    @Test
    void testSolution(){
        String result= solution.solution(board, listMatrix, depth);// go to solve
        assertEquals("0,1 0,2 1,0",result);
    }
}