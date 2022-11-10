    .text
s1: .byte 255
    .align

.global __start
__start: 

    adr r0, s1
    ldrb r1, [r0]

@ a)
    and r2, r1, #63

@ b)
    orr r2, r1, #26

@ c)
    eor r2, r1, #9

nek_ukaz: 

@ d)
    mov r2, #16
    tst r1, r2
    bne nek_ukaz

@ e)
    add r2, r1, #36
    cmp r2, #32
    beq nek_ukaz

b __end

__end: b __end

00100100