package cl.monsoon.s1next.data.pref;

public enum AvatarResolutionStrategy {
    LOW_RESOLUTION, HIGH_RESOLUTION_WHEN_WIFI, HIGH_RESOLUTION;

    private boolean isHigherResolution(boolean hasWifi) {
        return equals(HIGH_RESOLUTION_WHEN_WIFI) && hasWifi
                || equals(HIGH_RESOLUTION);
    }
}
