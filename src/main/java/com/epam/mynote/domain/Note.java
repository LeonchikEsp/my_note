package com.epam.mynote.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Note")
@Table(name = "note")
public class Note {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String content;

    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Label> labelList = new ArrayList<>();
}
