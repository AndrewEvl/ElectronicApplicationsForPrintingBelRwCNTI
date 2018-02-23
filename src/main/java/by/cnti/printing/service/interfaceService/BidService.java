package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.*;

import java.util.List;
import java.util.Optional;

public interface BidService {

    void save(Bid bid);

    Iterable<Bid> findAll();

    Optional<Bid> findById(Long id);

    void delete(Bid bid);

    List<Bid> findAllNowMonth();

    List<Bid> findAllLastMonth();

    List<Bid> findAllByPaperDensityAndPaperSize(PaperDensity paperDensity, PaperSize paperSize);

    List<Bid> findAllByPrinter(PrinterModel printerModel);

    List<Bid> findAllByStatusWork(StatusWork statusWork);

    List<Bid> findAllByAllowIsNull();

    Bid updateBid (Long id);
}
