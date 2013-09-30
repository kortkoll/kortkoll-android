package nu.kortkoll.android.activities;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;

import java.util.List;

import nu.kortkoll.android.R;
import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.views.CardView;

public class CardsActivity extends Activity {

  private List<Card> cards;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cards);
    cards = getIntent().getParcelableArrayListExtra(Card.PARCEL_KEYS);

    CardView cv = new CardView(this);
    cv.setCard(cards.get(0));
    ((ViewGroup) findViewById(R.id.root)).addView(cv);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.cards, menu);
    return true;
  }

}
