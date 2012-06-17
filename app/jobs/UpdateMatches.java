package jobs;

import models.Match;
import models.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.Logger;
import play.jobs.Job;
import play.jobs.On;

@On("0 0/5 16-23 * * ?")
public class UpdateMatches extends Job {

  public void doJob() throws Exception {

    Logger.info("UPDATE MATCHES");

    Document doc = Jsoup.connect("http://www.altomfotball.no/element.do?cmd=tournament&tournamentId=6270").get();
    Elements elements = doc.select(".sd_fixtures tr");

    int count = 1;
    for (Element element : elements) {

      if (count++ > 24) {
        return;
      }

      String homeTeamName = element.select(".sd_fixtures_home_flag a").text();
      String awayTeamName = element.select(".sd_fixtures_away_flag a").text();
      String result = element.select(".sd_fixtures_score a").text();

      if (result.contains("-")) {

        result = result.replace("(", "");
        result = result.replace(")", "");

        Team homeTeam = Team.find("name = ?", homeTeamName).first();
        Team awayTeam = Team.find("name = ?", awayTeamName).first();
        Match match = Match.find("homeTeam = ? and awayTeam = ?", homeTeam, awayTeam).first();

        if (match == null) {
          Logger.error("Could not find match: " + homeTeamName + " - " + awayTeamName);
        } else {
          Logger.info("Update match: " + homeTeam + " - " + awayTeam + " " + result);
          match.isPlayed = true;
          match.homeTeamScore = Integer.valueOf(result.substring(0, 1));
          match.awayTeamScore = Integer.valueOf(result.substring(result.length() - 1, result.length()));
          match.save();
          Logger.info("Saved match: " + match.toString());
        }
      }

    }
  }
}
