package fr.esiea.ex4A.pickup;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MyController {
    private final AgifyService agifyService;

    public MyController(AgifyService agifyService) { this.agifyService = agifyService; }

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody MyUser myUser) throws IOException {
        System.out.println(myUser);
        this.agifyService.addUser(myUser);
    }

    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MyUser> getMatchingUsers(@RequestParam(name = "userName", required = true) String name, @RequestParam(name = "userCountry", required = true) String country) {
        List<MyUser> userList = this.agifyService.matchUser(name, country);
        return userList;
    }
}
