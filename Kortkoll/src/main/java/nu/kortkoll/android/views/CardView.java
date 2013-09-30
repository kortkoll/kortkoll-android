package nu.kortkoll.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import nu.kortkoll.android.R;
import nu.kortkoll.android.models.Card;

/**
 * Created by henrik on 9/29/13.
 */
public class CardView extends LinearLayout {

  private TextView name;
  private TextView value;

  public CardView(Context context) {
    super(context);
    initialize();
  }

  public CardView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  public CardView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
  }

  private void initialize() {
    LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    layoutInflater.inflate(R.layout.view_card, this);

    name = (TextView) findViewById(R.id.cardName);
    value = (TextView) findViewById(R.id.cardValue);
  }

  public void setCard(Card card){
    name.setText(card.name);
    value.setText(card.purse);
  }
}
