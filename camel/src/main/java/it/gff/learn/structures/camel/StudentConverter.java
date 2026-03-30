package it.gff.learn.structures.camel;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class StudentConverter {

    public ExportStudent toStudent(LinkedHashMap<String, Object> map) {
        return new ExportStudent((String)map.get("id"), (String)map.get("name"), (String)map.get("surname"));
    }
}