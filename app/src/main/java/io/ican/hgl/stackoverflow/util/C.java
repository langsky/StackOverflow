package io.ican.hgl.stackoverflow.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hgl on 16-12-12.
 */

public class C {
    public static final String QUESTION_SUMMARY_NARROW = "question-summary narrow";
    public static final String BASE_URL = "http://stackoverflow.com";

    //header menus
    public static final String HEADER_MENU_ID = "hmenus";
    public static final String MENU_QUESTIONS = "Questions";
    public static final String MENU_JOBS = "Jobs";
    public static final String MENU_DOCUMENTATION = "Documentation";
    public static final String MENU_TAGS = "Tags";
    public static final String MENU_USERS = "Users";
    public static final String MENU_BADGES = "Badges";
    public static final String MENU_ASK_QUESTION = "Ask Question";
    public static final String MENU_UNANSWERED = "unanswered";

    //main menu tabs
    public static final String MAIN_PAGE_INTERESTING = "interesting";
    public static final String MAIN_PAGE_FEATURED = "featured";
    public static final String MAIN_PAGE_HOT = "hot";
    public static final String MAIN_PAGE_WEEK = "week";
    public static final String MAIN_PAGE_MONTH = "month";

    public static final List<String> MAIN_PAGE_TABS = new ArrayList<>();
    public static final String TABS_ID = "tabs";
    public static final String YOU_ARE_HERE = "youarehere";
    public static final String PAGE_NUMBERS_CURRENT = "page-numbers current";

    static {
        MAIN_PAGE_TABS.add(MAIN_PAGE_INTERESTING);
        MAIN_PAGE_TABS.add(MAIN_PAGE_FEATURED);
        MAIN_PAGE_TABS.add(MAIN_PAGE_HOT);
        MAIN_PAGE_TABS.add(MAIN_PAGE_INTERESTING);
        MAIN_PAGE_TABS.add(MAIN_PAGE_WEEK);
        MAIN_PAGE_TABS.add(MAIN_PAGE_MONTH);
    }

    //question tabs
    public static final String QUESTIONS_NEWEST = "newest";
    public static final String QUESTIONS_FEATURED = "featured";
    public static final String QUESTIONS_FREQUENT = "frequent";
    public static final String QUESTIONS_VOTES = "votes";
    public static final String QUESTIONS_ACTIVE = "active";

    public static final List<String> QUESTION_TABS = new ArrayList<>();

    static {
        QUESTION_TABS.add(QUESTIONS_NEWEST);
        QUESTION_TABS.add(QUESTIONS_FEATURED);
        QUESTION_TABS.add(QUESTIONS_FREQUENT);
        QUESTION_TABS.add(QUESTIONS_VOTES);
        QUESTION_TABS.add(QUESTIONS_ACTIVE);
    }

    //unanswered tabs
    public static final String UNANSWERED_MY_TAGS = "my tags";
    public static final String UNANSWERED_NEWEST = "newest";
    public static final String UNANSWERED_VOTES = "votes";
    public static final String UNANSWERED_NO_ANSWERS = "no answers";

    public static final List<String> UNANSWERED_TABS = new ArrayList<>();

    static {
        UNANSWERED_TABS.add(UNANSWERED_MY_TAGS);
        UNANSWERED_TABS.add(UNANSWERED_NEWEST);
        UNANSWERED_TABS.add(UNANSWERED_VOTES);
        UNANSWERED_TABS.add(UNANSWERED_NO_ANSWERS);
    }
}
