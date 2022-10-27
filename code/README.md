# ARGAEL: ARGument Annotation and Evaluation tooL

**ARGAEL** is an open-source Java desktop application designed to maximize the experience and efficiency of the process of annotating and evaluating arguments in large text corpora.

## System description

| Nr. | Code metadata description | Value |
| --- | --- | --- |
| C1  | Current code version | v2.0 |
| C2  | Permanent link to code/repository used for this code version | https://github.com/argrecsys/argael |
| C3  | Permanent link to Reproducible Capsule | https://github.com/argrecsys/argael/tree/main/jar |
| C4  | Legal Code License | Apache License 2.0 |
| C5  | Code versioning system used | git |
| C6  | Software code languages, tools, and services used | Java |
| C7  | Compilation requirements, operating environments and dependencies | JDK 16 |

## All dependencies
The implemented solution depends on or make use of the following libraries:
- JDK 16
- JSON Java 20210307
- OpenCSV 4.1
- Snake YAML 1.9

## Configuration
The ARGAEL JSON configuration file allows configuring (in a simple and readable way) both the argument model to be used during the annotation and the quality metrics to be used during the evaluation.

The input parameters (<a href="https://github.com/argrecsys/argael/blob/main/code/Argael/Resources/config/params.json">params.json</a> file) of the tool are:
```json
{
    "data": {
        "fileExtension": "jsonl",
        "language": "es",
        "source": "C:/Dev Projects/argael/data/proposals",
        "result": "C:/Dev Projects/argael/data/results"
    },
    "annotation_model": {
        "components": ["major claim", "claim", "premise"],
        "relation_categories": ["cause: condition", "cause: reason", "clarification: conclusion", "clarification: exemplification", "clarification: restatement", "clarification: summary", "consequence: explanation", "consequence: goal", "consequence: result", "contrast: alternative", "contrast: comparison", "contrast: concession", "contrast: opposition", "elaboration: addition", "elaboration: precision", "elaboration: similarity"],
        "relation_intents": ["support", "attack"]
    },
    "evaluation_model": {
        "quality": ["incorrect", "not relevant", "relevant", "very relevant"]
    }
}
```

## Execution and use
The project has an executable package in the `\jar` folder, called `Argael.jar`. To run the tool from the Command Prompt (CMD), execute the following commands:

``` console
  cd "argael\jar\"
  java -jar Argael.jar
```

## Authors
Created on Jun 29, 2022  
Created by:
- <a href="https://github.com/ansegura7" target="_blank">Andrés Segura-Tinoco</a>
- <a href="http://arantxa.ii.uam.es/~cantador/" target="_blank">Iv&aacute;n Cantador</a>

## License
This project is licensed under the terms of the <a href="https://github.com/argrecsys/argael/blob/main/LICENSE">Apache License 2.0</a>.

## Acknowledgements
This work was supported by the Spanish Ministry of Science and Innovation (PID2019-108965GB-I00).
