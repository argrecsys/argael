# ARGAEL: ARGument Annotation and Evaluation tooL
![version](https://img.shields.io/badge/version-1.4.0-blue)
![last-update](https://img.shields.io/badge/last_update-10/18/2022-orange)
![license](https://img.shields.io/badge/license-Apache_2.0-brightgreen)

**ARGAEL** is an open-source Java desktop application designed to maximize the experience and efficiency of the process of annotating and evaluating arguments in large text corpora.

Some advantages of our proposed tool are:
- it is designed to support both annotation and evaluation
- it supports multiple rich argument models
- it allows users to define their own argument model (if required)
- it offers support for multiple annotators
- it allows intra- and inter-argument annotations (due to its text-driven annotation view), among others

## System
ARGAEL is mainly composed of 2 views, which offer different functionalities and roles to the users.

**Annotator view**. It allows in 3 simple steps, i) the annotation of argumentative components (ACs), ii) the annotation of argumentative relation (ARs), and iii) their initial validation.

![ARGAEL annotation view](https://raw.githubusercontent.com/argrecsys/argael/main/images/argael-annotation-view.png)

**Evaluator view**. It allows (in a simple, but straightforward way) the manual assessment of the argument units (both components and relations) annotated by other users.

![ARGAEL evaluation view](https://raw.githubusercontent.com/argrecsys/argael/main/images/argael-evaluation-view.png)

## Demo video
In the following link you can see a demo video of the tool.

[![ARGAEL demo video](https://img.youtube.com/vi/8Kfca5YVlE8/default.jpg)](https://youtu.be/8Kfca5YVlE8)

## Dependencies
The implemented solution depends on or make use of the following libraries:
- JDK 16
- JSON Java 20210307
- OpenCSV 4.1
- Snake YAML 1.9

## Execution and Use
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
