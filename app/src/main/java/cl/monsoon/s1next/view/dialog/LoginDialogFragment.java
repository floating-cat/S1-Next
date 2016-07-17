package cl.monsoon.s1next.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.monsoon.s1next.R;
import rx.Observable;

/**
 * A {@link ProgressDialogFragment} posts a request to login to server.
 */
public final class LoginDialogFragment extends ProgressDialogFragment<String> {

    public static final String TAG = LoginDialogFragment.class.getName();

    private static final String ARG_USERNAME = "username";
    private static final String ARG_PASSWORD = "password";
    private static final String ARG_CAPTCHA_HASH = "captcha_hash";
    private static final String ARG_CAPTCHA_VALUE = "captcha_value";

    /**
     * For desktop is "login_succeed".
     * For mobile is "location_login_succeed_mobile".
     * "login_succeed" when already has logged in.
     */
    private static final String STATUS_AUTH_SUCCESS = "location_login_succeed_mobile";
    private static final String STATUS_AUTH_SUCCESS_ALREADY = "login_succeed";

    public static LoginDialogFragment newInstance(String username, String password,
                                                  @Nullable String captchaHash,
                                                  @Nullable String captchaValue) {
        LoginDialogFragment fragment = new LoginDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_USERNAME, username);
        bundle.putString(ARG_PASSWORD, password);
        bundle.putString(ARG_CAPTCHA_HASH, captchaHash);
        bundle.putString(ARG_CAPTCHA_VALUE, captchaValue);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected Observable<String> getSourceObservable() {
        String username = getArguments().getString(ARG_USERNAME);
        String password = getArguments().getString(ARG_PASSWORD);
        String captchaHash = getArguments().getString(ARG_CAPTCHA_HASH);
        String captcha = getArguments().getString(ARG_CAPTCHA_VALUE);
        return mS1Service.login(username, password, captchaHash, captcha).map(string -> {
//            // the authenticity token is not fresh after login
//            resultWrapper.getAccount().setAuthenticityToken(null);
//            mUserValidator.validate(resultWrapper.getAccount());
            return string;
        });
    }

    @Override
    protected void onNext(String data) {
        Pattern pattern = Pattern.compile("<p>(.+)<script");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            String message = matcher.group(1);
            if (message.contains("欢迎您回来")) {
                showShortTextAndFinishCurrentActivity(message);
            } else {
                showShortText(message);
            }
        } else {
            showShortText(getString(R.string.message_unknown_error));
        }
//        if (result.getStatus().equals(STATUS_AUTH_SUCCESS)
//                || result.getStatus().equals(STATUS_AUTH_SUCCESS_ALREADY)) {
//            showShortTextAndFinishCurrentActivity(result.getMessage());
//        } else {
//            showShortText(result.getMessage());
//        }
    }

    @Override
    protected CharSequence getProgressMessage() {
        return getText(R.string.dialog_progress_message_login);
    }
}
