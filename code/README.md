# ARGAEL: ARGument Annotation and Evaluation tooL

**ARGAEL** is an open source Java desktop application designed to maximize the experience and efficiency of the process of annotating and evaluating arguments in large text corpora.

### Configuration
The input parameters (<a href="https://github.com/argrecsys/argael/blob/main/code/Argael/Resources/config/params.json">params.json</a> file) of the tool are:
```json
{
    "language": "es",
    "anno_model": {
        "categories": ["addition", "alternative", "comparison", "concession", "conclusion", "condition", "exemplification", "explanation", "goal", "opposition", "precision", "reason", "restatement", "result", "similarity", "summary"],
        "components": ["major claim", "claim", "premise"],
        "intents": ["support", "attack"]
    },
    "eval_model": {
        "quality": ["low", "medium", "high"]
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
