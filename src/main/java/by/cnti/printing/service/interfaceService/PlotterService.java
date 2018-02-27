package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Plotter;

import java.util.List;
import java.util.Optional;

public interface PlotterService {

    void save(Plotter plotter);

    Iterable<Plotter> findAll();

    Optional<Plotter> findById(Long id);

    void delete(Plotter plotter);

    List<Plotter> findAllPlotterMountNow ();

    List<Plotter> findAllPlotterByLastMounts();
}
