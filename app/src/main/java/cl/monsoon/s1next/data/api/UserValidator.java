package cl.monsoon.s1next.data.api;

import android.text.TextUtils;

import cl.monsoon.s1next.data.User;
import cl.monsoon.s1next.data.api.model.AccountInfo;
import cl.monsoon.s1next.data.api.model.collection.HasAccountInfo;

public final class UserValidator {
    private static final String INVALID_UID = "0";

    private final User mUser;

    public UserValidator(User user) {
        this.mUser = user;
    }

    /**
     * Intercepts the data in order to check whether current user's login status
     * has changed and update user's status if needed.
     *
     * @param d   The data we want to intercept.
     * @param <D> The data type.
     */
    public <D> void validateIntercept(D d) {
        if (d instanceof HasAccountInfo) {
            validate(((HasAccountInfo) d).getAccountInfo());
        }
    }

    /**
     * Checks current user's login status and updates {@link User}'s in our app.
     */
    public void validate(AccountInfo accountInfo) {
        final boolean logged = mUser.isLogged();
        String uid = accountInfo.getUid();
        if (INVALID_UID.equals(uid) || TextUtils.isEmpty(uid)) {
            if (logged) {
                // if account has expired
                mUser.setUid(null);
                mUser.setName(null);
                mUser.setLogged(false);
            }
        } else {
            if (!logged) {
                // if account has logged
                mUser.setUid(uid);
                mUser.setName(accountInfo.getUsername());
                mUser.setLogged(true);
            }
        }
        mUser.setPermission(accountInfo.getPermission());
        mUser.setAuthenticityToken(accountInfo.getAuthenticityToken());
    }
}
