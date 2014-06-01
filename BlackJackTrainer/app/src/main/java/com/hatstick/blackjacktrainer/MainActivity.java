package com.hatstick.blackjacktrainer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hatstick.blackjacktrainer.app.R;
import com.hatstick.blackjacktrainer.entity.Card;
import com.hatstick.blackjacktrainer.entity.Dealer;
import com.hatstick.blackjacktrainer.entity.Deck;
import com.hatstick.blackjacktrainer.entity.Player;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dealer = new Dealer();
        dealer.shuffle();

        players.add(new Player("Jordan"));
        players.add(new Player("Alex"));
        players.add(new Player("Aaron"));

        dealer.deal(players);

        for( Player player : players) {
            player.printHand();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
