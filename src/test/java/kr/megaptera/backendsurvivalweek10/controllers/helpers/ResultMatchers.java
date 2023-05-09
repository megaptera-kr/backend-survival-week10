package kr.megaptera.backendsurvivalweek10.controllers.helpers;

import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ResultMatchers {
    public static ResultMatcher contentContains(String substring) {
        return content().string(
                containsString(substring));
    }
}
