# volunteer-organizer
Service for organizing volunteers and their work.

## Stats
[![DeepSource](https://app.deepsource.com/gh/benicamera/volunteer-organizer.svg/?label=active+issues&show_trend=true&token=ZO2OqNng3DQ-sKosN6S_L7BP)](https://app.deepsource.com/gh/benicamera/volunteer-organizer/)
[![DeepSource](https://app.deepsource.com/gh/benicamera/volunteer-organizer.svg/?label=resolved+issues&show_trend=true&token=ZO2OqNng3DQ-sKosN6S_L7BP)](https://app.deepsource.com/gh/benicamera/volunteer-organizer/)
![CI](https://github.com/benicamera/volunteer-organizer/actions/workflows/mavenci.yml/badge.svg)

## Project Layout

LaTeX files for technical documentation (german) are stored in `doc/`.

Source code for the backend is provided in `volunteer-organizer-backend/`.

## Getting started with development

Make sure that you have Kotlin and Maven set up.

```
git clone https://github.com/benicamera/volunteer-organizer.git
cd volunteer-organizer/volunteer-organizer-backend
```

In order to run tests, use

```
mvn test
```

## CI

Source Code Analysis and Linting is provided by DeepSource.

On each push, an automatic GitHub workflow runs the tests and packages the Kotlin project.
