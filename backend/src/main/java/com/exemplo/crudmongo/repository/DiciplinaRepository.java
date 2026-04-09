package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Diciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiciplinaRepository extends JpaRepository<Diciplina, Long> {
}
