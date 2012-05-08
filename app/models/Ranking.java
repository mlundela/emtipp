package models;

public enum Ranking {

  GROUP_PLAY,
  QUARTER_FINAL,
  SEMI_FINAL,
  RUNNER_UP,
  WINNER;

  public int getValue() {
    switch (this) {
      case QUARTER_FINAL: return 1;
      case SEMI_FINAL: return 2;
      case RUNNER_UP: return 4;
      case WINNER: return 10;
      default: return 0;
    }
  }
}
