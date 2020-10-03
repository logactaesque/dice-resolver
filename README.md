# Logactaesque Dice Resolver

This service resolves which dice are to be employed when playing a Logactaesque match fixture. The dice it returns are aligned with dice rolled as part of the [the Logactaesque Dice Roller Service](https://github.com/logactaesque/dice-roller)

The application is a [Spring Boot](https://spring.io/projects/spring-boot) Java application. To run locally, clone the repository, navigate into the directory and then:

    # run the application on port 8080
    mvn spring-boot:run &

The service presently holds a single endpoint called *resolve*, hence a GET on:

    localhost:8080/resolve 

with a JSON payload of: 

    {"homeTeam":"Liverpool", "awayTeam":"West Bromwich Albion"}

will return:

    {"homeTeam":"Liverpool", "awayTeam":"West Bromwich Albion", "homeDice":"BLUE", "awayDice":"RED"}

Hence for a given match, the _blue_ dice will be used to determine home goals, and _red_ for away.  If a home team and away team name are supplied, it will (for the moment) __always__ return the above JSON with a response code of 200, however in the near future the resolver will have additional logic to allow dice to be selected based upon:

- Competition
- Recent form
- Number of points between teams (for league matches)


If either team is missing in the request, then the response code is 400 and you will see something akin to the following error message:

    {"error":"Missing away team"}

