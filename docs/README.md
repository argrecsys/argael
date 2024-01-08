# ARGAEL: ARGument Annotation and Evaluation tooL

**ARGAEL** is an open-source Java desktop application designed to maximize the experience and efficiency of the process of annotating and evaluating arguments in large text corpora.

## System Description

| Nr. | Code metadata description | Value |
| --- | --- | --- |
| C1  | Current code version | v1.2 |
| C2  | Permanent link to code/repository used for this code version | https://github.com/argrecsys/argael |
| C3  | Permanent link to Reproducible Capsule | https://github.com/argrecsys/argael/tree/main/exec |
| C4  | Legal Code License | Apache License 2.0 |
| C5  | Code versioning system used | git |
| C6  | Software code languages, tools, and services used | Java |
| C7  | Compilation requirements, operating environments and dependencies | JDK 17 |

## All Dependencies
The implemented solution depends on or make use of the following libraries:
- <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html" target="_blank">JDK 17</a>
- JSON Java 20210307
- OpenCSV 4.1
- Snake YAML 1.9

## Configuration
The JSON configuration file allows configuring, in a simple and readable way, the argument model to be used during the annotation (*annotation_model* key), the metrics to be used during the evaluation (*evaluation_model* key), as well as some characteristics of the text documents to be used (*data* key).

The input parameters (<a href="https://github.com/argrecsys/argael/blob/main/src/Argael/Resources/config/params.json">params.json</a> file) of the tool are:
```json
{
    "annotation_model": {
        "components": ["major claim", "claim", "premise"],
        "relation_categories": ["cause: condition", "cause: reason", "clarification: conclusion", "clarification: exemplification", "clarification: restatement", "clarification: summary", "consequence: explanation", "consequence: goal", "consequence: result", "contrast: alternative", "contrast: comparison", "contrast: concession", "contrast: opposition", "elaboration: addition", "elaboration: precision", "elaboration: similarity"],
        "relation_intents": ["support", "attack"]
    },
    "evaluation_model": {
        "quality": ["incorrect", "not relevant", "relevant", "very relevant"]
    },
    "data": {
        "fileExtension": "jsonl",
        "language": "es",
        "source": "C:/argael/data/proposals",
        "result": "C:/argael/data/results"
    }
}
```

The  users registered in ARGAEL are allowed to both generate and evaluate annotations. Their usernames are stored as a list in a plain text (TXT) file.

## System Interface
The ARGAEL interface offers two views for the annotation generation task, namely the *simple view* and the *assisted view*. Both of them allow the user to: i) identify and manually annotate ACs on a given text, ii) annotate ARs between pairs of identified ACs, and iii) interactively validate the created annotations.

### Simple Annotation View
This view, available in the *Independent Annotation* tab, allows the user to identify and annotate argumentative components and their respective relations independently, i.e., without taking into account previous annotations from other people on the same document. This view is recommended for expert or senior annotators.

