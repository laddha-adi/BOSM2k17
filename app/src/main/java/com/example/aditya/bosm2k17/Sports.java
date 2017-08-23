package com.example.aditya.bosm2k17;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditya on 8/23/2017.
 */

public class Sports {
    public  List<List<String>>SportsRoundList;
    public  List<List<NonFixtureSportsData>> nonFixtureSportsDataList;
    public   List<List<FixtureSportsData>> fixtureSportsDataList;

    public  List<List<String>> getSportsRoundList() {
        return SportsRoundList;
    }

    public void setSportsRoundList(List<List<String>> sportsRoundList) {
        SportsRoundList = sportsRoundList;
    }

    public List<List<NonFixtureSportsData>> getNonFixtureSportsDataList() {
        return nonFixtureSportsDataList;
    }

    public void setNonFixtureSportsDataList(List<List<NonFixtureSportsData>> nonFixtureSportsDataList) {
        this.nonFixtureSportsDataList = nonFixtureSportsDataList;
    }

    public  List<List<FixtureSportsData>> getFixtureSportsDataList() {
        return fixtureSportsDataList;
    }

    public void setFixtureSportsDataList(List<List<FixtureSportsData>> fixtureSportsDataList) {
        this.fixtureSportsDataList = fixtureSportsDataList;
    }

    public Sports() {


        SportsRoundList= new ArrayList<>();
        nonFixtureSportsDataList = new ArrayList<>();
        fixtureSportsDataList = new ArrayList<>();


       for(int i=0;i<26;i++) {
           List<String> nullList = new ArrayList<>();
           SportsRoundList.add(nullList);
           List<NonFixtureSportsData> nullList2 = new ArrayList<>();
           nonFixtureSportsDataList.add(nullList2);
           List<FixtureSportsData> nullList3 = new ArrayList<>();
           fixtureSportsDataList.add(nullList3);

       }
    }
}

