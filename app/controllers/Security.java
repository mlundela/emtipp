package controllers;

import models.User;
import play.Logger;

public class Security extends Secure.Security {

  /**
   * This method is called during the authentication process. This is where you check if
   * the user is allowed to log in into the system. This is the actual authentication process
   * against a third party system (most of the time a DB).
   *
   * @param username
   * @param password
   * @return true if the authentication process succeeded
   */
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
    String connected = connected();
    Logger.info("Connected user (email): " + connected);
    if (connected == null) {
      return null;
    }
    User user = User.find("byEmail", connected).first();
    return user;
  }

}
