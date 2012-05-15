import models.Match;
import models.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import play.test.UnitTest;

import java.io.IOException;

public class GetMatchesTest extends UnitTest {

  @Test
  public void doIt() throws IOException {

    Document doc = Jsoup.connect("http://www.altomfotball.no/element.do?cmd=tournament&tournamentId=6270").get();
    Elements elements = doc.select(".sd_fixtures tr");

    int count = 1;
    for (Element element : elements) {

      if (count++ > 24) {
        return;
      }

      String homeTeamName = element.select(".sd_fixtures_home_flag a").text();
      String awayTeamName = element.select(".sd_fixtures_away_flag a").text();
      String group = element.select(".sd_fixtures_tournament span").text().substring(7, 8);

      Team homeTeam = Team.getTeam(homeTeamName, group);
      Team awayTeam = Team.getTeam(awayTeamName, group);

      Match match = new Match(homeTeam, awayTeam);
      match.save();

      System.out.println(match.toString());

    }

  }

}
