# Problema da Montanha Russa

<img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=E51C44&labelColor=0A1033">
<img alt="License" src="https://img.shields.io/static/v1?label=java&message=1.8&color=E51C44&labelColor=0A1033">


![cover](.github/roller-coaster.jpg?style=flat)

## :fire: Introdução

O **Problema da Montanha Russa (Roller Coaster)** é um exemplo lúdico de um problema muito comum em controle de
processos. O problema simula uma montanha russa onde pessoas entram na fila esperando a vez, depois entram no carro, que
quando estiver cheio, parte para a viagem até retornar para pegar novos passageiros.

Para resolvê-lo deve-se usar princípios básico de **programação concorrente**.

## :roller_coaster: Formulação do problema

O problema da Montanha Russa usa apenas três processos: a montanha russa com o processo main() os passageiros e o(s)
carro(s).

O sistema não possui um _"controlador"_ (ou a pessoa que controla a movimentação da montanha russa), isto é, a função
MontanhaRussa() apenas cria os carros e os passageiros. Depois disso, os Passageiro() e Carro() se autocontrolarão
sozinhos, isto é, os passageiros saberão a hora de esperar na fila, entrar no carro, sair do carro e o carro saberá
quando sair, conforme as condições foram atendidas.

Desenvolver um algoritmo concorrente e códigos para a montanha russa, o carro e os passageiros. Desenvolver uma solução
para sincronizá-los usando exclusão mútua com espera bloqueada. Pense em escrever o código genérico, prevendo os demais
casos.

## Descrição dos algoritmos

## Descrição da Implementação

Diagrama de classes:

![uml](.github/uml.svg?style=flat)

### Classe [Montanha Russa][1]

### Classe [Passenger][2]

### Classe [Car][3]

### Classe [Printer][4]

### Enum [PrinterColors][5]

## :computer: Requisitos do sistema

1. Algoritmo concorrente
2. Sistema não possui um _"controlador"_
3. Códigos para a montanha russa, carro e passageiros
4. Código genérico, prevendo os demais casos

User reference [Montanha Russa][1]

## O que o relatório deve ter

- Formulação do problema
- Descrição dos algoritmos
- Descrição da Implementação (diagrama de classes)
- Resultados

[1]: src/MontanhaRussa.java

[2]: src/Passenger.java

[3]: src/Car.java

[4]: src/Printer.java

[5]: src/PrinterColors.java