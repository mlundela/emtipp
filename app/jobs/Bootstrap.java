package jobs;

import models.Group;
import models.Match;
import models.Team;
import models.User;
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

      Logger.info("Create test user");
      User user = new User();
      user.email = "lundeland@gmail.com";
      user.save();


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

        Group group = Group.findOrCreateGroup(groupName);
        Team homeTeam = Team.findOrCreateTeam(homeTeamName);
        Team awayTeam = Team.findOrCreateTeam(awayTeamName);
        if (!group.teams.contains(homeTeam)) {
          group.teams.add(homeTeam);
        }
        if (!group.teams.contains(awayTeam)) {
          group.teams.add(awayTeam);
        }

        Match match = new Match(homeTeam, awayTeam, group);
        match.save();

        group.matches.add(match);
        group.save();

        Logger.info(match.toString());
      }
    }
  }
}
