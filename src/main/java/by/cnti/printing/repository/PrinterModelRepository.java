package by.cnti.printing.repository;

import by.cnti.printing.entity.PrinterModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PrinterModelRepository extends CrudRepository<PrinterModel, Long> {
}
