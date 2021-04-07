package fr.esiea.ex4A.pickup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

class AgifyServiceTest {



    AgifyClient agifyClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.agify.io/").addConverterFactory(JacksonConverterFactory.create()).build();
        return retrofit.create(AgifyClient.class);
    }

    AgifyClient mockAgifyClient = agifyClient();

    AgifyService agifyService = new AgifyService(mockAgifyClient);

    @Test
    void adding_a_user_should_increment_the_number_of_users() throws IOException {
        UserInfo userInfo = new UserInfo("Sonar@mail.com", "Sonar", "sonar14", "FR", "M", "F");
        agifyService.addUser(userInfo);
        Assertions.assertThat(agifyService.pickupRepository.userList.get("SonarFR")).isEqualTo(userInfo);
        Assertions.assertThat(agifyService.pickupRepository.getNumberOfUsers()).isEqualTo(1);
    }

    @Test
    void adding_multiple_users_should_increment_the_number_of_users() throws IOException {
        UserInfo userInfo1 = new UserInfo("Mika@mail.fr", "Mika", "bMika", "FR", "M", "M");
        UserInfo userInfo2 = new UserInfo("Olivier@movistar.es", "Olivier", "Olivier", "ES", "M", "F");
        UserInfo userInfo4 = new UserInfo("marlon@mail.com", "Marlon", "marlon14", "US", "M", "F");
        UserInfo userInfo3 = new UserInfo("elena@mail.ru", "Elena", "elenaaa", "RU", "F", "F");
        agifyService.addUser(userInfo1);
        agifyService.addUser(userInfo2);
        agifyService.addUser(userInfo3);
        agifyService.addUser(userInfo4);
        Assertions.assertThat(agifyService.pickupRepository.userList.get("MikaFR")).isEqualTo(userInfo1);
        Assertions.assertThat(agifyService.pickupRepository.userList.get("OlivierES")).isEqualTo(userInfo2);
        Assertions.assertThat(agifyService.pickupRepository.userList.get("MarlonUS")).isEqualTo(userInfo4);
        Assertions.assertThat(agifyService.pickupRepository.userList.get("ElenaRU")).isEqualTo(userInfo3);
        Assertions.assertThat(agifyService.pickupRepository.getNumberOfUsers()).isEqualTo(4);
    }
}
