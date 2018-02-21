package by.cnti.printing.service.impl;


import by.cnti.printing.entity.Bid;
import by.cnti.printing.repository.BidRepository;
import by.cnti.printing.service.interfaceService.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Iterable<Bid> findAllPerNowMounts() {
        return null;
    }
}
