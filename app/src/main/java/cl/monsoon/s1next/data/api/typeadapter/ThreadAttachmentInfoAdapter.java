package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import org.apache.commons.lang3.StringUtils;

import cl.monsoon.s1next.data.api.model.collection.Posts.ThreadAttachment.ThreadAttachmentInfo;
import cl.monsoon.s1next.util.StringUtil;

public final class ThreadAttachmentInfoAdapter {
    @FromJson
    public ThreadAttachmentInfo fromJson(OriginalThreadAttachmentInfo originalThreadAttachmentInfo) {
        String value = StringUtil.unescapeNonBreakingSpace(originalThreadAttachmentInfo.value)
                + StringUtils.defaultString(originalThreadAttachmentInfo.unit);
        return new ThreadAttachmentInfo(originalThreadAttachmentInfo.label, value);
    }

    private static final class OriginalThreadAttachmentInfo {
        @Json(name = "title")
        private final String label;

        @Json(name = "value")
        private final String value;

        @Json(name = "unit")
        private final String unit;

        private OriginalThreadAttachmentInfo(String label, String value, String unit) {
            this.label = label;
            this.value = value;
            this.unit = unit;
        }
    }
}
