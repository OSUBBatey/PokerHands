# PokerHands

A simple utility that compares poker hands and prints the outcome to the console.

Compilation Instructions:
To compile the program, download/unzip the archive and then navigate to the "src" folder.
With "src" as the working directory enter the following command.
```
$ javac com/Batey/PokerGame/*.java com/Batey/PokerGame/Enums/*.java com/Batey/PokerGame/PlayerComponents/*.java
```
This will create the class files necessary to run the program.

To run the program:
From the "src" directory enter the following command:
```
$ java com/Batey/PokerGame/Main [input arguments]
```
You may also load the project in your desired IDE and run it.

Valid Input Arguments are as follows:
[p1Name] [CardID] [CardID] [CardID] [CardID] [CardID] [p2Name] [CardID] [CardID] [CardID] [CardID] [CardID]

Where p1Name/p2Name = player name     
CardID = a two character ID with the following format [rank,suit] 
Ranks are as follows: {2,3,4,5,6,7,8,9,T,J,Q,K,A} 
Suits are as follows: {C,D,H,S} 

Example program call:
```
java com/Batey/PokerGame/Main Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH
```
Example chained program call:
```
java com/Batey/PokerGame/Main Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH
```
Input must be formatted as specified. Any non-formatted input will result in undefined operation.
