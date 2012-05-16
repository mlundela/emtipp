import jobs.Bootstrap;
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
  public void doIt() throws Exception {
    new Bootstrap().doJob();
  }
}
