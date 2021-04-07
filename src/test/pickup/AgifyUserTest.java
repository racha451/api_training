package fr.esiea.ex4A.pickup;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AgifyUserTest {


    @ParameterizedTest
    @CsvSource({
        "Kevin, Kevin",
        "Nathan, Nathan",
        "George, George"
    })
    void creating_a_new_agifyuser_with_a_name_should_give_the_same_name(String wantedName, String expectedName){
        AgifyUser agifyUser = new AgifyUser(wantedName, 10, 10, "US");
        Assertions.assertThat(agifyUser.getName()).isEqualTo(expectedName);
    }

    @ParameterizedTest
    @CsvSource({
        "20, 20",
        "34, 34",
        "40, 40"
    })
    void creating_a_new_agifyuser_with_an_age_should_give_the_same_age(int wantedAge, int expectedAge){
        AgifyUser agifyUser = new AgifyUser("test", wantedAge, 10, "US");
        Assertions.assertThat(agifyUser.getAge()).isEqualTo(expectedAge);
    }

    @ParameterizedTest
    @CsvSource({
        "1212, 1212",
        "555, 555",
        "3333, 3333"
    })
    void creating_a_new_agifyuser_with_a_count_should_give_the_same_count(int wantedCount, int expectedCount){
        AgifyUser agifyUser = new AgifyUser("test", 10, wantedCount, "US");
        Assertions.assertThat(agifyUser.getCount()).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @CsvSource({
        "TN, TN",
        "GB, GB",
        "PR, PR"
    })
    void creating_a_new_agifyuser_with_a_country_should_give_the_same_country(String wantedCountry, String expectedCountry){
        AgifyUser agifyUser = new AgifyUser("test", 10, 100, wantedCountry);
        Assertions.assertThat(agifyUser.getCountry_id()).isEqualTo(expectedCountry);
    }
}
