package cl.monsoon.s1next.data.api.typeadapter;

import android.graphics.Color;
import android.support.v4.util.SimpleArrayMap;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.monsoon.s1next.data.api.model.Post;

public final class PostAdapter {
    private static final SimpleArrayMap<String, String> COLOR_NAME_MAP;

    static {
        COLOR_NAME_MAP = new SimpleArrayMap<>();

        COLOR_NAME_MAP.put("sienna", "#A0522D");
        COLOR_NAME_MAP.put("darkolivegreen", "#556B2F");
        COLOR_NAME_MAP.put("darkgreen", "#006400");
        COLOR_NAME_MAP.put("darkslateblue", "#483D8B");
        COLOR_NAME_MAP.put("indigo", "#4B0082");
        COLOR_NAME_MAP.put("darkslategray", "#2F4F4F");
        COLOR_NAME_MAP.put("darkred", "#8B0000");
        COLOR_NAME_MAP.put("darkorange", "#FF8C00");
        COLOR_NAME_MAP.put("slategray", "#708090");
        COLOR_NAME_MAP.put("dimgray", "#696969");
        COLOR_NAME_MAP.put("sandybrown", "#F4A460");
        COLOR_NAME_MAP.put("yellowgreen", "#9ACD32");
        COLOR_NAME_MAP.put("seagreen", "#2E8B57");
        COLOR_NAME_MAP.put("mediumturquoise", "#48D1CC");
        COLOR_NAME_MAP.put("royalblue", "#4169E1");
        COLOR_NAME_MAP.put("orange", "#FFA500");
        COLOR_NAME_MAP.put("deepskyblue", "#00BFFF");
        COLOR_NAME_MAP.put("darkorchid", "#9932CC");
        COLOR_NAME_MAP.put("pink", "#FFC0CB");
        COLOR_NAME_MAP.put("wheat", "#F5DEB3");
        COLOR_NAME_MAP.put("lemonchiffon", "#FFFACD");
        COLOR_NAME_MAP.put("palegreen", "#98FB98");
        COLOR_NAME_MAP.put("paleturquoise", "#AFEEEE");
        COLOR_NAME_MAP.put("lightblue", "#ADD8E6");

        // https://code.google.com/p/android/issues/detail?id=75953
        COLOR_NAME_MAP.put("white", "#FFFFFF");
    }

    @FromJson
    public Post fromJson(OriginalPost originalPost) {
        String reply = mapColorNamesToHex(originalPost.reply);
        reply = correctImageTags(reply);
        reply = replaceAttachmentTagsWithImages(reply, originalPost.originalAttachmentMap);
        return new Post(originalPost.id, originalPost.authorName, originalPost.authorId, reply, originalPost.count,
                originalPost.datetime);
    }

    /**
     * {@link Color} doesn't support all HTML color names.
     * So {@link android.text.Html#fromHtml(String)} won't
     * map some color names for replies.
     * We need to map these color names to their hex value.
     */
    private String mapColorNamesToHex(String reply) {
        // example: color="sienna"
        // matcher.group(0): color="sienna"
        // matcher.group(1): sienna
        Matcher matcher = Pattern.compile("color=\"([a-zA-Z]+)\"").matcher(reply);

        StringBuffer stringBuffer = new StringBuffer();
        String color;
        while (matcher.find()) {
            // get color hex value for its color name
            color = COLOR_NAME_MAP.get(matcher.group(1).toLowerCase(Locale.US));
            if (color == null) {
                continue;
            }
            // append part of the string and its color hex value
            matcher.appendReplacement(stringBuffer, "color=\"" + color + "\"");
        }
        matcher.appendTail(stringBuffer);

        return stringBuffer.toString();
    }

    private String correctImageTags(String reply) {
        // Replaces "imgwidth" with "img width",
        // because some img tags from Discuz backend aren't correct.
        // This may be the best way to deal with it though
        // we may replace something wrong by accident.
        return reply.replaceAll("<imgwidth=\"", "<img width=\"");
    }

    /**
     * Replaces attachment tags with HTML img tags
     * in order to display attachment images in TextView.
     * <p>
     * Also concats the missing img tag from attachment.
     * See https://github.com/floating-cat/S1-Next/issues/7
     */
    private String replaceAttachmentTagsWithImages(String reply,
                                                   Map<Integer, OriginalAttachment> attachmentMap) {
        // the Discuz' API is broken and would return incorrect
        // form for post attachments if this post has no attachments
        if (attachmentMap == null) {
            return reply;
        }

        for (Map.Entry<Integer, OriginalAttachment> entry : attachmentMap.entrySet()) {
            OriginalAttachment attachment = entry.getValue();
            String imgTag = "<img src=\"" + attachment.urlPrefix + attachment.urlSuffix + "\" />";
            String replyCopy = reply;
            // get the original string if there is nothing to replace
            reply = reply.replace("[attach]" + entry.getKey() + "[/attach]", imgTag);
            //noinspection StringEquality
            if (reply == replyCopy) {
                // concat the missing img tag
                reply = reply + imgTag;
            }
        }
        return reply;
    }

    private static final class OriginalPost {
        @Json(name = "pid")
        private final String id;

        @Json(name = "author")
        private final String authorName;

        @Json(name = "authorid")
        private final String authorId;

        @Json(name = "message")
        private final String reply;

        @Json(name = "number")
        private final String count;

        @Json(name = "dbdateline")
        @SecondsToMilliSeconds
        private final long datetime;

        @Json(name = "attachments")
        private final Map<Integer, OriginalAttachment> originalAttachmentMap;

        private OriginalPost(String id, String authorName, String authorId, String reply, String count,
                             long datetime, Map<Integer, OriginalAttachment> attachmentMap) {
            this.id = id;
            this.authorName = authorName;
            this.authorId = authorId;
            this.reply = reply;
            this.count = count;
            this.datetime = datetime;
            this.originalAttachmentMap = attachmentMap;
        }
    }

    private static final class OriginalAttachment {
        @Json(name = "url")
        private final String urlPrefix;

        @Json(name = "attachment")
        private final String urlSuffix;

        private OriginalAttachment(String urlSuffix, String urlPrefix) {
            this.urlSuffix = urlSuffix;
            this.urlPrefix = urlPrefix;
        }
    }
}
