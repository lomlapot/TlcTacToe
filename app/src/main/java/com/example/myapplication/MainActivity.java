package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
//import android.view.View.OnClickListener;
import android.view.View;
import com.example.myapplication.Game;
import android.widget.Toast;
import com.example.myapplication.R;

public class MainActivity extends Activity {

  private TableLayout container;
  private Button[][] buttons = new Button[3][3];
  Game game;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    container = findViewById(R.id.container);
    game = new Game();
    buildGameField();
    game.start();
  }

  private void buildGameField() {
    Square[][] field = game.getField();
    for (int i = 0, lenI = field.length; i < lenI; i++) {
      TableRow row = new TableRow(this); // создание строки таблицы
      for (int j = 0, lenJ = field[i].length; j < lenJ; j++) {
        Button button = new Button(this);
        buttons[i][j] = button;
      //  button.setBackgroundColor(0xfeefffff);
        button.setOnClickListener(new Listener(i, j));

        // установка слушателя, реагирующего на клик по кнопке
        row.addView(
            button,
            new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        // добавление кнопки в строку таблицы
        button.setWidth(360);
        button.setHeight(360);
      }
      container.addView(
          row,
          new TableLayout.LayoutParams(
              TableLayout.LayoutParams.WRAP_CONTENT,
              TableLayout.LayoutParams.WRAP_CONTENT)); // добавление строки в таблицу
    }
  }

  public class Listener implements View.OnClickListener {
    private int x = 0;
    private int y = 0;

    Listener(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public void onClick(View view) {
      Button button = (Button) view;
      Game g = game;

      // g.makeTurn(x,y);
      Player player = g.getCurrentActivePlayer();
      if (g.makeTurn(x, y)) {
        button.setText(player.getName());
       // button.setTextSize(30);
       
      }

      Player winner = g.checkWinner();
      if (winner != null) {
        gameOver(winner);
      }
      if (g.isFieldFilled()) { // в случае, если поле заполнено
        gameOver();
      }
    }
  }

  private void gameOver(Player player) {
    CharSequence text = "Игрок \"" + player.getName() + "\" победил!";
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    game.reset();
    refresh();
  }

  private void gameOver() {
    CharSequence text = "Ничья";
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    game.reset();
    refresh();
  }

  private void refresh() {
    Square[][] field = game.getField();

    for (int i = 0; i < 3; i++) {
      for (int j = 0, len2 = field[i].length; j < len2; j++) {
        if (field[i][j].getPlayer() == null) {
          buttons[i][j].setText(null);
        } else {
          buttons[i][j].setText(field[i][j].getPlayer().getName());
        }
      }
    }
  }
}
