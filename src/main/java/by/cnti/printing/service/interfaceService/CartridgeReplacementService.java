package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.CartridgeReplacement;
import by.cnti.printing.repository.CartridgeRepository;

import java.util.Optional;

public interface CartridgeReplacementService {

    void save(CartridgeReplacement cartridgeReplacement);

    Iterable<CartridgeReplacement> findAll();

    Optional<CartridgeReplacement> findById(Long id);

    void delete(CartridgeReplacement cartridgeReplacement);
}
