package com.netrox.demo.repository;

import com.netrox.demo.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepo extends JpaRepository<DemoModel,Long> {

}
