# volunteer-organizer
Service for organizing volunteers and their work.

## Stats
[![DeepSource](https://app.deepsource.com/gh/benicamera/volunteer-organizer.svg/?label=code+coverage&show_trend=true&token=ZO2OqNng3DQ-sKosN6S_L7BP)](https://app.deepsource.com/gh/benicamera/volunteer-organizer/)

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
