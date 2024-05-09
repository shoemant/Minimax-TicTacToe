# TicTacToe
# Overview
This project is a simple console-based Tic-Tac-Toe game written in Java. The game allows a player to compete against an automated opponent that uses the minimax algorithm to determine its moves. The board is displayed in the console, and players input their moves by selecting numbers from 1 to 9, corresponding to positions on the game board.

Features
Play against a computer-controlled opponent.
Automatic detection of win, loss, or tie.
Repeated gameplay until the user decides to quit.
Clear display of the game board and instructions.
How to Run
To run this game, you will need a Java Runtime Environment (JRE) installed on your system. You can compile and run the game using the following commands in your terminal:

1. Compile the Java program:

```javac TicTacToe.java```

2. Run the compiled Java program:

```java TicTacToe```

# Game Instructions
When prompted, enter a number between 1 and 9 to place your mark ('X') on the board.
The computer will automatically make its move ('O') after you.
Continue to alternate turns until the game concludes with a win, loss, or tie.
After each game, you can choose to play again by entering 1 or exit the game by entering 0.

# Game Rules
The game is played on a 3x3 grid.
Players take turns placing their marks on the grid.
The first player to align three of their marks vertically, horizontally, or diagonally wins.
If all nine squares are filled and no player has three aligned marks, the game is a tie.

# Implementation Details
The TicTacToe class handles the main game loop, input processing, and alternating turns between the player and the computer.
The bestMove() method in the TicTacToe class implements the minimax algorithm to determine the optimal move for the computer.
Game state (win/loss/tie) is checked after each move using the checkGameState() method.

# Author
This game was developed as a fun project to understand game implementation and AI algorithms in Java.
