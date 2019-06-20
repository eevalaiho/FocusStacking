# Toteutus

## Ohjelman rakenne

### Oletuspaketti

Oletuspakettiin sisältyy kaksi luokkaa: pääohjelmaluokka <a href="#Main">```Main```</a> ja ohjelman varsinaisen toiminnallisuuden toteuttava <a href="#FocusStacking">```FocusStacking```</a> -luokka. 

<a name="Main"></a>
#### Main.class

Main-luokkaan on toteutettu ohjemakutsuun vastaava oletusmetodi ```main``` ja sen lisäksi metodeja parametrien käsittelyyn (```parseArguments``` ja ```argumentsToString```) sekä ohjelman varsinaisen toiminnallisuuden käynnistävä ```makeImageStack``` -metodi. 

Luokkaan on tuotu ohjelman ulkopuolisia kirjastoja ```java.io.IOException``` ohjelman virheiden käsittelyä ja hallintaa varten  sekä ```java.time.LocalDateTime``` uloskirjoitettavan tiedoston uniikkia nimeämistä varten.

<a name="FocusStacking"></a>
#### FocusStacking.class




### IO

### Util

### Math


## Ohjelman kulku

Ohjelman kulku (pääohjelma, tarkimman pikselin laskeminen) on kuvattu pääpiirteittäin seuraavissa kaavioissa.

<a name="paaohjelma"></a>
### Pääohjelma

<img src="./diagrams/main.png" alt="Main program" width="250px"/>

<a name="tarkin"></a>
### Tarkimman pikselin laskeminen

<img src="./diagrams/sharpest.png" alt="Compute sharpest pixels" width="500px"/>
