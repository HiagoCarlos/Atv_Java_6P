# 🌍 Projeto: atvaula10 - API de Conversão

Esta é uma **API REST simples** construída com **Java** e **Spring Boot (v3.5.7)** que serve como um **conversor de moedas e unidades**.

---

## 🧩 Arquitetura e Funcionamento

A API segue uma arquitetura padrão de **Controller-Service**, com **validação e tratamento de exceções centralizado**:

- **ConversionController**:  
  Recebe as requisições HTTP (GET), lê os parâmetros da URL (ex: `from`, `to`, `amount`) e chama o `ConversionService`.

- **ConversionValidator**:  
  Antes de qualquer cálculo, verifica se as unidades são suportadas (ex: `"USD"`, `"BRL"`, `"C"`, `"F"`) e se os valores são válidos (não negativos, dentro dos limites).

- **ConversionService**:  
  Contém a lógica de negócio e realiza os cálculos de conversão.  
  As taxas de câmbio estão fixas no código neste exemplo.

- **GlobalExceptionHandler**:  
  Captura exceções personalizadas, como `UnsupportedUnitException` ou `NegativeAmountNotAllowedException`, retornando uma resposta JSON formatada (`Status 400 Bad Request`) em vez de um erro genérico do Spring.

---

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.7**
- **Maven** (para gerenciamento de dependências)

---

## 🚀 Como Executar o Projeto

### 🔧 Pré-requisitos
- Java **JDK 17** (ou superior) instalado.  
- Maven instalado (ou usar o **Maven Wrapper** — `mvnw` — que já vem no projeto).

### ▶️ Passos para executar

1. Abra um terminal na **pasta raiz do projeto** (onde está o arquivo `pom.xml`).
2. Execute o comando para iniciar o servidor Spring Boot:

#### 💻 No Windows:

./mvnw.cmd spring-boot:run
----

#### 🌐 Endpoints da API

1. Você pode testar os endpoints no navegador, Postman ou Insomnia.
2. O servidor roda na porta 8888.

---

#### 💵 Endpoint 1: Conversão de Moeda

1. Converte entre USD, BRL e EUR.

URL:

http://localhost:8888/convert/currency

2. Parâmetros:
from, to, amount

Exemplo de sucesso:

http://localhost:8888/convert/currency?from=USD&to=BRL&amount=100


Resposta (JSON):

{
  "fromUnit": "USD",
  "toUnit": "BRL",
  "originalValue": 100.0,
  "convertedValue": 540.0,
  "result": "100.00 USD = 540.00 BRL"
}

---

#### 🌡️ Endpoint 2: Conversão de Temperatura

1. Converte entre Celsius (C) e Fahrenheit (F).

URL:

http://localhost:8888/convert/unit/temperature


Parâmetros:
from, to, value

Exemplo de sucesso:

http://localhost:8888/convert/unit/temperature?from=C&to=F&value=37


Resposta (JSON):

{
  "fromUnit": "C",
  "toUnit": "F",
  "originalValue": 37.0,
  "convertedValue": 98.6,
  "result": "37.0°C = 98.6°F"
}

---

#### 📏 Endpoint 3: Conversão de Distância

Converte entre Quilômetros (KM) e Milhas (MI).

URL:

http://localhost:8888/convert/unit/distance


Parâmetros:
from, to, value

Exemplo de sucesso:

http://localhost:8888/convert/unit/distance?from=KM&to=MI&value=10


Resposta (JSON):

{
  "fromUnit": "KM",
  "toUnit": "MI",
  "originalValue": 10.0,
  "convertedValue": 6.21371,
  "result": "10.00 KM = 6.21 MI"
}

---

#### ⚠️ Tratamento de Erros

A API retorna respostas tratadas e formatadas quando ocorrem erros.

❌ Exemplo 1: Unidade Não Suportada

Requisição:

http://localhost:8888/convert/currency?from=USD&to=JPY&amount=100


Resposta (Status 400):

{
  "status": 400,
  "error": "Unidade Inválida",
  "message": "Unidade 'para' (to) não suportada: JPY"
}
----

#### 🚫 Exemplo 2: Valor Negativo

Requisição:

http://localhost:8091/convert/currency?from=USD&to=BRL&amount=-50


Resposta (Status 400):

{
  "status": 400,
  "error": "Valor Inválido",
  "message": "Valor não pode ser negativo: -50.0"
}

#### 👨‍💻 Autor

Hiago Carlos Cruvinel Silva

