package by.cnti.printing.repository;

import by.cnti.printing.entity.CartridgeReplacement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartridgeReplacementRepository extends CrudRepository<CartridgeReplacement, Long> {
}
