package nu.kortkoll.android.API;

import android.content.Context;
import android.util.Log;

import java.util.List;

import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.models.LoginDetails;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by henrik on 9/29/13.
 */
public class APIQuery {

  private static final String API = "https://www.kortkoll.nu/api";
  private static KortKollService kortKollService;

  private static KortKollService getService(){
    if(kortKollService == null){
      RestAdapter restAdapter = new RestAdapter.Builder()
              .setServer(API)
              .setLogLevel(RestAdapter.LogLevel.FULL)

              .build();

      kortKollService = restAdapter.create(KortKollService.class);
    }

    return kortKollService;
  }

  public static void getCards(Context context, String username, String password, final Callback<List<Card>> cb) {
    LoginDetails details = new LoginDetails();
    details.username = username;
    details.password = password;
    getService().login(details, new Callback<Void>() {
      @Override
      public void success(Void v, Response response) {
        Log.e("Response", response.toString());
        getService().getCards(cb);
      }

      @Override
      public void failure(RetrofitError retrofitError) {
        Log.e("Response", retrofitError.toString());
      }
    });
  }
}
