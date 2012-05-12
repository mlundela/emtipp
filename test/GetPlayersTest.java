import models.Match;
import models.Players;
import models.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import play.test.UnitTest;

import java.io.IOException;

public class GetPlayersTest extends UnitTest {

    @Test
    public void doIt() throws IOException {
      String url;
      int []teamid = {56370, 58837,35, 39, 43, 47, 49, 66, 95, 109, 110, 64, 57451, 122, 127, 57166};
      for(int i=0; i<teamid.length; i++)  {
      url ="http://www.uefa.com/uefaeuro/season=2012/teams/team=" + teamid[i] + "/squad/index.html";
      System.out.println(url);
      Document doc = Jsoup.connect(url).get();
      Elements elements = doc.select(".squadlist");

      for (Element element : elements) {
        String PlayerName = element.select(".medTitle a").text();
        System.out.println(PlayerName);

      }
      }

    }

}
