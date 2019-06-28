package by.cnti.printing.service.impl;


import by.cnti.printing.entity.*;
import by.cnti.printing.repository.BidRepository;
import by.cnti.printing.service.interfaceService.BidService;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class BidServiceImpl implements BidService {

    private BidRepository bidRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public void save(Bid bid) {
        bidRepository.save(bid);
    }

    @Override
    public Iterable<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public Optional<Bid> findById(Long id) {
        return bidRepository.findById(id);
    }

    @Override
    public void delete(Bid bid) {
        bidRepository.delete(bid);
    }

    @Override
    public List<Bid> findAllNowMonth() {
        return bidRepository.findAllBidPMountNow();
    }

    @Override
    public List<Bid> findAllLastMonth() {
        return bidRepository.findAllBidByLastMounts();
    }

    @Override
    public List<Bid> findAllByPaperDensityAndPaperSize(PaperDensity paperDensity, PaperSize paperSize) {
        return bidRepository.findAllByPaperDensityAndPaperSize(paperDensity, paperSize);
    }

    @Override
    public List<Bid> findAllByPrinter(PrinterModel printerModel) {
        return bidRepository.findAllByPrinter(printerModel);
    }

    @Override
    public List<Bid> findAllByStatusWork(StatusWork statusWork) {
        return bidRepository.findAllByStatusWork(statusWork);
    }

    @Override
    public List<Bid> findAllByAllowIsNull() {
        return bidRepository.findAllByAllowIsNull();
    }

    @Override
    public Bid updateBidStatusWork(String id) {
        return bidRepository.updateBidStatusWork(id);
    }

    @Override
    public Map<String, String> allPaperForMount(Long paperSizeId, Long paperDensityId) {
        return bidRepository.allPaperForMount(paperDensityId, paperSizeId);
    }
}
