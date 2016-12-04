package io.ican.hgl.stackoverflow.entity.question;

/**
 * Created by hgl on 16-12-1.
 */

public class SortType {

    public enum Question {
        QUESTIONS_NEWEST("newest"),
        QUESTIONS_FEATUED("featured"),
        QUESTIONS_FREQUENT("frequent"),
        QUESTIONS_VOTES("votes");

        private String text;

        Question(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public enum Unanswered {
        QUESTIONS_ACTIVE("active"),
        UNANSWERED_MY_TAGS("my tags"),
        UNANSWERED_NEWEST("newest"),
        UNANSWERED_VOTES("votes");
        private String text;

        Unanswered(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}


