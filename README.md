# Ohjelmistotuotanto-2020
Course assignments for http://ohjelmistotuotanto-hy-avoin.github.io/

First assignments (2-13) at https://github.com/sarlijes/ohtu-2020-viikko1

# Muistiinpanot


Komento | Mitä tekee
`cmd //c tree` | puu
`` | 
`` | 
`` | 
`` | 
`` | 

cmd //c tree

`cmd //c tree`
`./gradlew clean`
`gradle build` käännös
``
``

Lisää tiedostoon build.gradle seuraava:

```
jar {
    manifest {
        attributes 'Main-Class': 'ohtu.ohtuvarasto.Main'
    }
}
```
generoi jar-tiedosto komennolla ``gradle jar``
komennolla tree näet minne hakemistoon jar tulee
suorita jar-tiedosto komennolla ``java -jar tiedoston_nimi.jar``
komento tulee suorittaa hakemistosta, jossa jar-tiedosto sijaitsee

testit suoritetaan komennolla `gradle test`