# Task List

Datamalli
-
Sovelluksessa käytetään Task-datamallia, joka kuvaa yksittäistä tehtävää listassa.
- id = yksilöi tehtävän
- title = nimi
- description = kuvaus
- priority = tärkeysarvo
- dueDate = määräpäivä
- done = kertoo onko tehtävä suoritettu

Datamalli on muuttumaton (val), ja muutokset tehdään luomalla uusi olio.

Funktiot
-
- addTask = lisää uuden tehtävän listaan
- toggleDone = vaihtaa tehtävän suorituksen tilan id:n perusteella
- filterByDone = suodattaa tehtävät done-tilan mukaan
- sortByDueDate = järjestää tehtävät määräpäivän mukaan

Compose-tilanhallinta
-
Jetpack Compose käyttää tilaa (state) määrittämään, mitä käyttöliittymässä näytetään.
Kun tila muuttuu, Compose suorittaa automaattisesti uudelleen vain ne funktiot, jotka käyttävät kyseistä tilaa.
Tässä sovelluksessa käyttöliittymä päivittyy automaattisesti kun tehtäviä lisätään, poistetaan, suodatetaan tai järjestetään.

Viewmodel>remember
-
remember-muuttuja säilyttää tilan vain niin kauan kun Composable on muistissa.
Se ei säily konfiguraatiomuutoksissa eikä erota käyttöliittymää sovelluslogiikasta.
ViewModel säilyttää tilan koko ajan ja pitää sovelluslogiikan erillään UI-koodista, jolloin koodi on selkeämpää,
helpommin testattavaa ja käyttöliittymä pysyy vakaana myös konfiguraatiomuutosten aikana.

MVVM Compose-sovelluksissa
-
Model-View-ViewModel mallin avulla saadaan erotettua käyttöliittymä logiikasta, joka tekee koodista selkeämpää ja helpommin ylläpidettävää.
- View (UI/näkymä) - sisältää käyttöliittymän ja välittää toiminnot ViewModelille
- ViewModel (näkymämalli) - sisältää ohjelman tilan ja yhdistää näkymän ja mallin. 
- Model (domain/malli) - sisältää logiikan ja datan. ViewModelilla suora yhteys Modeliin, Viewillä ainoastaan ViewModelin kautta.

StateFlow
-
StateFlowia käytetään sovelluksen tilan hallintaan. ViewModelissa tila tallennetaan MutableStateFlow-muuttujaan ja käyttöliittymä kuuntelee sitä StateFlowin kautta.
Kun ViewModel muuttaa tilaa, StateFlow lähettää uuden arvon automaattisesti kuuntelijoille. Compose-käyttöliittymä käyttää collectAsState() -funktiota, jolloin UI päivittyy automaattisesti aina kun tila muuttuu.

Navigointi Jetpack Composessa
-
Jetpack Composessa navigointi toteutetaan NavController- ja NavHost- komponenttien avulla.
- NavController vastaa siirtymisestä ruudusta toiseen
- NavHost määrittelee sovelluksen navigointirakenteen ja reitit

Tässä sovelluksessa navigointi on toteutettu kolmen ruudun välillä:
- HomeScreen - tehtävälista
- CalendarScreen - tehtävät järjestyksessä määräpäivän mukaan
- SettingsScreen - asetukset (dummy näkymä)

MVVM ja navigointi
-
Sovellus käyttää MVVM-arkkitehtuuria, jossa käyttöliittymä, logiikka ja data on erotettu toisistaan. domain-paketti sisältää datamallit, kuten Task. viewmodel-paketissa on TaskViewModel, joka hallitsee sovelluksen tilaa ja käsittelee käyttäjän toiminnot. ui-paketti sisältää Compose-näkymät, kuten HomeScreen ja CalendarScreen.

Navigointi on toteutettu NavController- ja NavHost-komponenttien avulla. Molemmat pääruudut, HomeScreen ja CalendarScreen, käyttävät samaa ViewModel-instanssia, joka luodaan MainActivityssa ja välitetään molemmille ruuduille. Näin tila pysyy samana navigoinnista huolimatta.

ViewModel säilyttää tehtävälistan StateFlow-muuttujassa, jota molemmat ruudut kuuntelevat collectAsState()-funktion avulla. Kun tehtävä lisätään, muokataan tai poistetaan toisessa ruudussa, ViewModel päivittää tilan ja muutos näkyy automaattisesti myös toisessa ruudussa. Tämä varmistaa, että sovelluksen tila pysyy yhtenäisenä kaikissa näkymissä.

CalendarScreenin toteutus
-
CalendarScreen näyttää samat tehtävät kuin HomeScreen, mutta kalenterimaisessa näkymässä. Tehtävät ryhmitellään niiden dueDate-kentän perusteella. Jokainen päivämäärä näytetään otsikkona, ja sen alle listataan kyseisen päivän tehtävät. Näin käyttäjä näkee helposti, mille päivälle kukin tehtävä kuuluu.

AlertDialog - addTask & editTask
-
Tehtävien lisääminen ja muokkaaminen toteutetaan AlertDialog-ikkunoilla, ei erillisillä navigointiruuduilla.
- addTask: HomeScreenissä on lisäyspainike, joka avaa AlertDialogin, johon syötetään tehtävän tiedot. Kun käyttäjä painaa "Save", kutsutaan viewModel.addTask() ja dialogi sulkeutuu.
- editTask: Kun käyttäjä painaa tehtävää listassa, avautuu AlertDialog, jossa voi muokata tehtävän tietoja (updateTask()) tai poistaa tehtävän (removeTask())








