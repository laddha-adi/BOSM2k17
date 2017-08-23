package com.example.aditya.bosm2k17;

import java.util.ArrayList;

/**
 * Created by aditya on 8/23/2017.
 */

public class NonFixtureSportsData {
    private ArrayList <String> categoryName=new ArrayList<>();
    private ArrayList <String> categoryDesc=new ArrayList<>();
    private ArrayList<ArrayList<String>> categoryResult=new ArrayList<>();

    private String date;
    private String time;
    private String venue;
    private String round;

    public ArrayList<ArrayList<String>> getCategoryResult() {
        return categoryResult;
    }

    public void setCategoryResult(ArrayList<ArrayList<String>> categoryResult) {
        this.categoryResult = categoryResult;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(ArrayList<String> categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<String> getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(ArrayList<String> categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public NonFixtureSportsData(ArrayList<String> categoryName, ArrayList<String> categoryDesc, String date, String time, String venue, String round,ArrayList<ArrayList<String>> categoryResult) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryResult=categoryResult;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.round = round;
    }
}
