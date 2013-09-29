package nu.kortkoll.android.activities;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import java.util.List;

import nu.kortkoll.android.API.APIQuery;
import nu.kortkoll.android.R;
import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.views.LoginBoxView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
  public void onLoginPressed(String username, String password) {
    APIQuery.getCards(this, username, password, new Callback<List<Card>>() {
      @Override
      public void success(List<Card> cards, Response response) {
        Log.e("Response", response.toString());
      }

      @Override
      public void failure(RetrofitError retrofitError) {
        Log.e("Failure", retrofitError.toString());
      }
    });
  }
}
