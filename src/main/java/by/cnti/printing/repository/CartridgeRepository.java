package by.cnti.printing.repository;

import by.cnti.printing.entity.Cartridge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartridgeRepository extends CrudRepository<Cartridge, Long> {
}
