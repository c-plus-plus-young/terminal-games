# Terminal Games
Simple games that can be played in the terminal

### Hex
```
________________________
| 0 B R 0 0 0 R 0 0 0 0 |
 | 0 B R 0 0 0 R 0 0 R 0 |
  | 0 0 0 0 0 0 R 0 0 R 0 |
   | 0 0 0 0 0 R 0 0 0 B 0 |
    | 0 0 B B B R R 0 B 0 0 |
     | B B B 0 B 0 R B 0 0 0 |
      | 0 0 0 B 0 R 0 B 0 0 0 |
       | 0 0 0 B B B B 0 0 0 0 |
        | 0 0 0 0 R R 0 0 0 0 0 |
         | 0 0 R 0 0 0 0 0 0 0 0 |
          | 0 R 0 R 0 0 0 0 0 0 0 |
           ------------------------
```
Game of Hex using an uptree to determine if a player has won. Moves are made
by selecting the appropriate number of the grid space (starting at 0 for the 
top left). Game is in color in terminal.

### Chess

```
   a  b  c  d  e  f  g  h
8  R  N  B  Q  K  B  N  R 
7  P  P  P  P  P  P  P  P 
6  ▆    ▆    ▆    ▆      
5     ▆    ▆    ▆    ▆    
4  ▆    ▆    ▆    ▆       
3     ▆    ▆    ▆    ▆    
2  P  P  P  P  P  P  P  P 
1  R  N  B  Q  K  B  N  R 
```

Game of Chess. Moves are made using long algebraic notation (ex. the common 
opening e4 would be written as e2e4). 
