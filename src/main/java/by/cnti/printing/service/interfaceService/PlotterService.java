package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Plotter;

import java.util.Optional;

public interface PlotterService {

    void save(Plotter plotter);

    Iterable<Plotter> findAll();

    Optional<Plotter> findById(Long id);

    void delete(Plotter plotter);
}
