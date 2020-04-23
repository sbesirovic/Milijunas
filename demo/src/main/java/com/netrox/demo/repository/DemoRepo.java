package com.netrox.demo.repository;

import com.netrox.demo.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DemoRepo extends JpaRepository<DemoModel,Long> {
List<DemoModel> findAllByDeleten (boolean delete);
DemoModel findByIdAndDeleten (Long id,boolean delete );
}
