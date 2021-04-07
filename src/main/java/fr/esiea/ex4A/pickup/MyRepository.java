package fr.esiea.ex4A.pickup;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MyRepository {
    final HashMap<String, MyUser> userList = new HashMap<>();
    final HashMap<String, AgifyUser> userListWithAge = new HashMap<>();

    int getNumberOfUsers(){
        return this.userList.size();
    }

    void addNewUser(MyUser userInfo, AgifyUser userDataWithAge){
        this.userList.put(userInfo.getUserId(), userInfo);
        this.userListWithAge.put(userInfo.getUserId(), userDataWithAge);
    }

    boolean seeIfUserExists(MyUser userInfo){
        return this.userList.containsKey(userInfo.getUserId());
    }

    MyUser getUser(String userName, String countryId){
        return this.userList.get(userName.concat(countryId));
    }

    ArrayList<MyUser> matchUser(MyUser userInfo){
        ArrayList<MyUser> matchingUsers = new ArrayList<>();
        for(Map.Entry<String, AgifyUser> userEntry : this.userListWithAge.entrySet()){
            if(Math.abs(userEntry.getValue().getAge() - this.userListWithAge.get(userInfo.getUserId()).getAge()) <= 4){
                MyUser possiblyMatchingUser = this.userList.get(userEntry.getValue().getUserId());
                if(possiblyMatchingUser.getSex().equals(userInfo.getSexPref()) && userInfo.getSex().equals(possiblyMatchingUser.getSexPref())) {
                    matchingUsers.add(this.userList.get(userEntry.getValue().getUserId()));
                }
            }
        }
        return matchingUsers;
    }
}
