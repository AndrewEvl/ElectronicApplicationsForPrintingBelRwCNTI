package by.cnti.printing.service.impl;

import by.cnti.printing.entity.PaperSize;
import by.cnti.printing.repository.PaperSizeRepository;
import by.cnti.printing.service.interfaceService.PaperSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class PaperSizeServiceImpl implements PaperSizeService {

    private PaperSizeRepository paperSizeRepository;

    @Autowired
    public PaperSizeServiceImpl(PaperSizeRepository paperSizeRepository) {
        this.paperSizeRepository = paperSizeRepository;
    }

    @Override
    public void save(PaperSize paperSize) {
        paperSizeRepository.save(paperSize);
    }

    @Override
    public Iterable<PaperSize> findAll() {
        return paperSizeRepository.findAll();
    }

    @Override
    public Optional<PaperSize> findById(Long id) {
        return paperSizeRepository.findById(id);
    }

    @Override
    public void delete(PaperSize paperSize) {
        paperSizeRepository.delete(paperSize);
    }
}
