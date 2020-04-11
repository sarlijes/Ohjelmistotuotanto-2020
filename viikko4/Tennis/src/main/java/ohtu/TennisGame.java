package ohtu;

import java.util.HashMap;

public class TennisGame {

    private Player playerOne;
    private Player playerTwo;

    private HashMap<Integer, String> scoreStringMap;

    public TennisGame(String player1Name, String player2Name) {
        this.playerOne = new Player(player1Name);
        this.playerTwo = new Player(player2Name);
        this.scoreStringMap = new HashMap<>();
        scoreStringMap.put(0, "Love");
        scoreStringMap.put(1, "Fifteen");
        scoreStringMap.put(2, "Thirty");
        scoreStringMap.put(3, "Forty");
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(playerOne.getName()))
            playerOne.addPoint();
        else
            playerTwo.addPoint();
    }

    public String getTieScore(int score) {
        StringBuilder sb = new StringBuilder();

        if (score == 0) sb.append(scoreStringMap.get(0));
        else if (score == 1) sb.append(scoreStringMap.get(1));
        else if (score == 2) sb.append(scoreStringMap.get(2));
        else if (score == 3) sb.append(scoreStringMap.get(3));

        if (sb.toString().isEmpty()) sb.append("Deuce");
        else sb.append("-All");
        return sb.toString();
    }

    public String getScore() {
        String score = "";
        int tempScore;

        if (playerOne.getScore() == playerTwo.getScore()) {
            score = getTieScore(playerOne.getScore());

        } else if (playerOne.getScore() >= 4 || playerTwo.getScore() >= 4) {
            int minusResult = playerOne.getScore() - playerTwo.getScore();
            if (minusResult == 1) score = "Advantage player1";
            else if (minusResult == -1) score = "Advantage player2";
            else if (minusResult >= 2) score = "Win for player1";
            else score = "Win for player2";
        } else {
            int i = 1;
            while (i < 3) {
                if (i == 1) tempScore = playerOne.getScore();
                else {
                    score += "-";
                    tempScore = playerTwo.getScore();
                }
                score += scoreStringMap.get(tempScore);
                i++;
            }
        }
        return score;
    }
}