package cl.monsoon.s1next.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;

import com.google.common.base.Preconditions;

import cl.monsoon.s1next.R;
import cl.monsoon.s1next.data.api.model.Captcha;
import cl.monsoon.s1next.databinding.DialogLoginCaptchaBinding;
import cl.monsoon.s1next.util.ViewUtil;

/**
 * A dialog lets user enter CAPTCHA in order to log in.
 */
public final class LoginCaptchaDialogFragment extends DialogFragment {

    public static final String TAG = LoginCaptchaDialogFragment.class.getName();

    private static final String ARG_USERNAME = "username";
    private static final String ARG_PASSWORD = "password";
    private static final String ARG_CAPTCHA = "captcha";

    public static LoginCaptchaDialogFragment newInstance(String username, String password,
                                                         Captcha captcha) {
        LoginCaptchaDialogFragment fragment = new LoginCaptchaDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_USERNAME, username);
        bundle.putString(ARG_PASSWORD, password);
        bundle.putParcelable(ARG_CAPTCHA, captcha);
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DialogLoginCaptchaBinding binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(),
                R.layout.dialog_login_captcha, null, false);
        Captcha captcha = getArguments().getParcelable(ARG_CAPTCHA);
        binding.setCaptchaPicUrl(Preconditions.checkNotNull(captcha).getPicUrl());

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.captcha)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.action_login, (dialog, which) -> {
                    String username = getArguments().getString(ARG_USERNAME);
                    String password = getArguments().getString(ARG_PASSWORD);
                    String captchaValue = binding.captcha.getText().toString();
                    LoginDialogFragment.newInstance(username, password, captcha.getHash(), captchaValue)
                            .show(getFragmentManager(), LoginDialogFragment.TAG);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        alertDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        ViewUtil.consumeRunnableWhenImeActionPerformed(binding.captcha, new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
                    }
                });

        return alertDialog;
    }
}
