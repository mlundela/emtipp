package controllers;

import models.User;
import play.mvc.Controller;

public class Users extends Controller {

  public static void create() {
    User user = new User();
    render(user);
  }

  public static void save(User user) {
    user.save();
    get(user.id);
  }

  public static void get(Long id) {
    User user = User.findById(id);
    render(user);
  }
}
