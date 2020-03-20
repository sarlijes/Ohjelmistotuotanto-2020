
package ohtu;

public class PlayerStat {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;

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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", team: " + team + ", points: " + goals + " + " + assists;
    }

}
