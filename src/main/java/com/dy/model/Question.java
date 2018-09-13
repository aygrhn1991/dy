package com.dy.model;

import java.util.List;

public class Question {
    public int t_id;
    public String t_title;
    public int t_user_id;
    public long t_time;
    public int t_scan;
    public int t_sort;
    public boolean t_top;
    public boolean t_solved;
    public int t_scan_origin;
    public List<Integer> tags;
}
