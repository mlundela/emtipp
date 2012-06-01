package view;

import models.Group;
import models.MatchBet;

import java.util.List;

public class GroupBet {

  public Group group;
  public List<MatchBet> matchBets;

  public GroupBet(Group group, List<MatchBet> matchBets) {
    this.group = group;
    this.matchBets = matchBets;
  }
}
