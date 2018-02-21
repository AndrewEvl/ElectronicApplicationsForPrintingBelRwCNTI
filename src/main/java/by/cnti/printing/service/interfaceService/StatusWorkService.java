package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.StatusWork;

import java.util.Optional;

public interface StatusWorkService {

    void save(StatusWork statusWork);

    Iterable<StatusWork> findAll();

    Optional<StatusWork> findById(Long id);

    void delete(StatusWork statusWork);
}
