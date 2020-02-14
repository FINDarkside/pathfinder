Tällä viikolla toteutin `MyPriorityQueue` rakenteen ja lisäsin sille testit varmistaakseni sen toimivuuden. Sen lisäksi toteutin alkeellisen suorituskykytestauksen, jossa suoritetaan yksittäisen [.scen](https://www.movingai.com/benchmarks/formats.html) tiedoston skenaariot. Ohjelma ei tosin ota skenaarion sijaintia vielä parametrina vaan se on kovakoodattu tällä hetkellä Main metodiin. Tulevaisuudessa on tarkoitus suorittaa kokonainen kansiollinen skenaario tiedostoja yhdellä ajolla. Hyvänä uutisena suorituskykytestauksen tulokset vastaa odotettua, eli BFS on hitain ja JPS on nopein "tyypillisessä" tapauksessa. Seuraavilla viikoilla toki on mielenkiintoista nähdä häviääkö JPS joissain kartoissa muille algoritmeille.

Opin tällä viikolla hieman lisää binary heapin toteutuksesta. Olen toteuttanut sellaisen ennenkin, mutta huomasin että vanhoissa toteutuksissani oli ollut muutamia bugeja.

Tällä viikolla ei ollut erityisemmin mitään ongelmia.

Ensi viikolla teen suorituskykytestauksesta monipuolisemman. Ainakin tarkoituksena on ajaa testejä useammassa kartassa ja mieluummin myös useammasta "ryhmästä" yhdellä ajolla. Voisi myös olla hyödyllistä tulostaa muutakin tietoa kun kokonaisaika, ehkä yksittäisen ajon pisin ja lyhin aika ja mahdollisesti jotain muuta kuten keskihajonta. Pitää vähän miettiä mitkä luvut antaisi jotain lisäarvoa.

Tällä viikolla käytin projektiin noin 5 tuntia.
