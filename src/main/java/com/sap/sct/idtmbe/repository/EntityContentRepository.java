package com.sap.sct.idtmbe.repository;

import com.sap.sct.idtmbe.model.entity.EntityContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntityContentRepository extends JpaRepository<EntityContent, Long> {

}
