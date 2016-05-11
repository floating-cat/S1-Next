package cl.monsoon.s1next.data.pref;

public enum DownloadStrategy {
    NO, WIFI, ALWAYS;

    private boolean isDownload(boolean hasWifi) {
        return equals(WIFI) && hasWifi
                || equals(ALWAYS);
    }
}
