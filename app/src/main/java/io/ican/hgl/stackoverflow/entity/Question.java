package io.ican.hgl.stackoverflow.entity;

import java.util.ArrayList;

/**
 * Created by hgl on 16-12-1.
 */

public abstract class Question {

    protected String id = "";
    protected String votes = "";
    protected String answers = "";
    protected String views = "";
    protected String summary = "";
    protected String createTime = "";
    protected String modifyTime = "";
    protected String
    protected String url = "";
    protected ArrayList<String> tags = new ArrayList<>();
    protected String reputationScore = "";
    protected String excerpt = "";

    @Override
    public String toString() {
        return String.format("id:%s votes:%s answers:%s views:%s summary:%s reputationScore:%s url:%s", id, votes, answers, views, summary, reputationScore, url);
    }
}
