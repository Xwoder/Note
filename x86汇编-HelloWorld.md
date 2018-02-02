# x86汇编-HelloWorld

```asm
assume cs:codeseg ds:dataseg

; 数据段
dataseg segment
    msg db 'Hello World', 24h
dataseg ends

; 代码段
codeseg segment

start:
    ; 设置 ds 寄存器
    mov ax, dataseg
    mov ds, ax

    ; 设置 dx 寄存器
    mov dx, offset msg

    mov ah, 9h
    int 21h

    mov ah, 4ch
    int 21h
codeseg ends

end start
```

