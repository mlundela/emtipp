package controllers;

import models.Bet;
import models.Group;
import models.Team;
import play.mvc.Controller;

import java.util.List;

public class Bets extends Controller {

  public static void create() {
    Bet bet = new Bet();
    bet.init();
    render(bet);
  }

  public static void edit(Long id) {
    Bet bet = Bet.findById(id);
    renderTemplate("Bets/create.html", bet);
  }

  public static void save(Bet bet) {

    // todo: sjekk at q1-q8 er riktig, og viss ikkje oppdater finaler.

    List<Group> tables = bet.tables();
    Team q1 = tables.get(0).table().get(0);
    Team q2 = tables.get(1).table().get(1);
    Team q3 = tables.get(1).table().get(0);
    Team q4 = tables.get(0).table().get(1);
    Team q5 = tables.get(2).table().get(2);
    Team q6 = tables.get(3).table().get(3);
    Team q7 = tables.get(3).table().get(2);
    Team q8 = tables.get(2).table().get(3);

    if (bet.q1 != q1 ||
        bet.q2 != q2 ||
        bet.q3 != q3 ||
        bet.q4 != q4 ||
        bet.q5 != q5 ||
        bet.q6 != q6 ||
        bet.q7 != q7 ||
        bet.q8 != q8) {

      bet.updateFinals();
    }

    bet.save();
    edit(bet.id);
  }

  public static void get(Long id) {
//    UserBet userBet = UserBet.findById(id);
//    render(userBet);
    render();
  }

}
