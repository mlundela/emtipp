package controllers;

import models.Bet;
import models.Team;
import play.mvc.Controller;
import view.EditBet;

public class Bets extends Controller {

  public static void create() {
    EditBet bet = new EditBet();
    bet.init();
    render(bet);
  }

  public static void edit(Long id) {
    Bet bet = Bet.findById(id);
    bet.updateTables();
    renderTemplate("Bets/create.html", bet);
  }

  public static void save(Bet bet) {

    // sjekk at q1-q8 er riktig, og viss ikkje oppdater finaler.

    bet.updateTables();

    Team q1 = bet.tables.get(0).table().get(0);
    Team q2 = bet.tables.get(1).table().get(1);
    Team q3 = bet.tables.get(1).table().get(0);
    Team q4 = bet.tables.get(0).table().get(1);
    Team q5 = bet.tables.get(2).table().get(0);
    Team q6 = bet.tables.get(3).table().get(1);
    Team q7 = bet.tables.get(3).table().get(0);
    Team q8 = bet.tables.get(2).table().get(1);

    if (!bet.q1.id.equals(q1.id) ||
        !bet.q2.id.equals(q2.id) ||
        !bet.q3.id.equals(q3.id) ||
        !bet.q4.id.equals(q4.id) ||
        !bet.q5.id.equals(q5.id) ||
        !bet.q6.id.equals(q6.id) ||
        !bet.q7.id.equals(q7.id) ||
        !bet.q8.id.equals(q8.id)) {

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
