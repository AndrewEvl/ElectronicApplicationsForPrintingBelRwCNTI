package by.cnti.printing.repository;

import by.cnti.printing.entity.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {
}
