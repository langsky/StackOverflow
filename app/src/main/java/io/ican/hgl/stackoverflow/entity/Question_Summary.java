package io.ican.hgl.stackoverflow.entity;


import java.util.ArrayList;


/**
 * Created by swd1 on 16-11-30.
 */

public class Question_Summary {
    public String id = "";
    public String votes = "";
    public String answers = "";
    public String views = "";
    public String summary = "";
    public String time = "";
    public String url = "";
    public ArrayList<String> tags = new ArrayList<>();
    public String reputationScore = "";
    public String excerpt = "";

    @Override
    public String toString() {
        return String.format("id:%s votes:%s answers:%s views:%s summary:%s reputationScore:%s url:%s", id, votes, answers, views, summary, reputationScore, url);
    }
}
