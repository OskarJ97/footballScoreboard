package com.oskar.scoreboard.model;

import org.junit.jupiter.api.Test;

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

}