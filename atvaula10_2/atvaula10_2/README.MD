# API Calculadora de IMC (Spring Boot)

Esta é uma simples API RESTful construída com Spring Boot que calcula o Índice de Massa Corporal (IMC), fornece a classificação (de acordo com a OMS) e valida os dados de entrada.

## Descrição

O projeto `atvaula10_2` expõe endpoints HTTP para calcular o IMC com base no peso (em kg) e altura (em metros). Ele foi projetado para demonstrar conceitos fundamentais do Spring Boot, incluindo:

* **Padrão Controller-Service:** Separação clara de responsabilidades.
* **DTO (Data Transfer Object):** Uso de `record` Java para formatar os dados de resposta.
* **Exceções Personalizadas:** Lançamento de exceções específicas de negócios (`InvalidHeightException`, `InvalidWeightException`).
* **Manipulação Global de Exceções:** Uso de `@ControllerAdvice` para capturar exceções e retornar respostas HTTP `400 Bad Request` amigáveis.

## Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3+**
* **Spring Web:** Para criar os endpoints REST.
* **Maven:** Para gerenciamento de dependências.

## Como Executar

### Pré-requisitos

* JDK 17 ou superior instalado.
* Apache Maven instalado.
* Um editor de código ou IDE (como IntelliJ IDEA ou VS Code).

### Passos para Execução

1.  **Clone o repositório** (ou certifique-se de estar no diretório raiz do projeto).

2.  **Execute via Maven (Terminal):**
    Navegue até a pasta raiz do projeto (onde o `pom.xml` está localizado) e execute o seguinte comando:

    ```bash
    mvn spring-boot:run
    ```

3.  **Execute via IDE:**
    Importe o projeto como um projeto Maven em sua IDE de preferência e execute a classe principal `Atvaula102Application.java`.

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

A API expõe dois endpoints principais, ambos acessíveis via método `GET`.

---

### 1. Cálculo Completo do IMC

Retorna um objeto JSON (DTO) com o valor do IMC, a classificação e a faixa recomendada pela OMS.

* **Endpoint:** `GET /imc`
* **Parâmetros de Query:**
    * `peso` (obrigatório): O peso em quilogramas (ex: `70.5`).
    * `altura` (obrigatório): A altura em metros (ex: `1.75`).

#### Exemplo de Requisição (Sucesso)

`GET http://localhost:8080/imc?peso=80&altura=1.80`

#### Exemplo de Resposta (Sucesso - `200 OK`)

```json
{
  "imc": 24.69,
  "classificacao": "Peso normal",
  "faixaRecomendadaOms": "Faixa de peso normal recomendada pela OMS: 18.5 a 24.9"
}
```

---

### 2. Obter Apenas a Classificação

Retorna uma `String` simples contendo apenas a classificação do IMC.

* **Endpoint:** `GET /imc/classificacao`
* **Parâmetros de Query:**
    * `peso` (obrigatório): O peso em quilogramas.
    * `altura` (obrigatório): A altura em metros.

#### Exemplo de Requisição (Sucesso)

`GET http://localhost:8080/imc/classificacao?peso=120&altura=1.75`

#### Exemplo de Resposta (Sucesso - `200 OK`)

```
"Obesidade Grau II"
```

---

### Tratamento de Erros (Validação)

A API valida as entradas de `peso` e `altura` com base nas seguintes regras:
* **Altura:** Deve estar entre 0.5m e 2.5m. Não pode ser zero.
* **Peso:** Deve estar entre 2kg e 400kg.

Se alguma regra for violada, a API retornará um erro `400 Bad Request`.

#### Exemplo de Requisição (Inválida)

`GET http://localhost:8080/imc?peso=70&altura=0`

#### Exemplo de Resposta (Erro - `400 Bad Request`)

```json
{
  "status": "400",
  "error": "Bad Request",
  "message": "Altura não pode ser zero."
}
```

#### Exemplo de Requisição (Parâmetro Faltando)

`GET http://localhost:8080/imc?peso=70`

#### Exemplo de Resposta (Erro - `400 Bad Request`)

```json
{
  "status": "400",
  "error": "Bad Request",
  "message": "Parâmetro obrigatório ausente: altura"
}
```