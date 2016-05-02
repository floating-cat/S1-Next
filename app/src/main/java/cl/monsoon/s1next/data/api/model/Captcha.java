package cl.monsoon.s1next.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@SuppressWarnings("UnusedDeclaration")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Captcha implements Parcelable {

    public static final Parcelable.Creator<Captcha> CREATOR = new Parcelable.Creator<Captcha>() {
        @Override
        public Captcha createFromParcel(Parcel source) {
            return new Captcha(source);
        }

        @Override
        public Captcha[] newArray(int i) {
            return new Captcha[i];
        }
    };

    @JsonProperty("sechash")
    private String hash;

    @JsonProperty("seccode")
    private String picUrl;

    public Captcha() {}

    private Captcha(Parcel source) {
        hash = source.readString();
        picUrl = source.readString();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captcha captcha = (Captcha) o;
        return Objects.equal(hash, captcha.hash) &&
                Objects.equal(picUrl, captcha.picUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hash, picUrl);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hash);
        dest.writeString(picUrl);
    }
}
