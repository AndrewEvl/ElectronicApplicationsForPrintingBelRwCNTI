package by.cnti.printing.service.impl;

import by.cnti.printing.entity.PaperDensity;
import by.cnti.printing.repository.PaperDensityRepository;
import by.cnti.printing.service.interfaceService.PaperDensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PaperDensityServiceImpl implements PaperDensityService {

    private PaperDensityRepository paperDensityRepository;

    @Autowired
    public PaperDensityServiceImpl(PaperDensityRepository paperDensityRepository) {
        this.paperDensityRepository = paperDensityRepository;
    }

    @Override
    public void save(PaperDensity paperDensity) {
        paperDensityRepository.save(paperDensity);
    }

    @Override
    public Iterable<PaperDensity> findAll() {
        return paperDensityRepository.findAll();
    }

    @Override
    public Optional<PaperDensity> findById(Long id) {
        return paperDensityRepository.findById(id);
    }

    @Override
    public void delete(PaperDensity paperDensity) {
        paperDensityRepository.delete(paperDensity);
    }
}
