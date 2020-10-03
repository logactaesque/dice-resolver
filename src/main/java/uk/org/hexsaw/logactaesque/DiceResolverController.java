package uk.org.hexsaw.logactaesque;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.hexsaw.logactaesque.model.Dice;
import uk.org.hexsaw.logactaesque.model.DiceResolved;
import uk.org.hexsaw.logactaesque.model.Fixture;

@Controller
public class DiceResolverController {

    @GetMapping(path = "/resolve")
    public @ResponseBody DiceResolved resolve(@Valid @RequestBody Fixture fixture ) {
        return new DiceResolved(fixture.getHomeTeam(), Dice.BLUE, fixture.getAwayTeam(), Dice.RED);
    }
}
