
package ohtu;

public class PlayerStat {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;
    private int points;

    public PlayerStat(String name, String team, String nationality, int goals, int assists) {
        this.name = name;
        this.team = team;
        this.nationality = nationality;
        this.goals = goals;
        this.assists = assists;
    }

    public String getNationality() {
        return nationality;
    }

    public int getPoints() {
        return points + assists;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        setPoints(points + assists);
        return name + ", team: " + team + ", points: " + goals + " + " + assists + " = " + points;
    }

}
