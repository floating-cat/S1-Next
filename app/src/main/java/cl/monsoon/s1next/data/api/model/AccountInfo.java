package cl.monsoon.s1next.data.api.model;

import com.google.common.base.Objects;

public final class AccountInfo {
    private final String uid;

    private final String username;

    private final String authenticityToken;

    private final int permission;

    public AccountInfo(String uid, String username, String authenticityToken, int permission) {
        this.uid = uid;
        this.username = username;
        this.authenticityToken = authenticityToken;
        this.permission = permission;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthenticityToken() {
        return authenticityToken;
    }

    public int getPermission() {
        return permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInfo that = (AccountInfo) o;
        return permission == that.permission &&
                Objects.equal(uid, that.uid) &&
                Objects.equal(username, that.username) &&
                Objects.equal(authenticityToken, that.authenticityToken);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid, username, authenticityToken, permission);
    }
}
