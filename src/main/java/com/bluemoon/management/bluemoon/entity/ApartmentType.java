package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "apartment_types")
public class ApartmentType {
    @Id
    @Column(name = "apartment_type_id", nullable = false)
    private Integer id;

    @Column(name = "type_name", nullable = false, length = 100)
    private String typeName;

}