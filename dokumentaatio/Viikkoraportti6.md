Tällä viikolla toteutin suorituskykytestauksen ja päivitin dokumentaatiota. Suorituskykytestaus vaati lisäksi `MyArrays.sort` toteuttamisen, 25. ja 75. persentiilin löytämiseen.

Tällä viikolla sain paremman käsityksen JPS:n tehokkuudesta. Verrattuna A* algoritmiin ero on aika vaikuttava. Testasin vielä varmuuden vuoksi ajaa A* javan omilla tietorakenteilla varmistuakseni ettei JPS:n nopeus johtuisi esim. siitä että oma `PriorityQueue` olisi merkittävästi hitaampi kuin javan oma. En tosin ole vielä ajanut suorituskykytestejä kaikille karttatyypeille koska siinä kestää todella kauan.

Suurempia ongelmia tällä viikolla ei ollut, mutta kuten mainittu, [kaikkien skenaarioitten](https://www.movingai.com/benchmarks/grids.html) suorittaminen kaikissa kartoissa kestää kauemmin kuin olin odottanut. Karttoja ja skenaarioita on niin paljon, että kaikkien ajaminen  kestäisi noin 8-16 tuntia. Tämän takia ajan testit kaikella datalla vasta kun projekti on muuten valmis.

Ennen loppupalautusta pitää ainakin hioa vielä dokumentaatiota. Main metodia pitää myös muokata niin, että benchmark datan polku otetaan argumenttina sen sijaan että se olisi kovakoodattu ohjelmaan. Tällä viikolla toteutetulle järjestysalgoritmille (quicksort) pitää myös lisätä testit.

Käytin tällä viikolla projektiin noin 6 tuntia.
