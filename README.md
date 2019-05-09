# Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit (alkukesä)
Eeva-Maria Laiho

## Aihe: Focus stacking -algoritmi
Focus stacking -algoritmin toteutus ja vertailu kirjastototeutuksiin.
---
Focus stacking -algoritmi on digitaalinen kuvankäsittelymenetelmä, minkä avulla useasta samasta kohteesta otetusta valokuvasta voidaan luoda yksi mahdollisimman tarkka kuva. Alkuperäisistä valokuvista erotetaan mahdollisimman tarkat kohdat ja kootaan ne uudeksi kuvaksi.

Algoritmin toiminta on pääpiirteissään seuraava ([Sini Lehtosen gradun mukaan](https://helda.helsinki.fi/bitstream/handle/10138/154047/GraduSini.pdf?sequence=3)):

1. Esikäsittely: kuvien lataaminen ja kohdistus. Kuvan fokuksen säätäminen aiheuttaa sen, että samaa kohdetta esittävät pikselit eivät välttämättä ole samassa kohdassa. Kohdistus voidaan tehdä myös varsinaisen algoritmin ulkopuolella esim kuvankäsittelyohjelmalla ja tulee toteutukseen, jos aikaa jää.

2. Lasketaan jokaiselle kuvalle saman värikanavan konvoluution Fourier-muunnos (tai miten se nyt pitäisi oikein sanoakaan). Käytännössä siis liu'utetaan kuvan yli 16x16 (tai 32x32) kuvapisteen ikkunaa ja lasketaan jokaiselle ikkunalle:

    1. Fourier-muunnos,

    2. ylipäästösuodatus

    3. L^2-normi

3. Luodaan uusi kuva kopioimalla siihen kuvapisteet siitä alkuperäisestä kuvista, jonka L^2-normi on suurin  



## Määrittely
Sovelluksen määrittely [erillisessä dokumentissa](documentation/määrittely.md).

## Toteutus
Sovelluksen toteutusdokumentaatio [erillisessä dokumentissa](documentation/toteutus.md).

## Testaus
Sovelluksen toteutusdokumentaatio [erillisessä dokumentissa](documentation/testaus.md).

## Käyttöohje
Sovelluksen toteutusdokumentaatio [erillisessä dokumentissa](documentation/käyttöohje.md).
