package will.dev.appStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import will.dev.appStore.dto.UserDTO;
import will.dev.appStore.entites.User;
import will.dev.appStore.service.UserService;

import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(path = "all_user")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Stream<UserDTO> getAllUser(){return this.userService.getAllUser();}

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(path = "{id}",produces = APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable int id){
        return this.userService.getUser(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable Long id,@RequestBody User user){
        this.userService.modifier(id, user);
    }

}
