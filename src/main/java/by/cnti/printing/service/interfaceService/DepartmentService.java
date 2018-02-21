package by.cnti.printing.service.interfaceService;

import by.cnti.printing.entity.Bid;
import by.cnti.printing.entity.Department;

import java.util.Optional;

public interface DepartmentService {

    void save(Department department);

    Iterable<Department> findAll();

    Optional<Department> findById(Long id);

    void delete(Department department);
}
