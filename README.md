# Multi depth Lights Out Game Assessment for BLTMS company
This is an assessment about kind of pattern matching.
The assessment consists of a backtrack solution for solving this problem.

## Introduction
There is a famous game called “Lights out” which starts with a board of lights (either on or off) and each time a light is switched the neighbouring lights are also switched. The goal of the	game is	to switch all the lights off. Below an image visualizing the gameplay.
We start the puzzle with an	initial board state	where each cell has	an initial value
and	a global “depth”. Each time we place a piece on	the	board and update the board state by incrementing each board cell which overlaps with a non-empty piece cell. 
If this value equals the “depth” of	the board, then it is reset to 0. After all pieces have been placed, all cells should have the value 0.
I solved this problem by backtracking technique.

## Introduction of backtracking
Backtracking is a general algorithm for finding all (or some) solutions to some computational problems (notably Constraint satisfaction problems or CSPs),
which incrementally builds candidates to the solution and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot lead to a valid solution.

![backtracking](https://user-images.githubusercontent.com/8404721/196032329-3500f66a-b4bb-4e32-90e6-b14076b55643.png)

Conceptually, one can imagine the procedure of backtracking as the tree traversal. Starting from the root node, one sets out to search for solutions that are located at the leaf nodes. Each intermediate node represents a partial candidate solution that could potentially lead us to a final valid solution. At each node, we would fan out to move one step further to the final solution, i.e. we iterate the child nodes of the current node. Once we can determine if a certain node cannot possibly lead to a valid solution, we abandon the current node and backtrack to its parent node to explore other possibilities. It is due to this backtracking behaviour, the backtracking algorithms are often much faster than the brute-force search algorithm, since it eliminates many unnecessary exploration.

Backtracking as a tree search has three fundamental traits:
When faced with a choice, each possibility is explored recursively.
After trying one choice and before trying the next, the program state is restored back to exactly what it was before trying the first choice.
When it’s clear an exploration path will not lead to a viable solution, the exploration of that path stops.

## The main features of this:
1. Break down the task into the small task and will go on. When find there is no way to go, will return and restore the non-result board.
2. Because every result is related to the previous one, I can not break them into smaller problems. I mean there is a dependency between each step.
3. Because of 2, we need to find something different. Maybe a Data Analytic algorithm to help us. We can map this problem to pattern matching and use some algorithm like **Cross Entropy**. We need something to help us anticipate or tell us about the result without going deeper into the tree.

## Assumption
As a developer I need to solve the problem without Data Analytic algorithm.

## How Algorithm works:
In order to know the whole matrix is zero, I summed all cells. If sum is equal zero it means the problem solved.
For the pieces more than 11 it takes more 1 hours. Why? Because the Order of this algorithm is `(M*N) Power of PiecesNo` ((M*N) ^ PiecesNo).
Travers all cells of matrix and place a piece on the board whenever it can. Then go for the next piece. If did not find a result, revert the change then continue again for the next cell.
```
 for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[0].length; col++) {
           placePieceOnBoard;
           if sumBoardMatrix(board) == 0 return;
           if (!solution) backtrack;
           else return true;
        }
 }
```
In this picture you can see how algorithm works.
![how-algorithm-works-sample](https://user-images.githubusercontent.com/8404721/196033576-7c24d08f-c4c4-4e90-946b-95bcf11a2ec2.jpg)

In this picture you can see process of a result how to obtain.
![how-algorithm-works-sample-00](https://user-images.githubusercontent.com/8404721/196033789-20bae6a0-871c-41ea-925e-014a4c3d8824.jpg)


## Results of the samples.
Samples are in `/sample` folder.

```
* example:0,1 0,2 1,0  
* 01.txt: 0,0 1,0 1,0 0,0 1,0 1,0 0,1
* 02.txt: 1,0 0,1 0,1 2,2 1,0 1,0 0,2
* 03.txt: 0,0 1,3 0,2 2,0 1,0 0,4 1,2 0,0 0,0 0,2 1,0   ;36 Seconds
* 04.txt: 1,0 3,2 0,0 2,0 0,0 0,0 0,0 1,2 0,0 2,1 3,0   ;34 Seconds
* 05.txt: 2,0 3,1 0,0 4,0 2,3 1,0 0,1 3,2 1,0 2,1 0,0 1,0
* 06.txt: 1,0 0,2 0,3 0,3 0,0 0,2 2,3 2,3 0,1 1,2 0,0 0,1 ;8448 Seconds= 2:30 hour
* 07.txt: Time Limit Exceeded. It takes more than 3 hour.
* 08.txt: 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 3,0 1,1 4,0 0,0 1,0 2,2 0,0 ;2 Seconds
* 09.txt: Time Limit Exceeded. It takes more tha 4 hours
* 10.txt: Time Limit Exceeded. It takes more tha 4 hours
```

### Library
I found some library for work with matrix.Our problem is more about search not mathematical things in this algorithm.
* Colt
* LA4J
* Apache Commons math3
* ND4J

