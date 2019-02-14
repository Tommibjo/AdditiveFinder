# AdditiveFinder android app

Tehty Android studio sovelluskehityösympäristöllä.
Ohjelma noutaa HTTP GET metodilla JSON listan Additivefinder-Apilta. Api pyörii Raspissa ja on kirjoitettu Javalla (Spring Boot). JSON lista sisältää tiedot kilpailijoiden lisäaineista.Ohjelma luo oman H2 tietokannan käynnistyksen yhteydessä (mikäli tietokantaa ei ole olemassa) ja päivittää JSON listan datat sinne. 

Tehty nykyistä työpaikkaa varten myynnin työtä helpottamaan. Tarkoituksena on, että myyjä voisi asiakkaiden luona vieraillessa löytää helposti kilpailijan tuotteelle korvaavan tuotteen sovelluksen avulla.

Tuotetiedot on tallennettu erilliseen MySQL tietokantaan, jota hallinnoi rasperryllä AdditiveFinder-API <br/> ( https://github.com/Tommibjo/AdditiveFinder-API ).

Tämä on vielä suht kesken!

![alt text](https://i.imgur.com/0OA0VQM.png)


