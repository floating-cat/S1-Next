package cl.monsoon.s1next.data.api.model.wrapper;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.model.AccountInfo;
import cl.monsoon.s1next.data.api.model.Result;
import cl.monsoon.s1next.data.api.model.collection.HasAccountInfo;

public final class ResultWrapper implements HasAccountInfo {
    @Json(name = "Message")
    private final Result result;

    @Json(name = "Variables")
    private final AccountInfo accountInfo;

    public ResultWrapper(Result result, AccountInfo accountInfo) {
        this.result = result;
        this.accountInfo = accountInfo;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultWrapper that = (ResultWrapper) o;
        return Objects.equal(result, that.result) &&
                Objects.equal(accountInfo, that.accountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result, accountInfo);
    }
}
