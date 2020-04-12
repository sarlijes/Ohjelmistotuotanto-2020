package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("https://nhl27112019.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();

        Matcher m = query.playsIn("NYR")
                .hasAtLeast(5, "goals")
                .hasFewerThan(8, "goals")
                .build();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
