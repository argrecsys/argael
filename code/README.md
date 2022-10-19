# ARGAEL: ARGument Annotation and Evaluation tooL

**ARGAEL** is an open-source Java desktop application designed to maximize the experience and efficiency of the process of annotating and evaluating arguments in large text corpora.

## Configuration
The ARGAEL JSON configuration file allows configuring (in a simple and readable way) both the argument model to be used during the annotation and the quality metrics to be used during the evaluation.

The input parameters (<a href="https://github.com/argrecsys/argael/blob/main/code/Argael/Resources/config/params.json">params.json</a> file) of the tool are:
```json
{
    "data": {
        "folder": "C:/Dev Projects/argael/data/proposals",
        "extension": "jsonl",
        "language": "es"
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

## Authors
Created on Jun 29, 2022  
Created by:
- <a href="https://github.com/ansegura7" target="_blank">Andrés Segura-Tinoco</a>
- <a href="http://arantxa.ii.uam.es/~cantador/" target="_blank">Iv&aacute;n Cantador</a>

## License
This project is licensed under the terms of the <a href="https://github.com/argrecsys/argael/blob/main/LICENSE">Apache License 2.0</a>.

## Acknowledgements
This work was supported by the Spanish Ministry of Science and Innovation (PID2019-108965GB-I00).
