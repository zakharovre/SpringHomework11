package ru.geekbrains.homework11.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.homework11.models.dtos.UserDto;
import ru.geekbrains.homework11.models.entities.Role;
import ru.geekbrains.homework11.models.entities.User;
import ru.geekbrains.homework11.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }

    public UserDto incrementScore(String username) {
        User user = userRepository.findByUsername(username).get();
        user.setScore(user.getScore() + 1);
        return new UserDto(userRepository.save(user));
    }

    public UserDto decrementScore(String username) {
        User user = userRepository.findByUsername(username).get();
        user.setScore(user.getScore() - 1);
        return new UserDto(userRepository.save(user));
    }

    public Optional<User> getUserById(int id) {

        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
