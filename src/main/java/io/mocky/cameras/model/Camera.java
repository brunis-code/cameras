package io.mocky.cameras.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Класс для представления данных по камере.
 * @autor safin.v
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Camera {
    private long id;
    private String sourceDataUrl;
    private String tokenDataUrl;


    public long getId() {
        return id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSourceDataUrl(String sourceDataUrl) {
        this.sourceDataUrl = sourceDataUrl;
    }

    public void setTokenDataUrl(String tokenDataUrl) {
        this.tokenDataUrl = tokenDataUrl;
    }

    @Override
    public String toString() {
        return "Camera [id=" + id + ", sourceDataUrl=" + sourceDataUrl + ", tokenDataUrl=" + tokenDataUrl + "]";
    }
}
