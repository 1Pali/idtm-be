package com.sap.sct.idtmbe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ENTITY_CONTENT")
public class EntityContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Lob
    @Column(name="TABLE_CONTENT")
    private String tableContent;
}
