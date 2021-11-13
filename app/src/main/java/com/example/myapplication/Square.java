package com.example.myapplication;

public class Square {
  private Player player = null;

  public void fill(Player player) {
    this.player = player;
  }

  public boolean isFilled() {
    return player != null;
  }

  public Player getPlayer() {
    return player;
  }
}
