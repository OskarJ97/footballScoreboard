package com.oskar.scoreboard.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ScoreBoard {

    private static final Comparator<Match> MATCH_RESULT_COMPARATOR = Comparator.comparingInt(Match::getTotalScore)
            .reversed().thenComparing(Match::getSequence, Comparator.reverseOrder());
    private final List<Match> matches = new ArrayList<>();
    private long sequence = 0;

    public void startMatch(String homeTeam, String awayTeam) {
        Objects.requireNonNull(homeTeam);
        Objects.requireNonNull(awayTeam);
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Match should be played between different teams: " + homeTeam);
        }
        if (isAnyTeamAlreadyPlaying(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("Match already started for teams: " + homeTeam + " vs " + awayTeam);
        }
        matches.add(new Match(homeTeam, awayTeam, sequence++));
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Objects.requireNonNull(homeTeam);
        Objects.requireNonNull(awayTeam);
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        Objects.requireNonNull(homeTeam);
        Objects.requireNonNull(awayTeam);
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative: " + homeTeamScore + ":" + awayTeamScore);
        }
        Match match = findMatch(homeTeam, awayTeam);

        match.updateScore(homeTeamScore, awayTeamScore);
    }

    public List<Match> getSummary() {
        return matches.stream().sorted(MATCH_RESULT_COMPARATOR).toList();
    }

    public ScoreBoard() {
    }

    private boolean isAnyTeamAlreadyPlaying(String homeTeam, String awayTeam) {
        return matches.stream().anyMatch(match ->
                homeTeam.equals(match.getHomeTeam()) || homeTeam.equals(match.getAwayTeam()) ||
                        awayTeam.equals(match.getAwayTeam()) || awayTeam.equals(match.getHomeTeam()));
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.stream().filter(
                        m -> homeTeam.equals(m.getHomeTeam()) && awayTeam.equals(m.getAwayTeam()))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("Match not found for teams: " + homeTeam + " vs " + awayTeam));
    }
}
