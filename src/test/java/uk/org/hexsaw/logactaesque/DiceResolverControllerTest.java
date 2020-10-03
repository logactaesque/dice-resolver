package uk.org.hexsaw.logactaesque;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = DiceResolverConfiguration.class)
@AutoConfigureMockMvc
@WebAppConfiguration 
public class DiceResolverControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    private final String HOME_TEAM = "Wolves";
    private final String AWAY_TEAM = "WBA";
    private final String DEFAULT_HOME_DICE = "BLUE";
    private final String DEFAULT_AWAY_DICE = "RED";
    private final String JSON_WOLVES_VS_WBA = "{\"homeTeam\": \"Wolves\", \"awayTeam\": \"WBA\"}";
    private final String JSON_MISSING_HOME_TEAM = "{\"homeTeam\": \"\", \"awayTeam\": \"WBA\"}";
    private final String JSON_MISSING_AWAY_TEAM = "{\"homeTeam\": \"Wolves\", \"awayTeam\": \"\"}";

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void resolverReturnsCorrectDiceByDefault() throws Exception {
        ResultActions result = this.mockMvc.perform(get("/resolve").contentType(MediaType.APPLICATION_JSON)
                .content(JSON_WOLVES_VS_WBA))
                .andExpect(status().isOk());

                String content = result.andReturn().getResponse().getContentAsString();
                String homeTeam = JsonPath.read(content, "$.homeTeam");
                String awayTeam = JsonPath.read(content, "$.awayTeam");
                String homeDice = JsonPath.read(content, "$.homeDice");
                String awayDice = JsonPath.read(content, "$.awayDice");
                assertThat(homeTeam, equalTo(HOME_TEAM));
                assertThat(awayTeam, equalTo(AWAY_TEAM));
                assertThat(homeDice, equalTo(DEFAULT_HOME_DICE));
                assertThat(awayDice, equalTo(DEFAULT_AWAY_DICE));
    }

    @Test
    public void resolverReturns400WhenHomeTeamMissingFromRequest() throws Exception {
         this.mockMvc.perform(get("/resolve").contentType(MediaType.APPLICATION_JSON)
                .content(JSON_MISSING_HOME_TEAM))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void resolverReturns400WhenAwayTeamMissingFromRequest() throws Exception {
         this.mockMvc.perform(get("/resolve").contentType(MediaType.APPLICATION_JSON)
                .content(JSON_MISSING_AWAY_TEAM))
                .andExpect(status().isBadRequest());
    }
    
}
