package io.ican.hgl.stackoverflow.entity.tab;

/**
 * Created by hgl on 16-12-1.
 */

public class Tab {
    public String text = "";
    public String url = "";

    @Override
    public String toString() {
        return String.format("text:\"%s\",url:\"%s\"", text, url);
    }
}