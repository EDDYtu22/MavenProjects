package dev.edmond.springstart.web.dto;

import java.util.List;

public class PersonEditResponse {

    private List<String> edits;

    public List<String> getEdits() {
        return edits;
    }

    public void setEdits(List<String> edits) {
        this.edits = edits;
    }

}
