import jobs.Bootstrap;
import jobs.UpdateMatches;
import org.junit.Test;
import play.test.UnitTest;

public class GetMatchesTest extends UnitTest {

  @Test
  public void doIt() throws Exception {
    new Bootstrap().doJob();
  }

  @Test
  public void testUpdateMatches() throws Exception {
    new UpdateMatches().doJob();
  }
}
