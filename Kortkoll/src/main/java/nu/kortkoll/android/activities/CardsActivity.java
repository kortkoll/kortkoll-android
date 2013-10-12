package nu.kortkoll.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import java.util.List;

import nu.kortkoll.android.R;
import nu.kortkoll.android.models.Card;

public class CardsActivity extends FragmentActivity {

  private List<Card> cards;
  private MyAdapter mAdapter;
  private ViewPager mPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cards);
    cards = getIntent().getParcelableArrayListExtra(Card.PARCEL_KEYS);

    cards.addAll(cards);
    cards.addAll(cards);
    cards.addAll(cards);

    mAdapter = new MyAdapter(getSupportFragmentManager(), cards);

    mPager = (ViewPager) findViewById(R.id.pager);
    mPager.setAdapter(mAdapter);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.cards, menu);
    return true;
  }

  public static class MyAdapter extends FragmentStatePagerAdapter {
    List<Card> cards;

    public MyAdapter(FragmentManager fm, List<Card> cards) {
      super(fm);
      this.cards = cards;
    }

    @Override
    public int getCount() {
      return cards.size();
    }

    @Override
    public Fragment getItem(int position) {
      return CardFragment.newInstance(cards.get(position));
    }
  }

}
