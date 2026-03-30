package it.gff.learn.structures.camel;

import java.util.Date;

public class ExportStudent {
    private String name;
    private String surname;
    private String id;
    private Date exportDate;
    public String getName() {
        return name;
    }

    public ExportStudent() {}
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }



    public ExportStudent(String id, String name, String surname) {
        this.surname = surname;
        this.name = name;
        this.id = id;
        setExportDate();
    }

    private void setExportDate() {
        this.exportDate = new Date();
    }
}