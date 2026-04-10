package com.oskar.scoreboard.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();
    private long sequence = 0;

    void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam, sequence++));
    }

    void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match -> homeTeam.equals(match.getHomeTeam()) && awayTeam.equals(match.getAwayTeam()));
    }

    List<Match> getSummary() {
        return matches;
    }

    public ScoreBoard() {
    }

}
