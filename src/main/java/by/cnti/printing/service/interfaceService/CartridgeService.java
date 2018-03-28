package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Bid;
import by.cnti.printing.entity.Cartridge;

import java.util.Optional;

public interface CartridgeService {

    void save(Cartridge cartridge);

    Iterable<Cartridge> findAll();

    Optional<Cartridge> findById(Long id);

    void delete(Cartridge cartridge);
}
