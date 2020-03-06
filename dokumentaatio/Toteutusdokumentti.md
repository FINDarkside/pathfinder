# Toteutusdokumentti

## Ohjelman yleisrakenne

* Projektin reitinhakualgoritmit sijaitsevat tiralabra.pathfinders paketissa. 
* Itse tehdyt tietorakenteet sijaitsee tiralabra.datastructure paketissa.
* Suorituskykytestaukseen vaadittavat toiminnot sijaitsee tiralabra.benchmark paketissa.

## Saavutetut aika- ja tilavaativuudet

Aikavaativuudet:
* BFS: O(V)
* A*: O(V\*log(V))
* JPS: O(V\*log(V))

Tilavaativuudet:
* BFS: O(V)
* A*: O(V)
* JPS: O(V)

## Suorituskykyvertailu

[Testausdokumentti#suorituskykyvertailu](https://github.com/FINDarkside/pathfinder/blob/master/dokumentaatio/Testausdokumentti.md#suorituskykytestaus)

## Työn mahdolliset puutteet ja parannusehdotukset

Benchmarkista olisi mielenkiintoista muodostaa tarkempi jakauma, koska keskiarvo ja muutama persentiili lukema ei anna välttämättä hyvää kokonaiskuvaa tilanteesta. Pathfindereita pystyisi todennäköisesti optimoimaan, itse algoritmeissa tuskin on mitään vikaa, mutta esim. JPS lähtee alussa tällä hetkellä aina samaan suuntaan. Tietyissä tapauksissa saisi huomattavan nopeutuksen jo sillä että lasketaan missä suunnassa maali on lähtöpisteeseen verrattuna ja aloitettaisiin tutkimalla sinne suuntaan. Jump pointissa myöskään `bestDist` mapin päivittäminen olisi (varmaan) pakollista vain jump pointtien kohdalla. Tällä hetkellä se tehdään myös hyppyjen välille oleville sijainneille. Tämä voi joko nopeuttaa tai hidastaa, jäi testaamatta. 
