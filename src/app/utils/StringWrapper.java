package app.utils;

import lombok.Getter;

@Getter
public class StringWrapper {
    private String value;

    public StringWrapper(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}