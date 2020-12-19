package uk.org.hexsaw.logactaesque.model;

import java.time.LocalDateTime;

public class DiceResolved {

    private LocalDateTime when;

    private String homeTeam;
    private String awayTeam;

    private Dice homeDice;
    private Dice awayDice;

    public DiceResolved() {
    }

    public DiceResolved(String homeTeam, Dice homeDice, String awayTeam, Dice awayDice) {
        this.when = LocalDateTime.now();
        this.homeTeam = homeTeam;
        this.homeDice = homeDice;
        this.awayTeam = awayTeam;
        this.awayDice = awayDice;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Dice getHomeDice() {
        return homeDice;
    }

    public void setHomeDice(Dice homeDice) {
        this.homeDice = homeDice;
    }

    public Dice getAwayDice() {
        return awayDice;
    }

    public void setAwayDice(Dice awayDice) {
        this.awayDice = awayDice;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
}
