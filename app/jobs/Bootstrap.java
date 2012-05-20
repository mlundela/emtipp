package jobs;

import models.Match;
import models.TGroup;
import models.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

  public void doJob() throws Exception {

    if (Team.count() == 0) {

      Logger.info("INIT DATABASE");

      Document doc = Jsoup.connect("http://www.altomfotball.no/element.do?cmd=tournament&tournamentId=6270").get();
      Elements elements = doc.select(".sd_fixtures tr");

      int count = 1;
      for (Element element : elements) {

        if (count++ > 24) {
          return;
        }

        String homeTeamName = element.select(".sd_fixtures_home_flag a").text();
        String awayTeamName = element.select(".sd_fixtures_away_flag a").text();
        String groupName = element.select(".sd_fixtures_tournament span").text().substring(7, 8);

        TGroup group = TGroup.findOrCreateGroup(groupName);
        Team homeTeam = Team.findOrCreateTeam(homeTeamName);
        Team awayTeam = Team.findOrCreateTeam(awayTeamName);
        group.teams.add(homeTeam);
        group.teams.add(awayTeam);

        Match match = new Match(homeTeam, awayTeam);
        match.save();

        group.matches.add(match);
        group.save();

        Logger.info(match.toString());
      }
    }
  }
}
