package io.mocky.cameras.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Класс для представления данных SourceData.
 * @autor safin.v
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceData {
    private String urlType;
    private String videoUrl;

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
