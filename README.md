# Lab 10 - Tic Tac Toe

## Due Tuesday, Nov. 6 at 11:59 PM

## Background

See the [Wikipedia Tic Tac Toe](https://en.wikipedia.org/wiki/Tic-tac-toe) article for a complete description of the familiar Tic-Tac-Toe game.  Today we are going to implement a Java Swing GUI version of Tic-Tac-Toe. In order to save time, most of the infrastructure has already been provided for you. We are providing three classes:

1. The Square class - This is a class which is used to model one of the 9 squares on a Tic-Tac-Toe board. It starts off with a blank value, but can be set to either an X or an O. It keeps track of the entire game to which it belongs with the "game" field, which is set by the intializer. This class has a ```choose``` method which allows either player to choose a new value.  We assume that the user plays "X", and the computer plays "O" in this game.  When the user selects a Square by pressing the button, it's value is changed to "X", and the button is disabled so it cannot be chosen again.  When the computer chooses this square, it's value is changed to "O", and it is disabled.

2. The Line class - When a Tic-Tac-Toe board is created, there are eight ways to create three in a row... three in a row on the top row, three in a row on the middle row, three in a row on the bottom row, three in a row in the first column, three in a row in the middle column, three in a row on the right column, three in a row on the left-to-right diagonal, and three in a row on the right-to-left diagonal.  A "line" consists of three squares which are all in a line. 

   When the board is created, the computer initializes and keeps track of all 8 lines. To start off with, a line is empty, but as the board is created, all three squares are added to the line using the ```add``` method. 
   
   Once all the squares have been added, the line can be checked to see if there is a winner on that line with the ```isWinner``` method. This method checks to see if all three squares in this line are the same non-blank value (either all X or all O).  If so, the isWinner method changes this line so that it blinks in green, and returns a true value. Note that the blinking is enabled by creating a Java ```Timer```, which is a library class which periodically calls back it's action listener, depending on the time specified when we create the Timer. The action listener in this case is implemented by a Lambda expression that sets the background color of the winning squares in the line to either green or null (which says use the default background color), and then flips a boolean field called ```blinker``` so that the next time it is called, it changes the background the other way.
   
   The Line class also has an ```allButOne``` method. This is very useful to check to see if all of the squares in this line but one are set to the check value specified in the argument, and the last square is open. If such a condition exists, the ```allButOne``` method returns the square that is open.  For instance, if the line contains all the squares on the top row of the board, and the first and third columns of the top row contain an "X", and the middle row is still blank, then ```allButOne("X")``` will return the middle square. If allButOne is false, for instance if all squares are taken, or less than two squares are at the correct value, then the allButOne method returns a null.
   
   There is also a method called ```avail``` which returns the first available square in the line, or returns null if all squares are already taken.
   
   Finally, there is a ```disableAllSquares``` method, which simply turns off all the Squares so the user is no longer allowed to press that button. This method is used as soon as someone wins the game.

3. The TicTacToe class - This is a class which plays a single game of Tic-Tac-Toe. It extends JFrame, and uses the JFrame contained inside it to manage the GUI for the game. Note that this is a minimalist implementation of the game. We can always add bells and whistles later.

   There is only one field in the TicTacToe class (other than the fields inherited from JFrame)... an array of lines.
   
   The creator for the TicTacToe class invokes the JFrame creator, passing the title "Tic-Tac-Toe" into that creator, sets the default close operation for the frame, and sets the content pane of the frame to be a new panel managed by the GridLayout layout manager.  The parameters to the Grid Layout manager indicate that we want a 3 by 3 grid, with a 5 pixel space between each row and each column. We also instantiate the lines array to be an array of 8 references to lines. Then we initialize each element on the lines array with a reference to a new Line object... passing in ```this``` as the game that is being played. Next, we actually create each square, keeping track of which row and column the square is in.  We add this square to all of the lines it belongs to... one addition because this square is in a row, and should belong in that row's line, one addition because this square is in a column and should belong in that rows column, and if the square is on either the left to right or right to left diagonal, it is added to that as well.
   
   The TicTacToe class has a ```checkWinner``` method that just invokes isWinner for each line in the game.
   
   The TicTacToe class has a ```disableAllSquares``` method that invokes disableAllSquares for the first three lines (the rows) to ensure that no button can be pressed. This is invoked by the line isWinner method when there is a winner.

   The TicTacToe class has a ```computerTurn``` method, which takes no arguments, but chooses a square for the computer to put an "O" in when it is the computer's turn. This method is invoked after the user has chosen a square by clicking on that square. This is the method you will need to implement for this lab! The way it is delivered to you, it throws an UnsupportedOperation exception.
   
   Finally, the TicTacToe class has a ```main``` method which creates a game, and sends the commands to pack and make the frame visible to the event loop using the javax.swing.SwingUtilities.invokeLater static method, and using a lambda expression to encapsulate the functionality required.
   
## Computer Strategy for the ```computerTurn``` method

You will need to implement a strategy for the computer to play the "O" side of the Tic-Tac-Toe game.  The computerTurn method must... 

1. Select a single square that does not have either X or O in it already.
2. Invoke the ```choose("O")``` method on that square.
3. If choosing that square causes the computer to win the game, invoke the ```checkWinner()``` method.

If you read up on the game of Tic-Tac-Toe, if you are playing against a very good player, and you don't make any mistakes, the game will end up in a draw... nobody wins. If you are playing against a decent player, then most of the time, nobody wins, but occasionally, you can win.  If you are playing against a dumb player, you can win consistently.

We'd like to create a computer strategy that's good enough so that the computer is at least a decent player.  It's pretty easy to make a dumb player...

```
public void compputerTurn() {
  for(Line line : lines) {
    Square sq=line.avail();
    if (sq != null) {
      sq.choose("O");
      checkWinner();
      return;
    }
  }
  // If you get here, there are no squares open!
}
```

Here is one strategy that might make at least a decent player...

1. If there is any line that has two "O"s and one free block, put an "O" in the free block... computer wins!
2. If there is any line that has two "X"s and one free block, put an "O" in the free block... block your opponent from winning!
3. If neither 1 nor 2 is true, choose the next available square.

See if you can implement this strategy in the computerTurn method... or come up with your own strategy.  Then play the game and see if you can beat the computer!

## Possible Enhancements

If you have some extra time and want to work on this some more, here are some extra things you can add to your game...

- It is possible, but non-trivial, to write a computerTurn method that cannot be beat.  The strategy needs to be even more complicated than the one outlined for the decent player above.

- Can you implement a "Hint" button that pops up a hint to the human player? For instance, "You've got a winning move next turn!" or "Better block... if you don't I'm winning next turn!" or "It doesn't really matter... select whatever square you like." If your user hits the hint button twice on the same turn, you might want to respond with something smart like "Hey... you don't pay me enough for TWO hints."

- Another possible button is a "Reset" button that would reset the game to the beginning state and allow you to start again. If you had a reset button, you could also keep and show a running tally of how many games the computer has won this session, how many the human has won, and how many ended in a draw.

- Make the winning row blink in green when the human wins, but blink in red when the computer wins.

## Using Eclipse to commit your results

In the Git perspective, select "lab10" and stage all your changes. Select the "Git Staging" tab, and make sure all your modified files are in the "Staged Changes" window. Type a commit message in the "Commit Message" sub-window. Then "Commit and Push...", get through the commit wizard and you will get a pop-up that tells you that the commit was succesful. Now, click on the "Git Reflog" tab, select the final commit, and right click to copy the commit ID. This is the commit hash code that can now be pasted into myCourses.

## Grading Criteria

This lab is worth 10 points. If your code does not compile, you will get an 8 point deduction. If your code gets a compiler warning, you will get a 2 point deduction. The CA's will run your lab10.TicTacToe main function. If the main method does not do everything requested with correct results (we're only grading the basic function - not the enhancements), you will get up to a 5 point deduction, depending on the severity of the problem. No points will be added or subtracted for extra enhancements *UNLESS* these enhancements inhibit the basic function of the GUI.
