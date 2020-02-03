# Ohjelmistotuotanto-2020
Course assignments for http://ohjelmistotuotanto-hy-avoin.github.io/

First assignments (2-13) at https://github.com/sarlijes/ohtu-2020-viikko1

# Muistiinpanot


Komento | Toiminnallisuus
--- | --- 
`cmd //c tree` | puu
`` | test
`` | test
`` | test
`` | test
`` | test

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

Colons can be used to align columns.

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

There must be at least 3 dashes separating each header cell.
The outer pipes (|) are optional, and you don't need to make the 
raw Markdown line up prettily. You can also use inline Markdown.

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3