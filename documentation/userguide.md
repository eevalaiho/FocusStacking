# Käyttöohje

## Ohjelman käynnistäminen

Ohjelma voidaan käynnistää ohjelman juurihakemistossa ```application``` komennolla:
```
java -jar build/libs/FocusStacking.jar
```
Kun ohjelma käynnistetään ilman parametreja, se ajetaan debug-tilassa oletusasetuksillaan. Debug-tilassa ohjelma tulostaa konsoliin enemmän tietoa eli käytetyt parametrit ja tarkimpien pikselien valintaa varten lasketun matriisin. Oletusasetuksillaan ohjelma koostaa sovelluksen rakenteesta löytyvistä kolmesta kaunokinkuvasta tarkemman version. Tarkimpien pikseleiden valinnassa käytetään sinistä värikanavaa ja Fourier-muunnoksessa 16 kuvapisteen ikkunaa. 

## JavaDoc

Ohjelman JavaDoc -dokumentaatio [https://eevalaiho.github.io/FocusStacking/javadoc/](https://eevalaiho.github.io/FocusStacking/javadoc/)



