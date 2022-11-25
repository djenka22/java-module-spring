package com.example.javamodule.domain.models;

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
        return outputs.get(0).output.documents.get(0).transUnits.get(0).sentences.get(0).transe.get(0).target.get(0).getLang();
    }
    public String getText() {
        return outputs.get(0).output.documents.get(0).transUnits.get(0).sentences.get(0).transe.get(0).target.get(0).getText();
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
class Outputs {
    Output output;
    Info info;
}
@Data
@AllArgsConstructor
class Output {
    List<Document> documents;
    String mimetype;
}
@Data
@AllArgsConstructor
class Document {
    String datatype;
    String mimetype;
    @Column(name = "src_lang")
    String scrLang;
    @Column(name = "tgt_lang")
    String tgtLang;
    @Column(name = "trans_units")
    List<Unit> transUnits;
    String version;
}
@Data
@AllArgsConstructor
class Unit {
    Integer id;
    List<Sentence> sentences;
    @Column(name = "src_lang")
    String scrLang;
    @Column(name = "tgt_lang")
    String tgtLang;

}
@Data
@AllArgsConstructor
class Sentence {
    @Column(name = "alt_transes")
    List<Transe> transe;
    String id;
    String source;
}
@Data
@AllArgsConstructor
class Transe {
    Integer id;
    List<String> props;
    List<Target> target;
}
@Data
@AllArgsConstructor
class Target {
    String lang;
    String text;
}
@Data
@AllArgsConstructor
class Source {
    String lang;
    String text;
}
@Data
@AllArgsConstructor
class Info {
    @Column(name = "mime_type")
    String mimetype;
    List<SelectedRoutes> selectedRoutes;
    Stats stats;
}
@Data
@AllArgsConstructor
class SelectedRoutes {
    List<Routes> routes;
    String stepName;
}
@Data
@AllArgsConstructor
class Stats {

}
@Data
@AllArgsConstructor
class Lid {

}
@Data
@AllArgsConstructor
class Routes {
    Integer priority;
    String profileId;
    String queue;
    String service;
    String version;
}



