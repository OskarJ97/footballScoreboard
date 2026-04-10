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
        // Implementation to finish a match
    }

    List<Match> getSummary() {
        // Implementation to return match summary
        return new ArrayList<>();
    }

    public ScoreBoard() {
    }

}
