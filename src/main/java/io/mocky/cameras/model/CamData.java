package io.mocky.cameras.model;
/**
 * Класс для представления агрегированных данных по камере.
 * @autor safin.v
 */
public class CamData {
    private long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private int ttl;

    public CamData() {
    }

    public CamData(Camera camera, SourceData sourceData, TokenData tokenData) {
        this.id = camera.getId();
        this.urlType = sourceData.getUrlType();
        this.videoUrl = sourceData.getVideoUrl();
        this.value = tokenData.getValue();
        this.ttl = tokenData.getTtl();
    }

    public long getId() {
        return id;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }
}
