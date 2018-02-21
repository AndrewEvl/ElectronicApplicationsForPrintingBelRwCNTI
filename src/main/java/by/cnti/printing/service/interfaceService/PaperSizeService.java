package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.PaperSize;

import java.util.Optional;

public interface PaperSizeService {

    void save(PaperSize paperSize);

    Iterable<PaperSize> findAll();

    Optional<PaperSize> findById(Long id);

    void delete(PaperSize paperSize);
}
