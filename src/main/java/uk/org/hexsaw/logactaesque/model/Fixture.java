package uk.org.hexsaw.logactaesque.model;

import javax.validation.constraints.NotBlank;

public class Fixture {

    @NotBlank(message = "Home team cannot be empty!")
    private String homeTeam;
    
    @NotBlank(message = "Away team cannot be empty!")
    private String awayTeam;

    public Fixture(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

}