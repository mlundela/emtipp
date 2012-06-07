package controllers;

import models.User;

public class Security extends Secure.Security {

  static boolean authenticate(String username, String password) {
    User user = User.find("byEmail", username).first();
    return user != null && user.password.equals(password);
  }

  /**
   * This method is called after a successful authentication.
   * You need to override this method if you with to perform specific actions (eg. Record the time the user signed in)
   */
  static void onAuthenticated() {
    Users.get(connectedUser().id);
  }


  /**
   * This method is called after a successful sign off.
   * You need to override this method if you wish to perform specific actions (eg. Record the time the user signed off)
   */
  static void onDisconnected() {
    Application.index();
  }

  public static User connectedUser() {
    return User.find("byEmail", connected()).first();
  }

}
