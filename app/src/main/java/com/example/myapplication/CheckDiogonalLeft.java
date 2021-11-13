package com.example.myapplication;

public class CheckDiogonalLeft implements CheckWinnerInterface {
  Game game ;
  public CheckDiogonalLeft(Game game){
    this.game = game;
  }
  public Player checkWinner(){
    Square[][] field = game.getField();
        Player currPlayer;
        Player lastPlayer = null;
        int successCounter = 1;
        for (int i = 0, len = field.length; i < len; i++) {
            currPlayer = field[i][i].getPlayer();
            if (currPlayer != null) {
                if (lastPlayer == currPlayer) {
                    successCounter++;
                    if (successCounter == len) {
                        return currPlayer;
                    }
                }
            }
            lastPlayer = currPlayer;
        }
    return null ;
  }
}
