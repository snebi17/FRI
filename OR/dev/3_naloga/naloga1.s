@ Napišite podprogram paser za 8-bitno paralelno serijsko pretvorbo. Podprogram naj preko registrov sprejme dva parametra:
@                  r0 – število, ki ga pretvarjamo v serijski zapis,
@                  r1 – kazalec na 8 bajtov, v katere se zapišejo posamezni biti.

@ Po izvajanju morajo biti v osmih zaporednih naslovih, od r1 dalje vrednosti, ki ustrezajo posameznim bitom števila, najprej najbolj pomembni bit.
@ Za preizkus uporabite vrednost 0b01011011.

@                  mov r0, #0b01011011
@                  adr r1, tab8
@                  bl paser

@ V pomnilniškem bloku tab8 (tabela osmih bajtov) morajo biti po izvajanju naslednje vrednosti: 0, 1, 0, 1, 1, 0, 1, 1

@ Namig: uporabite zanko (8 obhodov), v kateri v vsakem obhodu iz števila v r0 dobite en bit (preverjanje stanja enega bita) in ga zapišete na naslov, ki je določen z r1.


    .text
tab8: .space 8
    .align
.global _start

_start: 
    mov r1, #0b01011011
    adr r1, tab8
_end: b _end