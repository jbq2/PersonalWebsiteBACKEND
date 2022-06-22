package com.personalwebsite.personalwebsite.pojo.documentbuilder;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DocumentSection {

    private String sectionName;
    private List<String> bullets;
    private Map<String, DocumentSection> subSections;

    public DocumentSection(String sectionName, List<String> bullets, HashMap<String, DocumentSection> subSections){ //has subsections
        this.sectionName = sectionName;
        this.bullets = bullets;
        this.subSections = subSections;
    }

    public DocumentSection(String sectionName, List<String> bullets){ //no subsections
        this.sectionName = sectionName;
        this.bullets = bullets;
    }

    public void insertSubSection(DocumentSection subSection){
        this.subSections.put(subSection.getSectionName(), subSection);
    }

    public void insertBullet(String bullet){
        this.bullets.add(bullet);
    }

    public void removeSubsection(String sectionName){
        this.subSections.remove(sectionName);
    }

    public void removeBullet(int index){
        this.bullets.remove(index);
    }
}