![ARGAEL simple annotation view](https://raw.githubusercontent.com/argrecsys/argael/main/images/argael-view-annotation-simple.jpg)

Next, we describe the graphical controls available in this view, following their numeration in previous figure:

1. *Main annotation panel*. It displays the selected document text along with the argument components annotated by the current user. It supports particular text highlighting of each component type.
2. *Annotator toolbar*. It provides graphical controls for creating and deleting argument components and relations. It offers as options the elements configured in the argument model.
3. *Argument components (ACs) table*. It displays the argument components annotated by the current user, by means of three fields: AC id, AC text, and AC type.
4. *Argument relations (ARs) table*. It displays the argument relations annotated by the current user, by means of five fields: AR id, AC id 1, AC id 2, AR type, and AR intent.
5. *Argument textarea*. It displays the selected argument in a user-friendly way. Its text is shown when selecting an argument relation in the ARs table.

### Assisted Annotation View
This view, available in the *Assisted Annotation* tab, allows the user to identify and annotate argumentative components and relations in an assisted way, i.e., it can be based on previous annotations of other users. This view is recommended for junior annotators or in cases where there is little time available for annotation.

![ARGAEL assisted annotation view](https://raw.githubusercontent.com/argrecsys/argael/main/images/argael-view-annotation-assisted.jpg)

Next, we describe the graphical controls available in this view, following their numeration in previous figure:

1. *Drop-down list of target (reference) annotators*. It contains the set of other annotators registered into the system. By choosing one of these annotators, the current user specifies that she is interested in accessing such annotator's work on a selected document.
2. *Assisted annotation panel*. It displays the annotations made by the chosen target annotator on a selected document. These annotations serve as a guide for the current user to annotated the document by herself.
3. *Main annotation panel*. It displays the selected document text along with the argument components annotated by the current user. It supports particular text highlighting of each component type.
4. *Annotator toolbar*. It provides graphical controls for creating and deleting argument components and relations. It offers as options the elements configured in the argument model.
5. *Argument components (ACs) table*. It displays the argument components annotated by the current user, by means of three fields: AC id, AC text, and AC type.
6. *Argument relations (ARs) table*. It displays the argument relations annotated by the current user, by means of five fields: AR id, AC id 1, AC id 2, AR type, and AR intent.

### Annotation Evaluation View
The evaluator view, available in the *Evaluation* tab, allows (in a simple, but straightforward way) the manual assessment of the argument units (both components and relations) annotated by other users, based on the evaluation metrics configured in the *config.JSON* file.

One of the benefits of ARGAEL is the fact that it offers support for multiple annotators and evaluators for an input text document. Hence, each document can be annotated by several users and, at the end of the collaborative process, one can measure the average agreement between annotators or evaluators, and obtain consensual argument annotations.

![ARGAEL cross evaluation view](https://raw.githubusercontent.com/argrecsys/argael/main/images/argael-view-evaluation-colab.jpg)

Next, we describe the graphical controls available in this view, following their numeration in previous figure:

1. *Drop-down list of target (reference) annotators*. It contains the set of other annotators registered into the system. By choosing one of these annotators, the current user specifies that she is interested in accessing such annotator’s work on a selected document.
2. *Annotation panel*. It displays the annotations made by the chosen target annotator on a selected document. %It facilitates the visualization of the context of the annotated arguments.
3. *Argument components (ACs) table3. Its rows show the argument components annotated by the target user. For each row, the control allows the assessment of such components by selecting one of the values of a drop-down list in the *evaluation* column of the table.
4. *Argument relations (ARs) table*. Its rows show the argument relations annotated by the target user. For each row, the control allows the assessment of such relations by selecting one of the values of a drop-down list in the *evaluation* column of the table.
5. *Argument textarea*. It displays the selected argument in a user-friendly way. The text is shown when selecting an argument relation in the ARs table.

## Execution and Use
To edit the ARGAEL source code and also, to run the executable .JAR file, you need a version greater than or equal to JDK 17. In the following <a href="https://www.oracle.com/java/technologies/downloads/"  target="_blank">link</a>, you can download the latest version of JDK for Windows, Linux and macOS, from Oracle's website.

The ARGAEL executable file with its dependencies (i.e., lib and resources folders) are located in the `\exec` folder. Specifically, the name of the executable file is `Argael.jar`. Once JDK 17 is installed, you can run the ARGAEL tool by double-clicking on the .jar file or from the Command Prompt (CMD) by executing the following commands:

``` console
  cd "argael\exec\"
  java -jar Argael.jar
```

More information on how to manually run ARGAEL can be found <a href="https://github.com/argrecsys/argael/tree/main/exec" target="_blank">here</a>.

Before attempting to run ARGAEL, make sure that the environment variable that references the java.exe file exists. The following image is an example for the Windows operating system.

![Java environment variable](https://raw.githubusercontent.com/argrecsys/argael/main/images/java-env-variable.jpg)

**Final note**: The minimum screen resolution required to run ARGAEL correctly is 1680 x 1050 (with 100% scaling).

## Authors
Created on Jun 29, 2022  
Created by:
- <a href="https://github.com/ansegura7" target="_blank">Andrés Segura-Tinoco</a>
- <a href="http://arantxa.ii.uam.es/~cantador/" target="_blank">Iv&aacute;n Cantador</a>

## License
This project is licensed under the terms of the <a href="https://github.com/argrecsys/argael/blob/main/LICENSE">Apache License 2.0</a>.

## Acknowledgements
This work was supported by the Spanish Ministry of Science and Innovation (PID2019-108965GB-I00).
