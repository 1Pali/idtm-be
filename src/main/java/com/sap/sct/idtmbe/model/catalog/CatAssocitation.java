package com.sap.sct.idtmbe.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAT_ASSOCIATION")
public class CatAssocitation {
    @Id
    @Column(name = "label", updatable = false, nullable = false)
    private String label;

    @Column(name = "NAME")
    private String name;
}
