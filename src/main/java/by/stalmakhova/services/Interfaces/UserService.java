package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.RegistrateUser;
import by.stalmakhova.entity.User;

import javax.transaction.Transactional;

public interface UserService {

    User login(RegistrateUser User);

    @Transactional
    void register(User User);

    Long GetRoleId(String login);

    User getUserById(Long id);
    Long getUserIdByLogin(String login);
}
