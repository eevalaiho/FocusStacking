# Testaus

## Yksikkötestaus

Testausraportti: 

Testikattavuus: [https://eevalaiho.github.io/FocusStacking/coverage/index.html](https://eevalaiho.github.io/FocusStacking/coverage/index.html)


## Järjestelmätestaus

Ohjelman järjestelmätestausta varten on [Apysinia fulva -merikorallia esittävästä kuvasta](https://free-images.com/display/aplysina_fulva_png.html) tehty 150x100 pikselin kokoisia testikuvia (alla). Kuvat on tehty kuvankäsittelyohjelmalla peilaamalla 75x50 pikselin kokoinen pala kahteen suuntaan. Tarkoituksena on ollut saada joka neljännekseltään yhtä tarkka kuva. 

![Tarkka](../application/src/main/resources/150x100-koralli-mirrored-sharp.png "Tarkka")

Näin saadusta kuvasta on muokattu kolme testikuvaa, joissa kuvankäsittelyohjelmalla on sumennettu joko vasen-, oikea- tai yläpuoli. 

![Vasemmalta blurri](../application/src/main/resources/150x100-koralli-mirrored-left-blur.png "Vasemmalta blurri")
![Oikealta blurri](../application/src/main/resources/150x100-koralli-mirrored-right-blur.png "Oikealta blurri")
![Ylhäältä blurri](../application/src/main/resources/150x100-koralli-mirrored-top-blur.png "Ylhäältä blurri")

Kun ikkunan koko on 16, algoritmi tuottaa kuvan:

![Output, window size 16](../application/src/main/resources/150x100-koralli-output-16.png "Output, window size 16")

Kun ikkunan koko on 32, algoritmi tuottaa kuvan:

![Output, window size 32](../application/src/main/resources/150x100-koralli-output-32.png "Output, window size 32")

Vastaavalla menetelmällä on tehty toiset 150x100 pikselin kuvat. Tarkka kuva:

![Tarkka](../application/src/main/resources/150x100-koralli2-mirrored-sharp.png "Tarkka")

Sumennetut kuvat:

![Vasemmalta blurri](../application/src/main/resources/150x100-koralli2-mirrored-left-blur.png "Vasemmalta blurri")
![Oikealta blurri](../application/src/main/resources/150x100-koralli2-mirrored-right-blur.png "Oikealta blurri")
![Ylhäältä blurri](../application/src/main/resources/150x100-koralli2-mirrored-top-blur.png "Ylhäältä blurri")

Kun ikkunan koko on 16, algoritmi tuottaa kuvan:

![Output, window size 16](../application/src/main/resources/150x100-koralli2-output-16.png "Output, window size 16")

Kun ikkunan koko on 32, algoritmi tuottaa kuvan:

![Output, window size 32](../application/src/main/resources/150x100-koralli2-output-32.png "Output, window size 32")


Kuvista huomataan, että

