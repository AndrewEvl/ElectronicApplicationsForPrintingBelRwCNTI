package by.cnti.printing.repository;

import by.cnti.printing.entity.Bid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {
    @Query(value ="SELECT * FROM bid WHERE bit.date = ?",nativeQuery = true)
    List<Bid> findAllBidPerMountNow (LocalDate localDate);

}
