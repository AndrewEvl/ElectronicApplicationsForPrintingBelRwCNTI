package by.cnti.printing.service.impl;

import by.cnti.printing.entity.PrinterModel;
import by.cnti.printing.repository.PrinterModelRepository;
import by.cnti.printing.service.interfaceService.PrinterModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PrinterModelServiceImpl implements PrinterModelService {

    private PrinterModelRepository printerModelRepository;

    @Autowired
    public PrinterModelServiceImpl(PrinterModelRepository printerModelRepository) {
        this.printerModelRepository = printerModelRepository;
    }

    @Override
    public void save(PrinterModel printerModel) {
        printerModelRepository.save(printerModel);
    }

    @Override
    public Iterable<PrinterModel> findAll() {
        return printerModelRepository.findAll();
    }

    @Override
    public Optional<PrinterModel> findById(Long id) {
        return printerModelRepository.findById(id);
    }

    @Override
    public void delete(PrinterModel printerModel) {
        printerModelRepository.delete(printerModel);
    }
}
