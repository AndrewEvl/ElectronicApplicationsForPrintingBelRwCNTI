package by.cnti.printing.service.impl;

import by.cnti.printing.entity.TypeOfPaper;
import by.cnti.printing.repository.TypeOfPaperRepository;
import by.cnti.printing.service.interfaceService.TypeOfPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class TypeOdPaperServiceImpl implements TypeOfPaperService {

    private TypeOfPaperRepository typeOfPaperRepository;

    @Autowired
    public TypeOdPaperServiceImpl(TypeOfPaperRepository typeOfPaperRepository) {
        this.typeOfPaperRepository = typeOfPaperRepository;
    }


    @Override
    public void save(TypeOfPaper typeOfPaper) {
        typeOfPaperRepository.save(typeOfPaper);
    }

    @Override
    public Iterable<TypeOfPaper> findAll() {
        return typeOfPaperRepository.findAll();
    }

    @Override
    public Optional<TypeOfPaper> findById(Long id) {
        return typeOfPaperRepository.findById(id);
    }

    @Override
    public void delete(TypeOfPaper typeOfPaper) {
        typeOfPaperRepository.delete(typeOfPaper);
    }
}
