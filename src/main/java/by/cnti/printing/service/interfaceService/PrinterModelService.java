package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.PrinterModel;

import java.util.Optional;

public interface PrinterModelService {

    void save(PrinterModel printerModel);

    Iterable<PrinterModel> findAll();

    Optional<PrinterModel> findById(Long id);

    void delete(PrinterModel printerModel);
}
