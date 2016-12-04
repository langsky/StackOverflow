package io.ican.hgl.stackoverflow.entity.menu;

/**
 * Created by hgl on 16-12-4.
 * 二级网页有如下的内容
 */

public enum MenuType {

    MAIN_QUESTIONS("Questions"),

    MAIN_JOBS("Jobs"),

    MAIN_DOCUMENTATION("Documentation"),

    MAIN_TAGS("Tags"),

    MAIN_USERS("Users"),

    MAIN_BADGES("Badges"),

    MAIN_ASK_QUESTION("Ask Question"),

    MAIN_UNANSWERED("unanswered");

    private String text;

    MenuType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
