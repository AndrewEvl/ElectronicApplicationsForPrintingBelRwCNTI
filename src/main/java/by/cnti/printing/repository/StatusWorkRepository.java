package by.cnti.printing.repository;

import by.cnti.printing.entity.StatusWork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatusWorkRepository extends CrudRepository<StatusWork, Long> {
}
