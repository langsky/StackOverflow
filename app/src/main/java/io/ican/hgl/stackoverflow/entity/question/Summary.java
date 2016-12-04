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
    public String modifyTime;
    public String user;
    public String url;
    public ArrayList<String> tags;
    public String reputationScore;
    public String excerpt;
    public String bountyIndicator;

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
                        "\"modifyTime\":\"%s\", \"user\":\"%s\", " +
                        "\"excerpt\":\"%s\", \"tags\":" + Arrays.toString(tagsToJson(tags)) +
                        "\"reputationScore\":\"%s\", \"url\":\"%s\"" +
                        "\"bounty-indicator\":\"%s\" ", id, votes, answers.count, views, summary,
                modifyTime, user, excerpt, reputationScore, url, bountyIndicator);
    }

    public static class Answers {
        public String count;
        public int state;
    }

}
