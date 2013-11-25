package nu.kortkoll.android.credentials;

import android.content.Context;
import android.content.SharedPreferences;

import nu.kortkoll.android.R;

/**
 * Created by henrik on 11/24/13.
 */
public class Store {

  //http://android-developers.blogspot.com/2013/02/using-cryptography-to-store-credentials.html
  //Password to SL is not sensitive enough to do something more advanced

  public static void storeUsername(Context context, String username) {
    getSharedPreferences(context).edit().putString(context.getString(R.string.store_username), username).commit();
  }

  public static void storePassword(Context context, String password) {
    getSharedPreferences(context).edit().putString(context.getString(R.string.store_password), password).commit();
  }

  public static String getUsername(Context context) {
    return getSharedPreferences(context).getString(context.getString(R.string.store_username), "");
  }

  public static String getPassword(Context context) {
    return getSharedPreferences(context).getString(context.getString(R.string.store_password), "");
  }

  private static SharedPreferences getSharedPreferences(Context context) {
    return context.getSharedPreferences("nu.kortkoll.android", Context.MODE_PRIVATE);
  }
}
