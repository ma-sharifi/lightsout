## Multi Depth Lights Out BLTMS Assessment
This is an assessment about kind of pattern matching.
The assessment consists of a backtrack solution for solving this problem.

# Introduction
Welcome to Lights Out.

Backtracking is a general algorithm for finding all (or some) solutions to some computational problems (notably Constraint satisfaction problems or CSPs),
which incrementally builds candidates to the solution and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot lead to a valid solution.


Backtracking as a tree search
Backtracking has three fundamental traits:

When faced with a choice, each possibility is explored recursively.

After trying one choice and before trying the next, the program state is restored back to exactly what it was before trying the first choice.

When it’s clear an exploration path will not lead to a viable solution, the exploration of that path stops.

## The main features of this:
1. Break down the task into small task and will go on. When find there is no way to go, will return and restore the non result board.
2. Because every pieces related to previous one, I can not break them to small pieces. **Nor board and neither Pieces. I mean there is dependency here.
3. Because of 2 , wee nedd to find somthing different. Maybe Data Analytic algorithm to help us. We can map this problem to pattern matching
and use dome algorithm like Cross Entropy. We need something help us to anticipate or tell us about the result without goring deeper into the tree.

## Solution:
In order to know the whole matrix is zero, I summed all items.
For the pieces more than 11 it takes more 1 hours, sometime without any result.
The order of this algorithm is : 

###How Algorithm works:
Travers all item of matrix and place a piece on	the	board whenever it can. Then go for the next piece.
```
for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
               placePieceOnBoard
               if sumBoardMatrix(board) == 0 return;
               if (!solution)
                 baktrack;
               else
                return true;
            }
     }
```

//            File file = new    File(Path + f);
//        File file = new File(Path + "10.txt"); //4 Time consuming
//        File file = new File(Path + "09.txt"); //2 Time consuming UNLIMITED :-(
//        File file = new File(Path + "08.txt");//OK E Second
//        File file = new File(Path + "07.txt"); // 4 Time consuming
//        File file = new File(Path + "06.txt"); //4 Time consuming It took 2 hours
//        File file = new File(Path + "061.txt"); //Depth:4 - 10 Pieces Takes 8 Minutes; Depth: 2, Pieces: 10. It takes 7 minutes. -> The count of pieces is important.
//        File file = new File(Path + "05.txt"); //3 Time consuming 60 Take time without result.
//        File file = new File(Path + "04.txt"); // 3 OK  But Time consuming;  50 Seconds
//        File file = new File(Path + "03.txt"); //3 OK But is Time consuming 40 Seconds takes.
//        File file = new File(Path + "02.txt"); //OK
//        File file = new File(Path + "01.txt"); //OK //0,0 1,0 1,0 0,0 1,0 1,0 0,1

## Other way

###Library
I found some library for work with matrix.
Our problem is more about search not mathematical things in my algorithm.
* Colt
* LA4J
* Apache Commons math3
* ND4J

