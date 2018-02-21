package by.cnti.printing.service.impl;

import by.cnti.printing.entity.StatusWork;
import by.cnti.printing.repository.StatusWorkRepository;
import by.cnti.printing.service.interfaceService.StatusWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class StatusWorkServiceImpl implements StatusWorkService {

    private StatusWorkRepository statusWorkRepository;

    @Autowired
    public StatusWorkServiceImpl(StatusWorkRepository statusWorkRepository) {
        this.statusWorkRepository = statusWorkRepository;
    }


    @Override
    public void save(StatusWork statusWork) {
        statusWorkRepository.save(statusWork);
    }

    @Override
    public Iterable<StatusWork> findAll() {
        return statusWorkRepository.findAll();
    }

    @Override
    public Optional<StatusWork> findById(Long id) {
        return statusWorkRepository.findById(id);
    }

    @Override
    public void delete(StatusWork statusWork) {
        statusWorkRepository.delete(statusWork);
    }
}
