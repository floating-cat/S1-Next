package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.apache.commons.lang3.StringEscapeUtils;

public final class XmlDecodedAdapter {
    @FromJson
    @XmlDecoded
    public String fromJson(String string) {
        return StringEscapeUtils.unescapeXml(string);
    }

    @ToJson
    public String toJson(@XmlDecoded String string) {
        throw new UnsupportedOperationException();
    }
}
