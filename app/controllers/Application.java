package controllers;

import models.User;
import play.mvc.Controller;

public class Application extends Controller {

  public static void index() {
    boolean connected = Security.isConnected();
    User user = Security.connectedUser();
    if (connected && user != null) {
      Users.get(user.id);
    } else {
      render(connected);
    }
  }

}