    .text
niz: .byte	-10,0,30,11,-20,32,-22,76,54,12
    .align
.global __start
__start:

    adr r0, niz
    mov r1, #1 @ stevec
    mov r2, #0 @ index
    ldrsb r3, [r0] @ r3 = min

zanka: ldrsb r4, [r0, r1]
       cmp r4, r3
       movlt r3, r4
       movlt r2, r1

       addlo r1, r1, #1
       cmp r1, #10
       blo zanka

ldrsb r5, [r0]
strb r5, [r0, r2]
strb r3, [r0]

__end: b __end