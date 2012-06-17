package controllers;

import models.Bet;
import models.User;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

//@With(Secure.class)
public class Bets extends Controller {

//  public static void create() {
//    Bet bet = new Bet();
//    bet.user = Security.connectedUser();
//    bet.init();
//    render(bet);
//  }
//
//  public static void edit(Long id) {
//    Bet bet = Bet.findById(id);
//    bet.updateTables();
//    renderTemplate("Bets/create.html", bet);
//  }
//
//  public static void save(Bet bet) {
//    if (bet.tablesChanged()) {
//      bet.updateFinals();
//    }
//    bet.save();
//    Logger.info("Bet saved. Id=" + bet.id);
//    edit(bet.id);
//  }
//
//  public static void get(Long id) {
//    Bet bet = Bet.findById(id);
////    bet.updateTables();
//    render(bet);
//  }
//
//  public static void delete(Long id) {
//    Bet bet = Bet.findById(id);
//    bet.delete();
//    User user = Security.connectedUser();
//    Users.get(user.id);
//  }

  public static void list() {
    List<Bet> bets = Bet.findAll();
    Collections.sort(bets);
    User user = Security.connectedUser();
    Bet aBet = bets.get(0);
    render(bets, user, aBet);
  }

  public static void listWithBet(Long betId) {
    List<Bet> bets = Bet.findAll();
    Collections.sort(bets);
    User user = Security.connectedUser();
    Bet aBet = Bet.findById(betId);
//    render(bets, user, aBet);
    renderTemplate("Bets/list.html", bets, user, aBet);
  }
}
