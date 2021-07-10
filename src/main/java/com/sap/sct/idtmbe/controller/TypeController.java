package com.sap.sct.idtmbe.controller;

import com.sap.sct.idtmbe.api.dto.AssociationDto;
import com.sap.sct.idtmbe.api.enumeration.Association;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

    public TypeController() {
    }

    @GetMapping(value = "/association", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AssociationDto>> getListOfAssociationTypes() {
        final List<AssociationDto> listOfAssociationTypes = Stream.of(Association.values())
                .map(association -> new AssociationDto(association.getLabel(), association.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listOfAssociationTypes);
    }
}
