package cl.monsoon.s1next.data.pref;

public enum TotalDownloadCacheSize {
    // 32MB, 64MB, 128MB
    LOW(32), NORMAL(64), HIGH(128);

    private final int byteSize;

    TotalDownloadCacheSize(int mbSize) {
        this.byteSize = mbSize * 1000 * 1000;
    }

    public int getByteSize() {
        return byteSize;
    }
}
