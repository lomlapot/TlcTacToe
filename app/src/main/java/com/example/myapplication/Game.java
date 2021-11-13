package com.example.myapplication;

import com.example.myapplication.CheckWinnerInterface;

public class Game {
  // игроки
  private Player[] players;

  // поле
  private Square[][] field;

  // начата ли игра?
  private boolean started;

  // текущий игрок
  private Player activePlayer;

  // Считает колличество заполненных ячеек
  private int filled;

  // Всего ячеек
  private int squareCount;
  private CheckWinnerInterface[] winnerCheckers;

  public Game() {

    winnerCheckers = new CheckWinnerInterface[4];
    winnerCheckers[0] = new CheckHorizontal(this);
    winnerCheckers[1] = new CheckVertical(this);
    winnerCheckers[2] = new CheckDiogonalLeft(this);
    winnerCheckers[3] = new CheckDiogonalRight(this);
    field = new Square[3][3];
    squareCount = 0;
    // заполнение поля
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        field[i][j] = new Square();
        squareCount++;
      }
    }
    players = new Player[2];
    started = false;
    activePlayer = null;
    filled = 0;
  }

  public void start() {
    resetPlayers();
    started = true;
  }

  private void resetPlayers() {
    players[0] = new Player("X");
    players[1] = new Player("O");
    setCurrentActivePlayer(players[0]);
  }

  private void setCurrentActivePlayer(Player player) {
    activePlayer = player;
  }

  public Square[][] getField() {
    return field;
  }

  public boolean makeTurn(int x, int y) {
    if (field[x][y].isFilled()) {
      return false;
    }
    field[x][y].fill(getCurrentActivePlayer());
    filled++;
    switchPlayers();
    return true;
  }

  public void switchPlayers() {
    activePlayer = (activePlayer == players[0]) ? players[1] : players[0];
  }

  public Player getCurrentActivePlayer() {
    return activePlayer;
  }

  public boolean isFieldFilled() {
    return squareCount == filled;
  }

  public Player checkWinner() {
    for (CheckWinnerInterface winChecker : winnerCheckers) {
      Player winner = winChecker.checkWinner();
      if (winner != null) {
        return winner;
      }
    }
    return null;
  }

  public void reset() {
    resetField();
    resetPlayers();
  }

  private void resetField() {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        field[i][j].fill(null);
      }
    }
    filled = 0;
  }
}
