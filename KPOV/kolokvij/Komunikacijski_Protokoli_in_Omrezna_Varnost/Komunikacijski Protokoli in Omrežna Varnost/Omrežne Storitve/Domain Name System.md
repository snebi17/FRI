***DNS je definiran, kot imeniška storitev.***

Storitev DNS uporabljamo za *preslikavanje med črkami / nizi v števila in obratno*. 

Sicer obstaja tudi statično preslikovanje, ki sestoji iz datoteke zapisov ipjev in njihovih domenskih imen. Vendar je to zelo slabo skalirana zadeva. Na podobnem konceptu temelji DNS, le da so te zapisi na strežniku nekje in ne lokalno na računalnikih in ostalih hostih. DNS strežniki so hierarhično urejeni:

- **ROOT NAME SERVERS** te vsebujejo top level domenske zapise
- **Top Level Domain servers**, te vsebujejo vse zapise, do autoritativnega serverja. To pa vejo, ker jih update-a register, njih pa to sporoči registrar, ki domeni pove katere autoritativne domenske serverje naj uporablja
- **Authoritative name server**, pa vsebuje dejanski dns zapis za domeno v povpraševanju.

DNS strežnik lahko imamo ročno vnešen v sistem ali pa ga pridobimo preko DHCP-ja.

DNS uporablja standardna vrata oz. port št. 53 in standardno uporablja tudi UDP transportni protokol; št. 17.