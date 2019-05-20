# Määrittely

Focus stacking -algoritmi on digitaalinen kuvankäsittelymenetelmä, minkä avulla useasta samasta kohteesta otetusta valokuvasta voidaan luoda yksi mahdollisimman tarkka kuva. Alkuperäisistä valokuvista erotetaan mahdollisimman tarkat kohdat ja kootaan ne uudeksi kuvaksi.

Algoritmin toiminta on pääpiirteissään seuraava ([Sini Lehtosen gradun mukaan](https://helda.helsinki.fi/bitstream/handle/10138/154047/GraduSini.pdf?sequence=3)):

1. Esikäsittely: kuvien lataaminen 

2. Tarkimpien kuvapisteiden valinta: liu'utetaan jokaisen kuvan tietyn värikanavan (esim vihreä) yli 16x16 (tai 32x32) kuvapisteen ikkunaa ja lasketaan jokaiselle ikkunalle:

    1. Fourier-muunnos,

    2. ylipäästösuodatus

    3. L^2-normi

3. Luodaan uusi kuva kopioimalla siihen kuvapisteet:
    
    1. ensimmäisestä kuvasta, jos minkään kuvan L^2-normi ei ylitä tiettyä raja-arvoa: näin ei-fokusoituneesta taustasta tulee tarkempi
    
    2. muutoin siitä alkuperäisestä kuvista, jonka L^2-normi on suurin eli syväterävyys paras


## Esikäsittely

Kuvien lataaminen toteutetaan erilliseen IO-luokkaan, jotta kuvatiedoston tallentamiseen voidan käyttää XX valmiskirjastoa. Kuvien vihreät värikanavat tallennetaan k * w * h -taulukkoon, missä k = kuvien määrä, w = kuvan leveys, h = kuvan korkeus.

### Aikavaativuus

O(1)

### Tilavaativuus

O(n), missä n = w * h = kuvapisteiden määrä


## Tarkimpien kuvapisteiden valinta

Saa parametrikseen esikäsittelyvaiheessa luodun k * w * h -taulukon, missä k = kuvien määrä, w = kuvan leveys, h = kuvan korkeus. Taulukkoon on tallennettu alkuperäisten kuvien vihreät värikanavat.

Kuvapisteiden valinta toteutetaan kahdella sisäkkäisellä silmukalla, missä käydään läpi jokainen kuvapiste jokaisesta kuvasta. Silmukassa lasketaan ikkunan Fourier-muunnos, ylipäästösuodatus ja L^2 -normi.

Algoritmi ylläpitää w * h -kokoista apumatriisia (2D -taulukko tai harva matriisi), mihin on tallennettu kunkin kuvapisteen tarkin kuva.

### Aikavaativuus

O(n^2) = k * n * (Fourier-muunnos + ylipäästösuodatus + L^2-normi) = k * n * 3k

, missä n = kuvapisteiden määrä, k = alkuperäisten kuvien määrä << n.

### Tilavaativuus

O(n), missä n = kuvapisteiden määrä

### Tietorakenteet

Apumatriisi: 2D -taulukko, mihin tallennetaan tarkimman alkuperäisen kuvan tunniste

Konvoluutioikkuna: (ks konvoluutioikkunan laskeminen)

Kompleksiluku: jos tarpeen, mallia esim [https://algs4.cs.princeton.edu/.../Complex.java.html](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Complex.java.html)

## Konvoluutioikkunan laskeminen


### Tietorakenteet

Konvoluutioikkuna: 4 * m^2 taulukko, missä m on konvoluutioikkunan sivun pituus, esimerkiksi 16 tai 32

## Fourier-muunnos

Saa parametrikseen 2D kuvapistetaulukon, rivi- ja sarakeindeksin ja ikkunan koon. Laskee ikkunan Fourier-muunnoksen ja palauttaa ikkunan. 

### Aikavaativuus

O(m^2), missä m on konvoluutioikkunan sivun pituus

### Tilavaativuus

O(m^2), missä m on konvoluutioikkunan sivun pituus

## Ylipäästösuodatus

Saa parametrikseen 

### Aikavaativuus

### Tilavaativuus

### Tietorakenteet

## L^2-normi

### Aikavaativuus

### Tilavaativuus

### Tietorakenteet


## Uuden kuvan luonti

Uusi kuva luodaan instantioimalla kuvaobjekti, kopioimalla kuvapisteet oikeasta kuvasta ja tallentamalla kuvatiedosto levylle. Metodi saa parametrikseen tarkimpien kuvapisteiden valinnassa luodun apumatriisin, mikä sisältää tarkimman kuvan tunnisteen jokaiselle kuvapisteelle. 

Perustapauksessa apumatriisi on alkuperäisten kuvien kokoinen kokonaislukutaulukko. 

Ajan niin salliessa voidaan toteuttaa harvan matriisin implementaatio, missä apumatriisiin tallennetaan vain ne kuvapisteet mitkä eivät ole oletuskuvasta. Tällöin tietorakenteena on esimerkiksi kaksiulotteinen taulukko, missä rivinumero-, sarakenumero- ja arvorivit. 

Uuden kuvan luominen ja tallentaminen toteutetaan erilliseen IO-luokkaan, jotta kuvatiedoston tallentamiseen voidan käyttää XX valmiskirjastoa. 

### Aikavaativuus (perustapaus)

O(n) = O(1) + O(n) + O(1) = kuvaobjektin instantiointi + pikselien kopiointi + kuvatiedoston tallennus, 

missä n = kuvapisteiden määrä, apumatriisi käydään läpi pikseli kerrallaan.

### Aikavaativuus (harva matriisi) 

lambda * perustapaus, missä 0 < lambda < 1

kaikkia kuvapisteitä ei tarvitse käydä läpi, koska apumatriisi on harva.

### Tilavaativuus: 

O(n)








