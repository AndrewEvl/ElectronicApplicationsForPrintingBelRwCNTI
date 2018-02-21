package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void delete(User user);
}
