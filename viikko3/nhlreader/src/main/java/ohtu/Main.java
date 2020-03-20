package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        PlayerStat[] playersStats = mapper.fromJson(bodyText, PlayerStat[].class);

        Arrays.stream(playersStats)
                .filter(p -> p.getNationality().equalsIgnoreCase("FIN"))
                .sorted(((o1, o2) -> {
                    return  o2.getPoints() - o1.getPoints();
                }))
                .forEach(p -> System.out.println(p));
    }

}