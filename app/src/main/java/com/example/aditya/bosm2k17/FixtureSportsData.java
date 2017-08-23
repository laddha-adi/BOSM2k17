package com.example.aditya.bosm2k17;

/**
 * Created by aditya on 8/23/2017.
 */

public class FixtureSportsData {

    private String TeamA;
    private String TeamB;
    private String date;
    private String time;
    private String venue;
    private String round;
    private String winner;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getTeamA() {
        return TeamA;
    }

    public void setTeamA(String teamA) {
        TeamA = teamA;
    }

    public String getTeamB() {
        return TeamB;
    }

    public void setTeamB(String teamB) {
        TeamB = teamB;
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

    public FixtureSportsData(String teamA, String teamB, String date, String time, String venue, String round, String winner) {
        TeamA = teamA;
        TeamB = teamB;
        this.winner=winner;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.round = round;
    }
}
