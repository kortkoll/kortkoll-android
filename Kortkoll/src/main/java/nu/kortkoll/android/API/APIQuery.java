package nu.kortkoll.android.API;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import nu.kortkoll.android.MySSLSocketFactory;
import nu.kortkoll.android.models.Card;

/**
 * Created by henrik on 9/29/13.
 */
public class APIQuery {

  //This class is a mess, refactor of expanded.
  private static final String API = "https://www.kortkoll.nu/api";

  public static void getCards(Context context, final String username, final String password, final CardListener listener) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        AndroidHttpClient androidClient = AndroidHttpClient.newInstance(null);
        HttpClient client = sslClient(androidClient);
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());

        HttpPost post = new HttpPost(API + "/session");
        HttpGet get = new HttpGet(API + "/cards");

        JSONObject json = new JSONObject();

        try {
          json.put("username", username);
          json.put("password", password);
          StringEntity se = new StringEntity(json.toString());
          se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
          post.setEntity(se);
        } catch (Exception e) {
          e.printStackTrace();
        }

        String responseText = null;
        try {
          client.execute(post, localContext);
          HttpResponse response = client.execute(get, localContext);
          responseText = getText(response.getEntity().getContent());
          Log.e("response", responseText);
        } catch (IOException e) {
          e.printStackTrace();
        }

        androidClient.close();

        Gson gson = new Gson();
        List<Card> cards = gson.fromJson(responseText, new TypeToken<List<Card>>() {
        }.getType());

        listener.onSuccess(cards);
      }
    }).start();
  }

  private static HttpClient sslClient(HttpClient client) {
    try {
      X509TrustManager tm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }
      };
      SSLContext ctx = SSLContext.getInstance("TLS");
      ctx.init(null, new TrustManager[]{tm}, null);
      SSLSocketFactory ssf = new MySSLSocketFactory(ctx);
      ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      ClientConnectionManager ccm = client.getConnectionManager();
      SchemeRegistry sr = ccm.getSchemeRegistry();
      sr.register(new Scheme("https", ssf, 443));
      return new DefaultHttpClient(ccm, client.getParams());
    } catch (Exception ex) {
      return null;
    }
  }

  public static String getText(InputStream in) {
    String text = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
      text = sb.toString();
    } catch (Exception ex) {

    } finally {
      try {

        in.close();
      } catch (Exception ex) {
      }
    }

    return text;
  }

  public interface CardListener {
    public void onSuccess(List<Card> cards);

    public void onFailure(String error);
  }
}
