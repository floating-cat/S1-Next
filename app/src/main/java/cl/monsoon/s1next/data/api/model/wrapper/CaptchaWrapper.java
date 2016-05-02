package cl.monsoon.s1next.data.api.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import cl.monsoon.s1next.data.api.model.Captcha;

@SuppressWarnings("UnusedDeclaration")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CaptchaWrapper {

    @JsonProperty("Variables")
    private Captcha captcha;

    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaptchaWrapper that = (CaptchaWrapper) o;
        return Objects.equal(captcha, that.captcha);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(captcha);
    }
}
