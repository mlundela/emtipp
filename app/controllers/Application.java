package controllers;

import models.Bet;
import models.User;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

  public static void index() {

    boolean connected = Security.isConnected();
    User user = Security.connectedUser();
    if (connected && user != null) {
      Users.get(user.id);
    } else {
      List<Bet> bets = Bet.findAll();
      render(connected, bets);
    }
  }

}