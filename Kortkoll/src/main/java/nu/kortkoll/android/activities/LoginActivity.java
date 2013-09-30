package nu.kortkoll.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import nu.kortkoll.android.API.APIQuery;
import nu.kortkoll.android.R;
import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.views.LoginBoxView;

public class LoginActivity extends Activity implements LoginBoxView.OnLoginListener {

  private LoginBoxView loginBoxView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    loginBoxView = (LoginBoxView) findViewById(R.id.LoginLoginView);

    loginBoxView.setLoginListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public void onLoginPressed(final String username, final String password) {
    APIQuery.getCards(this, username, password, new APIQuery.CardListener() {
      @Override
      public void onSuccess(List<Card> cards) {
        Intent intent = new Intent(LoginActivity.this, CardsActivity.class);
        intent.putParcelableArrayListExtra(Card.PARCEL_KEYS, new ArrayList<Parcelable>(cards));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
      }

      @Override
      public void onFailure(String error) {

      }
    });
  }
}
