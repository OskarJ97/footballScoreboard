package com.oskar.scoreboard.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void shouldStartMatchWithZeroScore() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when
        scoreBoard.startMatch("Team A", "Team B");
        //then
        assertEquals(1, scoreBoard.getSummary().size());
        assertEquals(0, scoreBoard.getSummary().get(0).getHomeTeamScore());
        assertEquals(0, scoreBoard.getSummary().get(0).getAwayTeamScore());
    }

    @Test
    void shouldNotStartMatchWhenTeamIsAlreadyPlaying() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when+then
        scoreBoard.startMatch("Team A", "Team B");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.startMatch("Team B", "Team C"));
        assertTrue(ex.getMessage().contains("Match already started"));
    }

    @Test
    void shouldNotStartMatchWithTheSameTeam() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when+then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.startMatch("Team A", "Team A"));
        assertTrue(ex.getMessage().contains("Match should be played between different teams"));
    }

    @Test
    void shouldNotStartMatchWithNullTeam() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when+then
        assertThrows(NullPointerException.class, () ->
                scoreBoard.startMatch("Team A", null));
    }

    @Test
    void shouldFinishMatchByRightTeamNames() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        //when
        scoreBoard.finishMatch("Team A", "Team B");
        //then
        assertEquals(0, scoreBoard.getSummary().size());
    }

    @Test
    void shouldNotFinishMatchByWrongTeamNames() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        //when
        scoreBoard.finishMatch("Team A", "Team C");
        //then
        assertEquals(1, scoreBoard.getSummary().size());
    }

    @Test
    void shouldNotFinishMatchByNullTeam() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when+then
        assertThrows(NullPointerException.class, () ->
                scoreBoard.finishMatch(null, null));
    }

    @Test
    void shouldUpdateScore() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        //when
        scoreBoard.updateScore("Team A", "Team B", 1, 0);
        //then
        assertEquals(1, scoreBoard.getSummary().size());
        assertEquals(1, scoreBoard.getSummary().get(0).getHomeTeamScore());
        assertEquals(0, scoreBoard.getSummary().get(0).getAwayTeamScore());
    }

    @Test
    void shouldThrowErrorUponUpdateScoreWithWrongTeam() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        //when + then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.updateScore("Team A", "Team C", 1, 0));
        assertTrue(ex.getMessage().contains("Match not found"));
    }

    @Test
    void shouldThrowErrorUponUpdateScoreWithNullTeam() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        //when + then
        assertThrows(NullPointerException.class, () ->
                scoreBoard.updateScore("Team A", null, 1, 0));
    }

    @Test
    void shouldThrowErrorUponUpdateScoreWithNegativeScore() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        //when + then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.updateScore("Team A", "Team B", -1, 0));
        assertTrue(ex.getMessage().contains("Score cannot be negative"));
    }

    @Test
    void shouldReturnSortedSummary() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Team A", "Team B");
        scoreBoard.updateScore("Team A", "Team B", 2, 2);
        scoreBoard.startMatch("Team C", "Team D");
        scoreBoard.updateScore("Team C", "Team D", 3, 3);
        scoreBoard.startMatch("Team E", "Team F");
        scoreBoard.updateScore("Team E", "Team F", 4, 5);
        //when
        List<Match> matchesSummary = scoreBoard.getSummary();
        //then
        assertEquals(3, matchesSummary.size());
        Match match = matchesSummary.get(0);
        assertEquals("Team E", match.getHomeTeam());
        assertEquals("Team F", match.getAwayTeam());
        assertEquals(4, match.getHomeTeamScore());
        assertEquals(5, match.getAwayTeamScore());

        match = matchesSummary.get(1);
        assertEquals("Team C", match.getHomeTeam());
        assertEquals("Team D", match.getAwayTeam());
        assertEquals(3, match.getHomeTeamScore());
        assertEquals(3, match.getAwayTeamScore());

        match = matchesSummary.get(2);
        assertEquals("Team A", match.getHomeTeam());
        assertEquals("Team B", match.getAwayTeam());
        assertEquals(2, match.getHomeTeamScore());
        assertEquals(2, match.getAwayTeamScore());
    }

}