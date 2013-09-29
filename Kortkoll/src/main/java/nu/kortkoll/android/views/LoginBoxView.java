package nu.kortkoll.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import nu.kortkoll.android.R;

/**
 * Created by henrik on 9/29/13.
 */
public class LoginBoxView extends LinearLayout {

  private EditText usernameEdittext;
  private EditText passwordEdittext;
  private Button loginButton;

  private OnLoginListener loginListener;

  public LoginBoxView(Context context) {
    super(context);
    initialize();
  }

  public LoginBoxView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  public LoginBoxView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
  }

  public void setLoginListener(OnLoginListener listener) {
    this.loginListener = listener;
  }

  private void initialize() {
    LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    layoutInflater.inflate(R.layout.view_login_box, this);

    usernameEdittext = (EditText) findViewById(R.id.usernameEdittext);
    passwordEdittext = (EditText) findViewById(R.id.passwordEdittext);
    loginButton = (Button) findViewById(R.id.loginButton);

    loginButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        onLoginClicked();
      }
    });
  }

  private void onLoginClicked() {
    String username = String.valueOf(usernameEdittext.getText());
    String passowrd = String.valueOf(passwordEdittext.getText());
    loginListener.onLoginPressed(username, passowrd);
  }


  public interface OnLoginListener {
    public void onLoginPressed(String username, String password);
  }
}
