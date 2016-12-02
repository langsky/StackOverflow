package io.ican.hgl.stackoverflow.entity.question;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by hgl on 16-12-1.
 */

public class Summary {

    public static final int UNANSWERED = 0;
    public static final int ANSWERED = 1;
    public static final int ANSWERED_ACCEPTED = 2;


    public String id;
    public String votes;
    public Answers answers;
    public String views;
    public String summary;
    public String createTime;
    public String modifyTime;
    public String user;
    public String url;
    public ArrayList<String> tags;
    public String reputationScore;
    public String excerpt;

    private String[] tagsToJson(ArrayList<String> tags) {
        String[] result = new String[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            String s = tags.get(i);
            result[i] = "\"" + s + "\"";
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"votes\":\"%s\", \"answers\":\"%s\", \"views\":\"%s\", \"summary\":\"%s\", " +
                        "\"createTime\":\"%s\", \"modifyTime\":\"%s\", \"user\":\"%s\", " +
                        "\"excerpt\":\"%s\", \"tags\":" + Arrays.toString(tagsToJson(tags)) +
                        "\"reputationScore\":\"%s\", \"url\":\"%s\"", id, votes, answers.count, views, summary, createTime,
                modifyTime, user, excerpt, reputationScore, url);
    }

    public static class Answers {
        public String count;
        public int state;
    }

}
