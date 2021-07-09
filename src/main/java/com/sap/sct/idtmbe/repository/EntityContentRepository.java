package com.sap.sct.idtmbe.repository;

import com.sap.sct.idtmbe.model.entity.EntityContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EntityContentRepository extends JpaRepository<EntityContent, Long> {
    @Query("FROM EntityContent WHERE tableName = :newTableName")
    Optional<EntityContent> findByName(@Param("newTableName") String newTableName);
}
