Tällä viikolla sain korjattua JPS olevan ongelman joka johti sykliin kun koitettiin muodostaa reittiä haun lopuksi. Testasin manuaalisesti JPS:n toimivuutta [satunnaisilla kartoilla](https://www.movingai.com/benchmarks/random/index.html) verraten että JPS löytää yhtä lyhyen reitin kuin BFS. Tulin myös tulokseen että A* algoritmi tarvitsee `bestDist` mappinsa, vaikka epäilin sitä toisella viikolla. Aloitin myös toteuttamaan omia tietorakenteita, ja ainoa mikä jäi toteuttamatta tällä viikolla on `PriorityQueue`. `ArrayDeque`, `BitSet`, `HashMap` ja `HashSet` on taas toteutettu sen verran laajasti mitä projektissa tarvitaan. HashMapillä esim. ei ole `remove` metodia koska en tule sitä projektissa tarvitsemaan. Kaikille omille tietorakenteille on tehty 100% rivikattavuuden yksikkötestit, poislukien `MyPriorityQueue`, koska se on tällä hetkellä vaan wrapperi javan oman `PriorityQueue`n päälle. Projektissa ei vieläkään ole mitään pääohjelmaa, vaan algoritmit on toteutettu ja niille on kattavat yksikkötestit.

En oppinut tällä viikolla hirveästi mitään uutta. Ehkä jonkun verran HashMapin ja ArrayDequen toiminnasta, vaikka toki tiesin jo teoriassa miten ne tulisi toteuttaa vaikken niitä ennen ollutkaan toteuttanut.

Tällä viikolla ei myöskään hirveämmin ollut ongelmia, JPS debuggaamiseen meni kyllä muutama tunti mutta sain sen lopulta ratkaistua. Lisäilin uusia yksikkötestejä aina kun sain yksilöityä tilanteen jossa JPS toimi väärin. Aikavaatimuksissa tosin oli taas vähän miettimistä. Jos esim. BFS:n aikavatimus on `O(V + E)`, mutta koska kaarien määrä on gridissä suoraan verrannollinen solmujen määrään niin eikö aikavaatimus voida myös ilmaista: `O(V+4V)=O(5V)=O(V)`? Vastaavasti taas A* aikavaatimus täten olisi `O(V*logV)`. Mietin myös onko suorituskykyvertailussa pakko yrittää saada jonkunlaista dataa mikä osoittaisi aikavaativuutten paikkaapitävyyttä? Koska mielestäni ei ole hirveän mielekästä tutkia algoritmejen huonoimpia tapauksia, joka A* ja BFS tapauksessa olisi esim. 1x100000 kartta päästä päähän ja JPS:n tapauksessa varmaan samanlainen suora reitti jossa kuitenkin suoran ympärillä on joka toinen solu blokattu (koska silloin lisätään eniten tavaraa queueen). Ajattelin käyttää [näitä](https://www.movingai.com/benchmarks/grids.html) karttoja ja ryhmitellä tulokset kartan tyypin mukaan, enkä syötteen koon.

Ensi viikolla toteutan oman PriorityQueuen ja alan toteuttamaan suorituskykyvertailua.

Tällä viikolla käytin projektiin noin 9 tuntia.