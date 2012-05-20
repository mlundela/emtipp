package controllers;

import models.Match;
import models.MatchBet;
import models.TGroup;
import models.UserBet;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserBets extends Controller {

  public static void create() {

    UserBet userBet = new UserBet();
    Map<TGroup, ArrayList<MatchBet>> groups = new HashMap<TGroup, ArrayList<MatchBet>>();
    for (TGroup group : TGroup.<TGroup>findAll()) {
      ArrayList<MatchBet> groupMatches = new ArrayList<MatchBet>();
      for (Match match : group.matches) {
        MatchBet matchBet = new MatchBet(userBet, match);
        userBet.matchBets.add(matchBet);
        groupMatches.add(matchBet);
      }
      groups.put(group, groupMatches);
    }
    render(userBet, groups);
  }

  public static void save(UserBet userBet) {
    userBet.save();
    get(userBet.getId());
  }

  public static void get(Long id) {
//    UserBet userBet = UserBet.findById(id);
//    render(userBet);
    render();
  }
}
