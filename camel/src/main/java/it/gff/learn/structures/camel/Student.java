package it.gff.learn.structures.camel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
@TableGenerator( name = "Students" )
public class Student extends RepresentationModel<Student> {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @JsonIgnore
    private Boolean exported = false;

}