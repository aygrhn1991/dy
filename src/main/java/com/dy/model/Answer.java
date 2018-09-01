package com.dy.model;

public class Answer {
    public Answer() {
    }

    public Answer(int id, int question_id, boolean by_user, long time, String content) {
        this.id = id;
        this.question_id = question_id;
        this.by_user = by_user;
        this.time = time;
        this.content = content;
    }

    public int id;
    public int question_id;
    public boolean by_user;
    public long time;
    public String content;
}
