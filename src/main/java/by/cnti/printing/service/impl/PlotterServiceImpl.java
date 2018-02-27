package by.cnti.printing.service.impl;

import by.cnti.printing.entity.Plotter;
import by.cnti.printing.repository.PlotterRepository;
import by.cnti.printing.service.interfaceService.PlotterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PlotterServiceImpl implements PlotterService {

    private PlotterRepository plotterRepository;

    @Autowired
    public PlotterServiceImpl(PlotterRepository plotterRepository) {
        this.plotterRepository = plotterRepository;
    }

    @Override
    public void save(Plotter plotter) {
        plotterRepository.save(plotter);
    }

    @Override
    public Iterable<Plotter> findAll() {
        return plotterRepository.findAll();
    }

    @Override
    public Optional<Plotter> findById(Long id) {
        return plotterRepository.findById(id);
    }

    @Override
    public void delete(Plotter plotter) {
        plotterRepository.delete(plotter);
    }

    @Override
    public List<Plotter> findAllPlotterMountNow() {
        return plotterRepository.findAllPlotterMountNow();
    }

    @Override
    public List<Plotter> findAllPlotterByLastMounts() {
        return plotterRepository.findAllPlotterByLastMounts();
    }
}
