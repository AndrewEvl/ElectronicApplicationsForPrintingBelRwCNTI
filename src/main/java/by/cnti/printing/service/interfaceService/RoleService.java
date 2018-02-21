package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Role;

import java.util.Optional;

public interface RoleService {

    void save(Role role);

    Iterable<Role> findAll();

    Optional<Role> findById(Long id);

    void delete(Role role);
}
