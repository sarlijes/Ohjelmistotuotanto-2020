package statistics;

import statistics.matcher.*;

public class QueryBuilder {

    Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }

    public Matcher build() {
        return this.matcher;
    }

    public QueryBuilder hasAtLeast(int amount, String pointRecordType) {
        this.matcher = new And(this.matcher, new HasAtLeast(amount, pointRecordType));
        return this;
    }

    public QueryBuilder hasFewerThan(int amount, String pointRecordType) {
        this.matcher = new And(this.matcher, new HasFewerThan(amount, pointRecordType));
        return this;
    }
}
