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

