package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Bid;
import by.cnti.printing.entity.TypeOfPaper;

import java.util.Optional;

public interface TypeOfPaperService {

    void save(TypeOfPaper typeOfPaper);

    Iterable<TypeOfPaper> findAll();

    Optional<TypeOfPaper> findById(Long id);

    void delete(TypeOfPaper typeOfPaper);
}
