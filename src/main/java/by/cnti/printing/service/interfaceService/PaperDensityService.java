package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.PaperDensity;

import java.util.Optional;

public interface PaperDensityService {

    void save(PaperDensity paperDensity);

    Iterable<PaperDensity> findAll();

    Optional<PaperDensity> findById(Long id);

    void delete(PaperDensity paperDensity);
}
