package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.model.AccountInfo;

public final class AccountInfoAdapter {
    @FromJson
    public AccountInfo fromJson(OriginalAccountInfo originalAccountInfo) {
        return originalAccountInfo.getAccountInfo();
    }

    static class OriginalAccountInfo {
        @Json(name = "member_uid")
        private String uid;

        @Json(name = "member_username")
        private String username;

        @Json(name = "formhash")
        private String authenticityToken;

        @Json(name = "readaccess")
        private int permission;

        AccountInfo getAccountInfo() {
            return new AccountInfo(uid, username, authenticityToken, permission);
        }
    }
}
