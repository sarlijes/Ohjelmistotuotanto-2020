package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("https://nhl27112019.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();

        Matcher mOr = query.oneOf(
                query.playsIn("EDM")
                        .hasAtLeast(20, "points").build(),
                query.playsIn("PHI")
                        .hasAtLeast(10, "assists")
                        .hasFewerThan(8, "goals").build()


        ).build();


        for (Player player : stats.matches(mOr)) {
            System.out.println(player);
        }
    }
}
