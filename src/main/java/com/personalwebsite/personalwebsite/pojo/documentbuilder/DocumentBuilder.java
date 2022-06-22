package com.personalwebsite.personalwebsite.pojo.documentbuilder;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
public class DocumentBuilder {

    private String docStr;
    private Document document;

    public DocumentBuilder(String docStr){
        this.docStr = docStr;
        this.document = new Document(new HashMap<String, DocumentSection>());
    }

    public Document buildDocument(){
        //TODO create method for sections
            //TODO create method for subsections
        return document;
    }

    //public Document makeSection();
}
