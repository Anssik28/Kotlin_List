# Kotlin_List

Sovelluksessa käytetään Task-datamallia, joka kuvaa yksittäistä tehtävää listassa.
- id = yksilöi tehtävän
- title = nimi
- description = kuvaus
- priority = tärkeysarvo
- dueDate = määräpäivä
- done = kertoo onko tehtävä suoritettu

Datamalli on muuttumaton (val), ja muutokset tehdään luomalla uusi olio.

Funktiot
- addTask = lisää uuden tehtävän listaan
- toggleDone = vaihtaa tehtävän suorituksen tilan id:n perusteella
- filterByDone = suodattaa tehtävät done-tilan mukaan
- sortByDueDate = järjestää tehtävät määräpäivän mukaan

Compose-tilanhallinta
Jetpack Compose käyttää tilaa (state) määrittämään, mitä käyttöliittymässä näytetään.
Kun tila muuttuu, Compose suorittaa automaattisesti uudelleen vain ne funktiot, jotka käyttävät kyseistä tilaa.
Tässä sovelluksessa käyttöliittymä päivittyy automaattisesti kun tehtäviä lisätään, poistetaan, suodatetaan tai järjestetään.

Viewmodel>remember
remember-muuttuja säilyttää tilan vain niin kauan kun Composable on muistissa.
Se ei säily konfiguraatiomuutoksissa eikä erota käyttöliittymää sovelluslogiikasta.
ViewModel säilyttää tilan koko ajan ja pitää sovelluslogiikan erillään UI-koodista, jolloin koodi on selkeämpää,
helpommin testattavaa ja käyttöliittymä pysyy vakaana myös konfiguraatiomuutosten aikana.
