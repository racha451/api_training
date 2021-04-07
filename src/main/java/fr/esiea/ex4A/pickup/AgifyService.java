package fr.esiea.ex4A.pickup;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AgifyService {

    public final AgifyClient agifyClient;
    public final MyRepository myRepository = new MyRepository();

    public AgifyService(AgifyClient agifyClient) {
        this.agifyClient = agifyClient;
    }

    public void addUser(MyUser user) throws IOException {
        AgifyUser agifyUser;
        if(!myRepository.seeIfUserExists(user)){
            agifyUser = this.agifyClient.getUserAge(user.name, user.country).execute().body();
            myRepository.addNewUser(user, agifyUser);
        }
    }

    public ArrayList<MyUser> matchUser(String userName, String userCountry){
        MyUser userRequestingMatch = myRepository.getUser(userName, userCountry);
        if(userRequestingMatch != null){
            return myRepository.matchUser(userRequestingMatch);
        } else {
            return new ArrayList<>();
        }
    }
}
