package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import will.dev.appStore.entites.User;
import will.dev.appStore.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(path = "all_user")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAllUser(){return this.userService.getAllUser();}

    @GetMapping(path = "{id}",produces = APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable int id){
        return this.userService.getUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody User user){
        this.userService.modifier(id, user);
    }

}
