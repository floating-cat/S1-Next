package cl.monsoon.s1next.view.dialog;

import android.os.Bundle;
import android.text.TextUtils;

import cl.monsoon.s1next.R;
import cl.monsoon.s1next.data.api.model.Captcha;
import cl.monsoon.s1next.data.api.model.wrapper.CaptchaWrapper;
import rx.Observable;

/**
 * A {@link ProgressDialogFragment} gets a login CAPTCHA url when needed.
 */
public final class LoginCaptchaPicUrlRequestDialogFragment extends ProgressDialogFragment<CaptchaWrapper> {

    public static final String TAG = LoginCaptchaPicUrlRequestDialogFragment.class.getName();

    private static final String ARG_USERNAME = "username";
    private static final String ARG_PASSWORD = "password";

    public static LoginCaptchaPicUrlRequestDialogFragment newInstance(String username, String password) {
        LoginCaptchaPicUrlRequestDialogFragment fragment =
                new LoginCaptchaPicUrlRequestDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_USERNAME, username);
        bundle.putString(ARG_PASSWORD, password);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected Observable<CaptchaWrapper> getSourceObservable() {
        return mS1Service.getLoginCaptcha();
    }

    @Override
    protected void onNext(CaptchaWrapper data) {
        String username = getArguments().getString(ARG_USERNAME);
        String password = getArguments().getString(ARG_PASSWORD);
        Captcha captcha = data.getCaptcha();
        if (captcha == null ||
                TextUtils.isEmpty(captcha.getHash()) || (TextUtils.isEmpty(captcha.getPicUrl()))) {
            LoginDialogFragment.newInstance(username, password, null, null).show(
                    getFragmentManager(), LoginDialogFragment.TAG);
        } else {
            LoginCaptchaDialogFragment.newInstance(username, password, data.getCaptcha()).show(
                    getFragmentManager(), LoginCaptchaDialogFragment.TAG);
        }
    }

    @Override
    protected CharSequence getProgressMessage() {
        return getText(R.string.dialog_progress_message_login);
    }
}
