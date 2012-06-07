import jobs.BootstrapPlayers;
import org.junit.Test;
import play.test.UnitTest;

import java.io.IOException;

public class GetPlayersTest extends UnitTest {

  @Test
  public void doIt() throws IOException, InterruptedException {
    new BootstrapPlayers().doIt();
  }
}
