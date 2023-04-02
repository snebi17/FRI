# Transportna Plast
Transportna plast skrbi za povezovanje dveh oddaljenih procesov, ki komunicirajo na višjih plasteh. Ta plast ustvari neko t. i. virtualno povezavo med dvema sistemama, kjer poveže L4 oz. četrto plast, po kateri lahko poteka zanesljiva komunikacija, s kontrolo prometa in zamašitev.

Podatkovne enote na tej plasti se imenuje *SEGMENTI*, transportna plast ovije podatke (enkapsulira) v svoj segment in tega posreduje naprej omrežni plasti. Na nasprotni strani prejemnika pa transportna plast dekapsulira in podatke pošlje naprej aplikacijski plasti, na kateri komunicirajo aplikacijski protokoli.


# Glavna protokola na tej plasti sta TCP in UDP
Vsak proces ima vstopno točko iz Aplikacijske plasti v Transportno, ta točka se imenuje vtič oz. sock, preko tega vtiča aplikacija sprejema in oddaja promet v omrežje. 

## Vtič
Vtič je sestavljen iz dveh delov, IP naslova, preko katerega naprava na nižjih nivojih komunicira in port-a oz. vrat, ta številka je kot nek naslov s katerega lahko komunicira transportna plast, mišljeno, kakor je IP omrežni plasti, je tudi port Transportni plasti. Porti so lahko rezervirani ali pa splošni za uporabo, njihovo definicijo določajo razni standardi. Porti s številko od 0-1023 so t. i. *well-known ports*, ti so določeni na vsakem sistemu in na njih delujejo samo določeni procesi:
- HTTP: 80
- SMTP: 25
- DNS: 53
- TELNET: 23
- IRC: 194


## Glava transportne plasti
Tako TCP, kot UDP imasta podobno zgradbo glave transportne plasti;

![[Pasted image 20220608065701.png]]


## UDP
UDP je najbolj osnoven protokol transportne plasti, ki ne zahteva veliko večji overhead, **samo 8B**. 
![[Pasted image 20220608070308.png]]
Uporablja se v okoljih, kjer se lahko tolerirajo izgube in je pomembna hitrost (video prenos, multimedija, DNS, usmerjevalni protokoli...) 
![[Pasted image 20220608070531.png]]

Če hočemo zagotoviti zanesljivost UDP-ja moramo to storiti na aplikacijski plasti.

## TCP
TCP je kompleksen protokol, ki zagotavlja zanesljivo dostavo podatkov z uporabo nezanesljivega kanala, torej totalno upošteval end-to-end princip. Tak protokol nudi:
- Da se podatki ne okvarijo (0 <-> 1)
- Da se podatki ne izgubljajo 
- Da so podatki oz. segmenti dostavljeni v pravilnem zaporedju

TCP se izvaja point-to-point, zmeraj med dvema točkama. En pošiljatej in en sprejemnik. Znotraj TCP povezave povezava poteka dvosmerno (FULL-DUPLEX), nudi zanesljiv prenos podatkov. Je povezavni protokol, kar pomeni da vzpostavi in ruši zvezo, v zvezi pa nudi tudi kontrolo zamašitve.

TCP definira neko drseče okno, ki se povečuje ali pa manjša glede na povezavnost in zmašenost povezave. To okno določa velikost segmenta, ki se lahko prenese.

![[Pasted image 20220608081351.png]]

Zaporedna št. se šteje v bytih, ta je zaporedna št. prvega byta v segmentu, v nasprotju s ACK, je ta zaporedna št. naslednjega pričakovanega byta.

## Three-way handshake
To rokovanje je standardno in omogoči vzpostavitev TCP seje.  Imenuje se trosmerno, saj se zgodi tako:
![[Pasted image 20220608082203.png]]
SEQ je na začetku naključno določena, zaradi varnosti!

![[Pasted image 20220608083153.png]]
	