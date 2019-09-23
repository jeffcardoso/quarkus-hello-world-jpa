package org.acme.service;

import org.acme.dto.UserDTO;
import org.acme.model.Pageable;
import org.acme.model.User;
import org.acme.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserDTO getUser(UUID id) {
        User entity = userRepository.findById(id);
        Optional<User> user = Optional.ofNullable(entity);

        if (user.isPresent()) {
            return UserDTO.from(user.get());
        } else {
            String message = String.format("User %s not found", id.toString());
            throw new EntityNotFoundException(message);
        }
    }

    public List<UserDTO> getAllUsers(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable.getSort(), pageable.getPage());
        return users.stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO save(UserDTO userDTO) throws IOException {
        userRepository.persist(User.from(userDTO));
        User user = userRepository.findByLogin(userDTO.getLogin()).get();

        return UserDTO.from(user);
    }
}
