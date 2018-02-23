package by.cnti.printing.repository;

import by.cnti.printing.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {
    @Query(value = "SELECT * FROM bid WHERE month(date) = MONTH(now())AND YEAR(date) = YEAR(NOW())", nativeQuery = true)
    List<Bid> findAllBidPMountNow();

    @Query(value = "SELECT * FROM bid WHERE month(date) = MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH))AND YEAR(date) = YEAR(now())", nativeQuery = true)
    List<Bid> findAllBidByLastMounts();

    List<Bid> findAllByPaperDensityAndPaperSize(PaperDensity paperDensity, PaperSize paperSize);

    List<Bid> findAllByPrinter(PrinterModel printerModel);

    List<Bid> findAllByStatusWork(StatusWork statusWork);

    List<Bid> findAllByAllowIsNull();

    @Query (value = "UPDATE bid SET bid.allow = ('Одобренно') WHERE bid.id = ?" , nativeQuery = true)
    Bid updateBid (Long id);
}
