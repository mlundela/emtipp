package controllers;

import models.Bet;
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
    if (bet.tablesChanged()) {
      bet.updateFinals();
    }
    bet.save();
    edit(bet.id);
  }

  public static void get(Long id) {
    Bet bet = Bet.findById(id);
    bet.updateTables();
    render();
  }

}
