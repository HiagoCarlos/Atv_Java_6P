# 📌 Monitoramento de Sensores em Tempo Real (UDP)

Este projeto simula múltiplos sensores (clientes) que enviam dados de **temperatura** e **umidade** para um **servidor central** em tempo real.

A comunicação é feita com o protocolo **UDP** (User Datagram Protocol), que é ideal para fluxos contínuos de dados onde a velocidade é mais crítica do que a garantia de entrega. Não importa perder uma leitura ocasional, pois novas chegam a cada segundo.

---

## 🚀 Estrutura do Projeto

O projeto é composto por dois arquivos Java principais:

-   `ServidorUDP.java`: O servidor central que fica ouvindo em uma porta específica para receber, processar e exibir os dados enviados pelos sensores.
-   `ClienteUDP.java`: O cliente que simula um sensor. Ele gera dados aleatórios de temperatura e umidade e os envia periodicamente para o servidor.

---

## ⚙️ Como Compilar

Para executar o projeto, primeiro você precisa compilar os arquivos `.java` para `.class`. Você pode fazer isso de duas maneiras.

### Opção 1: Compilar para uma pasta `out/` (Mais organizado ✅)

Esta é a abordagem recomendada para manter os arquivos-fonte (`.java`) separados dos arquivos compilados (`.class`).

1.  Crie um diretório chamado `out` (se ele ainda não existir).
2.  Execute o seguinte comando no terminal:

    ```bash
    javac -d out ServidorUDP.java ClienteUDP.java
    ```

3.  Isso irá gerar a seguinte estrutura de pastas:

    ```
    out/
    ├── ServidorUDP.class
    └── ClienteUDP.class
    ```

### Opção 2: Compilar no mesmo diretório

Esta é uma abordagem mais simples, mas mistura os arquivos-fonte e os compilados.

1.  Execute o seguinte comando no terminal:

    ```bash
    javac ServidorUDP.java ClienteUDP.java
    ```

2.  Isso vai gerar os arquivos compilados no mesmo local dos arquivos-fonte:
    -   `ServidorUDP.class`
    -   `ClienteUDP.class`

---

## ▶️ Como Executar

Depois de compilar, siga estes passos para rodar a simulação.

### 1. Inicie o Servidor

Primeiro, você precisa iniciar o servidor para que ele comece a ouvir por conexões dos sensores.

-   **Se você compilou na pasta `out/`:**

    ```bash
    java -cp out ServidorUDP
    ```

-   **Se você compilou no mesmo diretório:**

    ```bash
    java ServidorUDP
    ```

Após executar o comando, o servidor exibirá a seguinte mensagem, indicando que está pronto:

```
Servidor UDP ouvindo na porta 12345...
```

### 2. Inicie um Cliente (Sensor)

Agora, em um **novo terminal**, inicie um cliente para começar a enviar dados.

-   **Se você compilou na pasta `out/`:**

    ```bash
    java -cp out ClienteUDP
    ```

-   **Se você compilou no mesmo diretório:**

    ```bash
    java ClienteUDP
    ```

O terminal do cliente começará a mostrar os dados que está enviando (o ID do sensor é gerado aleatoriamente):

```
Enviado: Sensor42: 25.3°C, 65%RH
Enviado: Sensor42: 26.1°C, 60%RH
...
```

### 3. Inicie Vários Clientes (Opcional)

Para simular um ambiente com múltiplos sensores, basta abrir novos terminais e executar o comando do cliente novamente em cada um deles.

-   **Comando para cada novo cliente (exemplo com `out/`):**

    ```bash
    java -cp out ClienteUDP
    ```

Cada novo cliente terá um ID de sensor diferente e enviará seus próprios dados.

### 4. Observe o Servidor

Volte para o terminal onde o servidor está rodando. Ele exibirá as últimas leituras recebidas de cada sensor conectado, atualizando a tela periodicamente:

```
===== Leituras Atuais =====
Sensor42 -> 25.3°C, 65%RH
Sensor17 -> 24.7°C, 70%RH
Sensor99 -> 22.1°C, 68%RH
===========================
```