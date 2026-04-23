package it.gff.learn.structures.camel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity // Entity means that this class must be threatened as a relational entity.
@TableGenerator( name = "Students" ) // This is used by the ORM to link this Entity in a DBMS Table.
public class Student extends RepresentationModel<Student> {
    @Column
    // this tells ORM to map this attribute (name) to a table field with same name. You can specify the name of the field inside the database by using @Column(name="db_column_name")
    private String name;
    @Column
    private String surname;
    @Id // Id means that this field is the Primary Key of the table.
    @Column
    private String id;

    @Column
    @JsonIgnore
    private Boolean exported = false;

}