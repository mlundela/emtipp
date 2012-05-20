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
        int[] teamid = {56370, 58837, 35, 39, 43, 47, 49, 66, 95, 109, 110, 64, 57451, 122, 127, 57166};
        for (int i = 0; i < teamid.length; i++) {
            url = "http://www.uefa.com/uefaeuro/season=2012/teams/team=" + teamid[i] + "/squad/index.html";
            // System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".squadlist");

            for (Element element : elements) {
                Elements players = element.select(".medTitle a");
                for (Element player : players) {


                    String PlayerURL = player.attr("href");
                    Document Doc = Jsoup.parse(PlayerURL);
                    String PlayerID = Doc.body().text();
                    int tempID = Integer.parseInt(PlayerID.split("=")[2].split("/")[0]);
                    playerBio(tempID);
                    //System.out.println("navn = " + PlayerName + "ID = " + tempID);


                }
            }
        }

    }

    public static void playerBio(int PlayerID) throws IOException {

        String url = "http://www.uefa.com/uefaeuro/season=2012/teams/player=" + PlayerID + "/index.html";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".infoBox");

        for (Element element : elements) {

            Elements Bios = element.select(".innerText li");
            for (Element Bio : Bios) {
                String name = Bios.get(0).text().split(": ")[1];
                String Posision = Bios.get(1).text().split(": ")[1];
                String Nationality = Bios.get(3).text().split(": ")[1];
                String Club = Bios.get(4).text().split(": ")[1];

                System.out.println(name);
                System.out.println(Posision);
                System.out.println(Nationality);
                System.out.println(Club);

            }

        }
    }


}
