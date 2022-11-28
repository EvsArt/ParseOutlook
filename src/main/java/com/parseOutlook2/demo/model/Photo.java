package com.parseOutlook2.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Entity
@Table(name = "photos")
@RequiredArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
//    @SequenceGenerator(name = "photos_seq", sequenceName = "PHOTOS_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photos_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String member_email;
}
