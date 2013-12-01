package nu.kortkoll.android.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import nu.kortkoll.android.R;

/**
 * Created by henrik on 9/29/13.
 */
public class LoginLogoView extends LinearLayout {

  private View card1;
  private View card2;
  private View card3;
  private ImageView logo;
  private AnimationDrawable frameAnimation;

  public LoginLogoView(Context context) {
    super(context);
    initialize();
  }

  public LoginLogoView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  public LoginLogoView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
  }

  private void initialize() {
    LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    layoutInflater.inflate(R.layout.view_login_logo, this);

    card1 = findViewById(R.id.login_view_logo_card1);
    card2 = findViewById(R.id.login_view_logo_card2);
    card3 = findViewById(R.id.login_view_logo_card3);
    logo = (ImageView) findViewById(R.id.login_view_logo);
    logo.setBackgroundResource(R.drawable.logo_animation_complete);
    frameAnimation = (AnimationDrawable) logo.getBackground();
  }

  public void startAnimation() {
    ObjectAnimator animation1 = ObjectAnimator.ofFloat(card2, "rotation", 0, 15);
    ObjectAnimator animation2 = ObjectAnimator.ofFloat(card3, "rotation", 0, -15);
    ViewHelper.setPivotX(card2, dpToPx(196 / 2));
    ViewHelper.setPivotX(card3, dpToPx(196 / 2));
    ViewHelper.setPivotY(card2, dpToPx(136 / 2));
    ViewHelper.setPivotY(card3, dpToPx(136 / 2));

    AnimatorSet set = new AnimatorSet();
    set.playTogether(animation1, animation2);
    set.setDuration(500);
    set.start();

    frameAnimation.start();
  }

  public float dpToPx(int dp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
  }
}
