# Määrittelydokumentti

## Mitä algoritmeja ja tietorakenteita toteutat työssäsi

Tarkoitus toteuttaa seuraavat algoritmit:

* [A*](https://en.wikipedia.org/wiki/A*_search_algorithm)
* [BFS](https://en.wikipedia.org/wiki/Breadth-first_search)
* [Jump point search](https://en.wikipedia.org/wiki/Jump_point_search) 

Jos aikaa jää, mahdollisesti myös [IDA*](https://en.wikipedia.org/wiki/Iterative_deepening_A*)

Tietorakenteiksi tarvitsee toteuttaa ainakin jono ja minimikeko. Todennäköisesti myös hajautustaulu tulee toteuttaa, mutta se riippuu vielä algoritmien toteutustavasta.

## Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet

Algoritmit etsivät lyhyimmän reitin kahden pisteen välillä ruudukossa. Algoritmit eivät siis käsittele solmuja ja kaaria, vaan 2 ulotteisia ruudukkoja. Tämä pääosin sen takia että olisi mahdollista toteuttaa jump point search.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Ohjelma lukee .map tiedostoja, todennäköisesti [täältä](https://www.movingai.com/benchmarks/grids.html). Kartan ominaisuuksilla on todennäköisesti aika paljon vaikutusta tuloksiin, joten olisi hyvä jos ohjelma osaisi tehdä benchmarkin monelle eri kartalle yhdellä ajolla. Mahdollisesti jopa niin, että esim. lähteestä haetut sokkelo kartat ovat omasssa kansiossaan, satunnaiset kartat omasssaan yms. Miten benchmarkista muodostetaan mahdollisimman järkevää dataa on myös vähän auki. Jonkunnäköisiä keskiarvoja pitää varmaan muodostaa koska yksittäinen maali/loppu voi suosia tiettyä algoritmia. Toisaalta esim. tilanteet joissa reittiä ei löydy pitää ainakin eritellä tilastoissa jotenkin, koska sellaisissa tilanteissa jokaisen algoritmin pitää käydä jokaisessa ruudussa missä on mahdollista vähintään kerran. Toisaalta kannattaa kuitenkin mitata onko algoritmien välillä eroa tapauksissa joissa reittiä ei löydy.

## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

BFS:n aikavaatimus on O(V+E), jossa V on solmujen määrä ja E kaarien määrä. BFS:n tilavatimus on O(V). A* aikavaatimus on O(V + E \* log E) ja tilavaatimus sama kuin leveyshaulla. En löytänyt jump point searchin vaatimuksia, mutta luettuani sen toimintatavan aika- ja tilavaatimus on todennäköisesti sama A* kanssa. IDA* poikkeaa muista tilavaatumuksellaan joka on vain O(d), missä d on lyhyimmän polun pituus.

## Lähteet

* [A* - Wikipedia](https://en.wikipedia.org/wiki/A*_search_algorithm)
* [BFS - Wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search)
* [Jump point search - Wikipedia](https://en.wikipedia.org/wiki/Jump_point_search) 
* [IDA* - Wikipedia](https://en.wikipedia.org/wiki/Iterative_deepening_A*)
* [Dijkstra's algorithm - Wikipedia](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)