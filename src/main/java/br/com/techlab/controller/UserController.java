package br.com.techlab.controller;

import br.com.techlab.model.User;
import br.com.techlab.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userForm) {
        User user = userRepository.save(userForm);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@PathParam("id") Long id, @RequestBody User userForm) {
        Optional<User> userFromDb = userRepository.findById(id);

        if (userFromDb.isPresent()) {
            User user = userRepository.save(userFromDb.get());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@PathParam("id") Long id) {
        Optional<User> userFromDb = userRepository.findById(id);

        if (userFromDb.isPresent()) {
            userRepository.delete(userFromDb.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}