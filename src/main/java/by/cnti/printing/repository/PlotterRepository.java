package by.cnti.printing.repository;


import by.cnti.printing.entity.Plotter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlotterRepository extends CrudRepository<Plotter, Long> {

    @Query(value = "SELECT * FROM plotter WHERE month(date) = MONTH(now())AND YEAR(date) = YEAR(NOW())", nativeQuery = true)
    List<Plotter> findAllPlotterMountNow();

    @Query(value = "SELECT * FROM plotter WHERE month(date) = MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH))AND YEAR(date) = YEAR(now())", nativeQuery = true)
    List<Plotter> findAllPlotterByLastMounts();
}
