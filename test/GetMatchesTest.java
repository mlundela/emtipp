import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.*;

import java.io.IOException;
import java.util.*;
import play.test.*;
import models.*;

public class GetMatchesTest extends UnitTest {

    @Test
    public void doIt() throws IOException {

      Document doc = Jsoup.connect("http://www.altomfotball.no/element.do?cmd=tournament&tournamentId=6270").get();
      Elements elements = doc.select(".sd_fixtures tr");

      for (Element element : elements) {
        String homeTeamName = element.select(".sd_fixtures_home_flag a").text();
        String awayTeamName = element.select(".sd_fixtures_away_flag a").text();

        Team homeTeam = Team.getTeam(homeTeamName);
        Team awayTeam = Team.getTeam(awayTeamName);

        Match match = new Match(homeTeam, awayTeam);
        match.save();

        System.out.println(match.toString());

      }

    }

}
