package com.oskar.scoreboard.model;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    int homeTeamScore;
    int awayTeamScore;
    private final long sequence;

        public Match(String homeTeam, String awayTeam, long sequence) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeTeamScore = 0;
            this.awayTeamScore = 0;
            this.sequence = sequence;
        }

        void updateScore(int homeTeamScore, int awayTeamScore) {
            this.homeTeamScore = homeTeamScore;
            this.awayTeamScore = awayTeamScore;
        }

        public int getTotalScore() {
            return homeTeamScore + awayTeamScore;
        }

        public long getSequence() {
            return sequence;
        }
}
