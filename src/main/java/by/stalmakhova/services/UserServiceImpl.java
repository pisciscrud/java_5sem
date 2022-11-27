package by.stalmakhova.services;

import by.stalmakhova.dto.RegistrateUser;
import by.stalmakhova.entity.User;
import by.stalmakhova.repositories.UserRepository;
import by.stalmakhova.services.Interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public User login(RegistrateUser unauthorizedUser) {
        String login = unauthorizedUser.getLogin();
        if (userRepository.findByLogin(login).isEmpty()) {
            throw new IllegalArgumentException("User with login " + login + " not found");
        }
        User userFromRepository = userRepository.findByLogin(login).get();
        if (!passwordEncoder.matches(unauthorizedUser.getPassword(), userFromRepository.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        System.out.println(unauthorizedUser.getPassword() + " " + userFromRepository.getPassword());
        return userFromRepository;
    }
 @Override
   public  Long GetRoleId(String login) {
     return userRepository.findByLogin(login).get().getIdRole() ;
 }

    @Override
    public void register(User User) {
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        userRepository.save(User);
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public Long getUserIdByLogin(String login) {
        return userRepository.findByLogin(login).get().getId();
    }
    @Override
    public User getUserByLogin(String name) {
        return userRepository.findByLogin(name).get();
    }
}