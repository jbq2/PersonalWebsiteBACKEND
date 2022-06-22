package com.personalwebsite.personalwebsite.pojo.documentbuilder;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Document {

    private Map<String, DocumentSection> sections;

    public Document(HashMap<String, DocumentSection> sections){
        this.sections = sections;
    }

    public void insertSection(DocumentSection section){
        this.sections.put(section.getSectionName(), section);
    }

    public void removeSection(String sectionName){
        this.sections.remove(sectionName);
    }
}
