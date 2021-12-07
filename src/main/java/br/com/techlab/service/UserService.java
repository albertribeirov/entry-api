package br.com.techlab.service;

import br.com.techlab.dto.UserDTO;
import br.com.techlab.model.User;
import br.com.techlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public ResponseEntity<User> updateUser(Long id, User userForm) {
        Optional<User> userFromDb = userRepository.findById(id);

        if (userFromDb.isPresent()) {
            User userToUpdate = userFromDb.get();
            userForm.setId(userToUpdate.getId());
            userRepository.save(userForm);
            return ResponseEntity.ok(userForm);
        }
        return ResponseEntity.notFound().build();
    }
}
