    .text
niz: .ascii "OR: petek 12-14"
     .byte 0
    .align

.global __start
__start:

    adr r0, niz
    mov r1, #0

zanka:  ldrb r3, [r0, r1]
        cmp r3, #0
        beq __end
        cmp r3, #'a'
        blo nadaljuj
        cmphs r3, #'z'
		andls r2, r3, #223
        strb r2, [r0, r1]
        add r1, r1, #1
        b zanka

nadaljuj:   add r1, r1, #1
            b zanka

__end: b __end


1