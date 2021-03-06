package by.cnti.printing.repository;

import by.cnti.printing.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
