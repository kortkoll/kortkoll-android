package nu.kortkoll.android.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

import nu.kortkoll.android.R;

public class LoadingDialogFragment extends DialogFragment {
  public static final String LOADING_DIALOG_TAG = "loading-dialog";

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Dialog dialog = new Dialog(getActivity(), R.style.Dialog_Loading);

    dialog.setContentView(R.layout.loading_fragment);
    dialog.setCanceledOnTouchOutside(false);
    dialog.setCancelable(false);

    dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
      @Override
      public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
          return true;
        }
        return false;
      }
    });

    return dialog;
  }

  public static void showLoadingDialogWithHolo(FragmentManager fragmentManager) {
    LoadingDialogFragment fragment = (LoadingDialogFragment) fragmentManager.findFragmentByTag(LOADING_DIALOG_TAG);
    if (fragment != null) {
      fragment.dismiss();
    }
    new LoadingDialogFragment().show(fragmentManager, LOADING_DIALOG_TAG);
  }

  public static void hideLoadingDialog(FragmentManager fragmentManager){
    DialogFragment fragment = (DialogFragment) fragmentManager.findFragmentByTag(LOADING_DIALOG_TAG);
    if(fragment != null){
      fragment.dismissAllowingStateLoss();
    }
  }

}
