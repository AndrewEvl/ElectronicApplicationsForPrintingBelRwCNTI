package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Bid;

import java.util.Optional;

public interface BidService {

    void save(Bid bid);

    Iterable<Bid> findAll();

    Optional<Bid> findById(Long id);

    void delete(Bid bid);

    Iterable<Bid> findAllPerNowMounts ();
}
