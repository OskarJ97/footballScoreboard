Live Football World Cup Score Board that shows matches and scores as a simple library.

---

The Scoreboard supports the following operations:
- startMatch (initial score is 0–0)
- finishMatch (remove it from the scoreboard)
- updateScore (for an existing match)
- getSummary (summary of matches sorted by):
    - Total score (descending)
    - Matches with the same total score are ordered by sequence counter value (sequence tracks insertion order)

---

ScoreBoard - Domain object responsible for managing the matches and their scores.

Match - Domain object representing a football match between two teams, including their names and current score.

---

### Design decisions and assumptions:
- In-memory storage: The Scoreboard will use an in-memory data structure (List<Match> matches) to store matches.
This allows quick access and easy implementation but this approach is not suitable for large datasets and large-scale
applications.
- Match ordering: Sequence of matches is used to track the order they were added to the scoreboard. This allows easy
sorting by total score and recency.
- Match uniqueness: Each match is uniquely identified by the combination of team names. Team cannot play against itself 
as well. This simplifies the implementation but may not cover all real-world scenarios.
- Error handling: Input validation is minimal by design. Nulls for object parameters for team names are not allowed.
Blank names and other validation rules are considered out of scope for this implementation. Validation can be handled
for example at the API boundary layer in a real application or custom checkers can be added to the Scoreboard class if
needed.

---

### Example usage:
```java
ScoreBoard scoreBoard = new ScoreBoard();
scoreBoard.startMatch("Team A", "Team B");
scoreBoard.updateScore("Team A", "Team B", 2, 1);
scoreBoard.startMatch("Team C", "Team D");
scoreBoard.finishMatch("Team C", "Team D");
scoreBoard.getSummary();
```

---

### Example case:
As an example, being the current data in the system:
- Mexico - Canada: 0 – 5
- Spain - Brazil: 10 – 2
- Germany - France: 2 – 2
- Uruguay - Italy: 6 – 6
- Argentina - Australia: 3 - 1

The summary would provide with the following information:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

---

### How to run
To see example usage and verify the functionality of the Scoreboard implementation you can:
- run unit tests in ScoreBoardTest.java
- mvn clean test (from footballScoreboard directory)

The tests cover starting and finishing matches, updating scores and retrieving the summary of matches.

---

### Tech Stack
- Java 17
- JUnit 5
- Maven