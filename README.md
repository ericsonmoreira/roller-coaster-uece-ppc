# Problema da Montanha Russa

<p align="center">
    <img alt="Universidade" src="https://img.shields.io/static/v1?label=university&message=UECE&color=13ad47&labelColor=0A1033">
    <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=13ad47&labelColor=0A1033">
    <img alt="Linguagem" src="https://img.shields.io/static/v1?label=java&message=1.8&color=13ad47&labelColor=0A1033">
</p>

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

## Como testar

Siga os seguintes passos para testar o projeto: 

```shell
# download do projeto
git clone https://github.com/ericsonmoreira/roller-coaster-uece-ppc.git

# entrar na pasta
cd roller-coaster-uece-ppc

# teste para um carro
java -jar out/artifacts/roller_coaster_uece_ppc_jar/roller-coaster-uece-ppc.jar configs/config01.txt

# teste para dois carros
java -jar out/artifacts/roller_coaster_uece_ppc_jar/roller-coaster-uece-ppc.jar configs/config02.txt

# teste para três carros
java -jar out/artifacts/roller_coaster_uece_ppc_jar/roller-coaster-uece-ppc.jar configs/config03.txt
```

Os testes foram separados em 3 configurações:

- N:  52, M: 1, C: 4, TE: 1, TM: 10, TP_MIN: 1 e TP_MAX: 3
- N:  92, M: 2, C: 4, TE: 1, TM: 10, TP_MIN: 1 e TP_MAX: 3
- N: 148, M: 3, C: 4, TE: 1, TM: 10, TP_MIN: 1 e TP_MAX: 3

Onde:

- **N:** Número de passageiros
- **M:** Número de carros
- **C:** Número bancos em um carro
- **TE:** Tempo de embarque e desembarque em um carro em segundos 
- **TM:** Tempo que um carro leva para dar uma volta em segundos
- **TP_MIN:** Tempo mínimo de chegada dos passageiros à montanha russa em segundos
- **TP_MAX:** Tempo máximo de chegada dos passageiros à montanha russa em segundos

## Resultados

Um dos requisitos é que no final da execução do código deverão ser calculados:

- Tempo mínimo, máximo e médio de espera dos passageiros na fila
- Tempo de utilização do(s) carros (tempo movimentando/tempo total)

No final da nossa implementação são apresentados as seguintes informações:

- Tempo Total App
- Menor tempo de um passageiro na fila
- Maior tempo de um passageiro na fila
- Média tempo de um passageiro na fila
- Tempo de movimentação do(s) carro(s)
- Tempo Utilização do(s) carro(s): 0.7969568975558321
- Tempo Utilização do(s) carro(s): 0.7969568975558321

### Um carro :car:

```shell
=============== Relatório ===============
Tempo Total App: 163123 ms
Menor tempo de um passageiro na fila: 1000 ms
Maior tempo de um passageiro na fila: 72998 ms
Média tempo de um passageiro na fila: 37423.86538461538 ms
Tempo de movimentação do(s) carro(s): 130002 ms
Tempo utilização do carro: Car{id=0}: 0.7969568975558321
Tempo utilização do(s) carro(s): 0.7969568975558321
============== Fim Relaório ==============
```

### Dois carros :car: :car:

```shell
=============== Relatório ===============
Tempo Total App: 260120 ms
Menor tempo de um passageiro na fila: 1000 ms
Maior tempo de um passageiro na fila: 98982 ms
Média tempo de um passageiro na fila: 46436.84782608696 ms
Tempo de movimentação do(s) carro(s): 230006 ms
Tempo utilização do carro: Car{id=0}: 0.4613409195755805
Tempo utilização do carro: Car{id=1}: 0.4228894356450869
Tempo utilização do(s) carro(s): 0.8842303552206674
============== Fim Relaório ==============
```

### Três carros :car: :car: :car:

```shell
=============== Relatório ===============
Tempo Total App: 414147 ms
Menor tempo de um passageiro na fila: 1000 ms
Maior tempo de um passageiro na fila: 166977 ms
Média tempo de um passageiro na fila: 75869.02027027027 ms
Tempo de movimentação do(s) carro(s): 370009 ms
Tempo utilização do carro: Car{id=0}: 0.2897594332447175
Tempo utilização do carro: Car{id=1}: 0.2897570186431388
Tempo utilização do carro: Car{id=2}: 0.31390786363296125
Tempo utilização do(s) carro(s): 0.8934243155208175
============== Fim Relaório ==============
```

## Arquivos

<pre><font color="#BD93F9"><b>.</b></font>
├── <font color="#BD93F9"><b>configs</b></font>
│         ├── config01.txt
│         ├── config02.txt
│         ├── config03.txt
│         └── config-teste.txt
├── LICENSE.md
├── README.md
├── roller-coaster-uece-ppc.iml
└── <font color="#BD93F9"><b>src</b></font>
    ├── Car.java
    ├── MontanhaRussa.java
    ├── Passenger.java
    ├── PrinterColors.java
    └── Printer.java
</pre>

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