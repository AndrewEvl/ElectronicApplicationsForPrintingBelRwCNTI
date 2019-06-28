package by.cnti.printing.repository;

import by.cnti.printing.entity.*;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.OptionalDataException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {

    @Query(value = "SELECT * FROM bid WHERE month(date) = MONTH(now())AND YEAR(date) = YEAR(NOW())", nativeQuery = true)
    List<Bid> findAllBidPMountNow();

    @Query(value = "SELECT * FROM bid WHERE month(date) = MONTH(DATE_ADD(NOW(), " +
            "INTERVAL -1 MONTH))AND YEAR(date) = YEAR(now())", nativeQuery = true)
    List<Bid> findAllBidByLastMounts();

    List<Bid> findAllByPaperDensityAndPaperSize(PaperDensity paperDensity, PaperSize paperSize);

    List<Bid> findAllByPrinter(PrinterModel printerModel);

    List<Bid> findAllByStatusWork(StatusWork statusWork);

    List<Bid> findAllByAllowIsNull();

    @Query(value = "UPDATE bid SET bid.allow = ('Одобренно') WHERE bid.id = ?", nativeQuery = true)
    Bid updateBidStatusWork(String id);

    @Query(value = "SELECT concat(paper_size.size, ' ', paper_density.density) AS size_destiny, " +
            "SUM(bid.number_of_pages) AS sum_pages FROM bid RIGHT JOIN paper_size ON bid.paper_size_id = paper_size.id " +
            "RIGHT JOIN paper_density ON bid.paper_density_id = paper_density.id WHERE paper_density_id = ? AND paper_size_id = ? " +
            "AND MONTH(date) = MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH)) AND YEAR(date) = YEAR(now())", nativeQuery = true)
    Map<String,String> allPaperForMount(Long paperDensityId, Long paperSizeId);
}
