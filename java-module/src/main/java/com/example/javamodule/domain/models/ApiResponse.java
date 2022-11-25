package com.example.javamodule.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    List<Outputs> outputs;

    public String getLang() {
        return outputs.get(0).output.documents.get(0).trans_units.get(0).sentences.get(0).transe.get(0).target.getLang();
    }
    public String getText() {
        return outputs.get(0).output.documents.get(0).trans_units.get(0).sentences.get(0).transe.get(0).target.getText();
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
class Outputs {
    Output output;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Output {
    List<Document> documents;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Document {
    List<Unit> trans_units;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Unit {
    List<Sentence> sentences;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Sentence {
    @JsonProperty("alt_transes")
    List<Transe> transe;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Transe {
    Target target;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Target {
    String lang;
    String text;
}



