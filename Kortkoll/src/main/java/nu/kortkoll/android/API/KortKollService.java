package nu.kortkoll.android.API;

import java.util.List;

import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.models.LoginDetails;
import retrofit.Callback;
import retrofit.client.Header;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by henrik on 9/29/13.
 */
public interface KortKollService {

  @Headers("Accept:application/json")
  @POST("/session")
  void login(@Body LoginDetails details, Callback<Void> cb);

  @Headers("Accept:application/json")
  @POST("/cards")
  void getCards(Callback<List<Card>> cb);

}

