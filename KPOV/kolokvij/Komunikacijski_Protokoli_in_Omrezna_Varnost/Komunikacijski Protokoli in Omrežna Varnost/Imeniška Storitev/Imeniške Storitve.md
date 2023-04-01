Imenik ali mapa, angl. *directory services*. Osnovni gradnik teh je točno direktorij, ki je sestavljen iz večih virov oz. atributov/prilastkov, te so tudi lahko drugi direktoriji. Nekateri te atributi so lahko obvezni, nekateri pa so opcijski. Imeniška storitev je torej hierarhična. 

Strukturo imenikov in prilastov definira *shemo*.

V isti mapi imamo lahko več istoimenskih prilastov vendar različni tip ali pa dejanska različna vrednost. Dejanski zapisi in implementacija teh direktorijev in njihovih prilastov *imenujemo objekti*. Diretorij je objekt, ki vsebuje druge objekte oz. je imenovan *kontejner*. Vsi predmeti v kontejnerju so v istem imenskem prostoru t.j. *namespace*.
Objekti živijo v imenskem prostoru! Atributi živijo v kontejnerju.

Kljub temu, da imajo lahko te objekti ista imena, jih moramo med seboj razlikovati, ta način razlikovanja je del načrtovanja imenika in za imenovanje, moramo uporabiti pravila, ki določajo enolično in nedvoumno ime. Temu imenu pravimo razloževalno ime ali **distinguished name**. To je lahko relativno na hierarhijo imenikov ali pa absolutno. Takšno ime običajno ni shranjeno v imeniški strukturi, pač pa je definirano s pravili.

![[Pasted image 20230112183326.png]]

Imeniška storitev pride prav, saj abstrahiramo ostale storitve, ki potrebujejo dostop do neke podatkovne baze uporabnikov, tako da te uporabljajo točn to imeniško storitev. Nato če hočemo dostopati do baze, lahko uporabimo imeniško storitev in nismo vezani na uporabo ostalih storitev, ki uporabljajo bazo. Seveda te storitve lahko uporabljajo svoje baze.
![[Pasted image 20230112192420.png]]

Imeniške strukture oz. sheme so standardizirane, te standarde vzdržuje IANA. To pa zato, da se lahko ena shema posluži uporabljenosti večim storitvam (privzete vrednosti ipd. ipd.)

[[Domain Name System]] je imeniška storitev. Imenski prostor v DNS-ju določa FQN - Fully Qualified Name, kar je distinguished name.


Imeniške storitve opisuje [[Standard X.500]], vendar se je v praksi izkazal za pre-teoretičnega in kompliciranega, tako se v praksi ni prijel vendar je ostal *de iure*. 

Se je pa razvila praktična implementacije tega standarda imenovana 
[[LDAP]].
