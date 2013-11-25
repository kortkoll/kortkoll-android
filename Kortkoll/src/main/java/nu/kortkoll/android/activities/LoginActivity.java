package nu.kortkoll.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import nu.kortkoll.android.API.APIQuery;
import nu.kortkoll.android.R;
import nu.kortkoll.android.credentials.Store;
import nu.kortkoll.android.fragments.LoadingDialogFragment;
import nu.kortkoll.android.models.Card;
import nu.kortkoll.android.models.Product;
import nu.kortkoll.android.views.LoginBoxView;
import nu.kortkoll.android.views.LoginLogoView;

public class LoginActivity extends FragmentActivity implements LoginBoxView.OnLoginListener {

  private LoginBoxView loginBoxView;
  private Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    loginBoxView = (LoginBoxView) findViewById(R.id.LoginLoginView);
    loginBoxView.setLoginListener(this);
    loginBoxView.setVisibility(View.INVISIBLE);

    findViewById(R.id.login_logo).setVisibility(View.INVISIBLE);
    animateBuss();
  }

  @Override
  protected void onStart() {
    super.onStart();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        animateLoginBox();
        animateLogo();
      }
    }, 1000);
  }

  private void animateLoginBox() {
    ObjectAnimator pushUp = ObjectAnimator.ofFloat(loginBoxView, "translationY", loginBoxView.getHeight() + dpToPx(100), 0);
    pushUp.setInterpolator(new BounceInterpolator());
    pushUp.setDuration(800);
    pushUp.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        loginBoxView.setVisibility(View.VISIBLE);
      }
    });

    pushUp.start();
  }

  private void animateLogo() {
    final LoginLogoView logo = (LoginLogoView) findViewById(R.id.login_logo);
    ObjectAnimator drop = ObjectAnimator.ofFloat(logo, "translationY", -logo.getHeight() - dpToPx(100), 0);
    drop.setInterpolator(new BounceInterpolator());
    drop.setDuration(800);
    drop.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        logo.setVisibility(View.VISIBLE);
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            logo.startAnimation();
          }
        }, 300);
      }
    });

    drop.start();
  }

  public void animateBuss() {
    final int START = (int) dpToPx(1000);
    final int STOP1 = (int) dpToPx(10);
    final int STOP2 = (int) dpToPx(-1000);
    View bus = findViewById(R.id.login_bus);
    ObjectAnimator part1 = ObjectAnimator.ofFloat(bus, "translationX", START, STOP1);
    part1.setInterpolator(new DecelerateInterpolator());
    part1.setDuration(6000);

    ObjectAnimator part2 = ObjectAnimator.ofFloat(bus, "translationX", STOP1, STOP1);
    part2.setDuration(3000);


    ObjectAnimator part3 = ObjectAnimator.ofFloat(bus, "translationX", STOP1, STOP2);
    part3.setInterpolator(new AccelerateInterpolator());
    part3.setDuration(6000);

    final AnimatorSet bussMovement = new AnimatorSet();
    bussMovement.playSequentially(part1, part2, part3);

    bussMovement.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        bussMovement.start();
      }
    });

    bussMovement.start();
  }


  @Override
  public void onLoginPressed(final String username, final String password) {
    LoadingDialogFragment.showLoadingDialogWithHolo(getSupportFragmentManager());
    APIQuery.getCards(this, username, password, new APIQuery.CardListener() {
      @Override
      public void onSuccess(List<Card> cards) {
        LoadingDialogFragment.hideLoadingDialog(getSupportFragmentManager());
        storeCredentials(username, password);
        cards.get(0).products.add(Product.getMockProduct());
        startCardActivity(cards);
      }

      @Override
      public void onFailure(String error) {
        LoadingDialogFragment.hideLoadingDialog(getSupportFragmentManager());
        Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG);
      }
    });
  }

  private void storeCredentials(String username, String password) {
    Store.storeUsername(this, username);
    Store.storePassword(this, password);
  }

  private void startCardActivity(List<Card> cards) {
    Intent intent = new Intent(this, CardsActivity.class);
    intent.putParcelableArrayListExtra(Card.PARCEL_KEYS, new ArrayList<Parcelable>(cards));
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  public float dpToPx(int dp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
  }
}
