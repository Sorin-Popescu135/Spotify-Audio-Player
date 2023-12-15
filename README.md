# Readme Etapa 2 POO

### Mentiuni : 
### * am folosit rezolvarea oficiala pentru etapa 1.
### * am dat 2 commituri cu acelasi nume inainte sa aflu ca se scade pentru asta, deci daca se poate sa 
### se treaca cu vederea.

## Explicatii pachete si clase
  
* User -> am adaugat 2 clase, artist si host :

      Artist -> extensie pentru user, contine metode specifice userilor care sunt si artisti

      Host -> extensie pentru user, contine metode specifice userilor care sunt si hosts

      #Mentiune : userul normal va fi obiect de tip User

* Collections -> am adaugat mai multe clase :

      Album -> obiectul pentru albumul unui artist. 

      AlbumResult -> obiect ce ajuta in afisarea unui album.

      PodcastResult -> obiect ce ajuta in afisarea unui podcast.


* Files -> am adaugat mai multe clase :

      Announcement -> obiect pentru un anunt al unui host.

      Event -> obiect pentru un event al unui artist.

      Merch -> obiect pentru un merch al unui artist.

* interfaces -> pachet unde se afla interfetele pentru Artist si Host

      ArtistOperatons -> interfata unde sunt declarate metodele specifice unui artist.

      HostOperations -> interfata unde sunt declarate metodele specifice unui host.

* visitor -> implementarea design pattern-ului Visitor pentru metoda Usage.

      UsageVisitor -> este implementata metoda prin care se verifica daca un obiect este folosit, pentru a
                      a stii daca poate fi eliminat sau nu. Aceasta metoda se implementeaza pentru : User,
                      Artist, Host, Album si Podcast.

  * VerifyDate -> clasa in care este verificata daca o data este valabila sau nu (folosita pentru verificare
                  atunci cand se adauga un event pentru un artist).


## Explicatii implementare comenzi


* addUser -> creeam un user nou daca acesta nu exista deja.

* switchConnectionStatus -> schimbam statusul unui user din online in offline sau invers. Daca userul este offline, 
                            ii este restrctionata utilizarea anumitor comenzi.

* getOnlineUsers -> returneaza toti userii online.

* addAlbum -> adauga un album nou pentru un user. De asemenea, introduce melodiile din album in baza de date(Admin).

* showAlbums -> afiseaza toate albumele unui artist.

* printCurrentPage -> afiseaza pagina pe care se afla userul. Aceasta poate fi fie Homepage sau LikedContentPage(pagini
                      pagini ale userului), sau pagina unui artist sau host selectat de user.

* addEvent -> adauga un event pentru un artist.

* addMerch -> adauga merch pentru un artist.

* getAllUsers -> afiseaza toti userii, in ordine (useri normali, artisti si hosti).

* deleteUser -> elimina un user. Mai intai se verifica ce tip de user este (normal, artist sau host). Apoi, se verifica daca
                userul este folosit(ex pentru artist : un user are un album sau o melodie a artistului in player, un user
                se afla pe pagina artistului). Acest lucru se verifica folosind metoda din clasa UsageVisitor, implementata
                folosind design pattern-ul visitor. In final, daca userul poate fi sters, se sterge si se elimina orice urma lasata
                de acesta(ex pentru artist: daca se sterge se sterg si melodiile din baza de date, daca un user are una din melodiile
                sale la like, se elimina din lista de melodii apreciate).

* addPodcast -> adauga un podcast pentru un host.

* addAnnouncement -> adauga un anunt pentru un host.

* removeAnnouncement -> elimina un anunt pentru un host daca acesta exista.

* showPodcasts -> afiseaza toate podcasturile unui host.

* removeAlbum -> elimina un album al unui artist si automat din baza de date. Are acelasi procedeu de verificare si de eliminare
                 a urmelor ca la deleteUser(deleteUser pentru un artist se foloseste de removeAlbum). 

* changePage -> schimba pagina userului din HomePage in LikedContentPage si invers.

* removePodcast -> elimina un podcast al unui host si automat din baza de date. Are acelasi procedeu de verificare ca la deleteUser
                   (deleteUser pentru host se foloseste de removePodcast).

* removeEvent -> se elimina un event pentru host.

* getTop5Albums -> se afiseaza top 5 cele mai apreciate albume de pe aplicatie(in functie de numarul de likeuri ale melodiilor de pe album)

* getTop5Artists -> se afiseaza top 5 cei mai apreciati artisti de pe aplicatie(in functie de numarul de likerui ale melodiilor de pe
                    toate albumele ale artistului).

* update player si searchbar -> cu introducerea conceputlui de album, am modificat searchbarul si playerul in asa fel incat asupra unui album
                                se pot aplica aceleasi comenzi specifice unui playlist. De asemenea, am modificat searchu-ul si select-ul 
                                astfel incat un user sa poata cauta si selecta un artist sau un host.

## Principii POO folosite :

* interfete pentru implementarea metodeleor pentru Artist si Host.
* mosteniri (Artist si Host mostenesc User).
* design pattern Visitor pentru implementarea metodei de verificarii a utilizarii unui obiect de tip User/Artist/Host/Album/Podcast.
* expresii lambda pentru sortare.