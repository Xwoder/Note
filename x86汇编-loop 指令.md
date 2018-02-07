# x86汇编-loop 指令

```asm
assume cs:codeseg

codeseg segment

start:
    mov ax, 0h
    mov cx, 5h

loop_add:
    add ax, 10h
    loop loop_add
    mov bx, ax
    
    ; 退出
    mov ah, 4ch
    int 21h

codeseg ends

end start
```

