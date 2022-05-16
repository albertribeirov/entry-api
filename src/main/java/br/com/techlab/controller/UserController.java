package br.com.techlab.controller;

import br.com.techlab.dto.UserDTO;
import br.com.techlab.model.User;
import br.com.techlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUser() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userForm) {
        User user = userService.save(userForm);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@PathParam("id") Long id, @RequestBody User userForm) {
        return userService.updateUser(id, userForm);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@PathParam("id") Long id) {
        Optional<User> userFromDb = userService.findById(id);

        if (userFromDb.isPresent()) {
            userService.delete(userFromDb.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}