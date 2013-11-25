package nu.kortkoll.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import nu.kortkoll.android.R;
import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.models.Product;

/**
 * Created by henrik on 10/11/13.
 */
public class CardFragment extends Fragment {

  private Card card;

  public static Fragment newInstance(Card card) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(Card.PARCEL_KEY, card);

    CardFragment fragment = new CardFragment();
    fragment.setArguments(bundle);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setRetainInstance(true);

    card = getArguments().getParcelable(Card.PARCEL_KEY);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.view_card, container, false);
    {
      TextView name = (TextView) view.findViewById(R.id.cardName);
      TextView value = (TextView) view.findViewById(R.id.cardValue);
      TextView owner = (TextView) view.findViewById(R.id.cardOwner);

      name.setText(card.name);
      value.setText(Math.round(card.purse) + "kr");
      owner.setText(card.owner);
    }
    LinearLayout products = (LinearLayout) view.findViewById(R.id.cardProducts);

    for (Product product : card.products) {
      if(!product.active){
        continue;
      }

      View v = inflater.inflate(R.layout.view_product, products);
      TextView type = (TextView) v.findViewById(R.id.productType);
      TextView value = (TextView) v.findViewById(R.id.productValue);
      TextView dates = (TextView) v.findViewById(R.id.productDates);

      type.setText(product.type);
      value.setText(product.price);
      dates.setText(product.getDateRange());
    }

    return view;
  }
}
