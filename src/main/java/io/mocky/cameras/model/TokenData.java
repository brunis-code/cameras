package io.mocky.cameras.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Класс для представления данных TokenData.
 * @autor safin.v
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenData {
    private String value;
    private int ttl;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
