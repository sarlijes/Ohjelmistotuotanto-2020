# Ohjelmistotuotanto-2020
Course assignments for http://ohjelmistotuotanto-hy-avoin.github.io/

First assignments (2-13) at https://github.com/sarlijes/ohtu-2020-viikko1

# Muistiinpanot



Komento | Toiminnallisuus
--- | --- 
`cmd //c tree` | puu
`./gradlew clean` | clean
`./gradlew build` | build
`./gradlew jar` | generoi jar-tiedosto
`java -jar tiedoston_nimi.jar` | suorita jar-tiedosto
`./gradlew test` | aja testit
`./gradlew test jacocoTestReport` | luo rivikattavuusraportin: build/reports/jacoco/test/html/index.html
`` | --


Lisää tiedostoon build.gradle:

```
apply plugin: "jacoco" 

jar {
    manifest {
        attributes 'Main-Class': 'ohtu.ohtuvarasto.Main'
    }
}
```

CircleCI lisäys:
1. Avaa “add project”-välilehti ja valitse “Set Up Project” repositorion vierestä
1. Valitaan configuraatioksi Linux ja Gradle (Java)
1. Lisää repositoriosi juureen hakemisto nimeltään .circleci
1. Tee tiedosto config.yml kansion .circleci sisälle ja kopioi sinne CircleCI:n antama Sample.yml-tiedoston sisältö
1. Commitoi ja pushaa repositorio GitHubiin
1. Paina Start Building



