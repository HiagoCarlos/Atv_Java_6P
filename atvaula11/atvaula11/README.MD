# API REST de Gestão Acadêmica (atvaula11)

Este projeto é uma API RESTful completa desenvolvida com Spring Boot para gerenciar entidades de um sistema acadêmico: **Alunos**, **Professores**, **Funcionários** e **Departamentos**.

A API implementa operações de CRUD (Create, Read, Update, Delete) para cada uma dessas entidades e utiliza um banco de dados em memória (H2) para fácil configuração e teste.

## 🚀 Tecnologias Utilizadas

* **Java 25**
* **Spring Boot 3.5.7**
    * **Spring Web:** Para criação de endpoints REST.
    * **Spring Data JPA:** Para persistência de dados.
    * **Spring Boot Validation:** Para validação de dados de entrada.
* **Maven:** Gerenciador de dependências.
* **H2 Database:** Banco de dados relacional em memória.
* **Lombok:** Para reduzir código boilerplate (Getters, Setters, Construtores).

---

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura em camadas padrão para facilitar a manutenção e organização do código:

```
src/main/java/com/atvaula11/atvaula11/
│
├── Atvaula11Application.java   # Ponto de entrada da aplicação
│
├── model/                      # Entidades JPA (POJOs do banco)
│   ├── Aluno.java
│   ├── Departamento.java
│   ├── Funcionario.java
│   └── Professor.java
│
├── repository/                 # Interfaces JpaRepository (Acesso ao banco)
│   ├── AlunoRepository.java
│   ├── DepartamentoRepository.java
│   ├── FuncionarioRepository.java
│   └── ProfessorRepository.java
│
├── service/                    # Camada de regras de negócio
│   ├── AlunoService.java
│   ├── DepartamentoService.java
│   ├── FuncionarioService.java
│   └── ProfessorService.java
│
└── controller/                 # Controladores REST (Endpoints da API)
    ├── AlunoController.java
    ├── DepartamentoController.java
    ├── FuncionarioController.java
    └── ProfessorController.java
```

---

## ⚙️ Configuração e Execução

### Pré-requisitos

* **JDK 25** (ou a versão especificada no seu `pom.xml`).
* **Apache Maven** instalado e configurado no PATH do sistema.
* Uma IDE como **VS Code** (com o "Extension Pack for Java") ou IntelliJ.

### Passo 1: Instalar Dependências

O Maven cuidará disso automaticamente. Ao abrir o projeto no VS Code, ele deve começar a baixar as dependências listadas no `pom.xml`.

Se isso não acontecer, você pode forçar a instalação pelo terminal:
```bash
mvn clean install
```

### Passo 2: Executar a Aplicação

Existem duas formas fáceis de rodar o projeto:

**1. Pela IDE (VS Code):**
   * Abra o arquivo `src/main/java/com/atvaula11/atvaula11/Atvaula11Application.java`.
   * Aperte **F5** ou clique no botão "Run" (▶) que aparece acima do método `main`.

**2. Pelo Terminal (Maven):**
   * Abra um terminal na pasta raiz do projeto (onde está o `pom.xml`).
   * Execute o comando:
   ```bash
   mvn spring-boot:run
   ```
O servidor será iniciado na porta **`http://localhost:8080`**.

---

## 🧪 Testando a API

Após iniciar a aplicação, você pode testá-la de duas maneiras: acessando o console do banco H2 e usando os endpoints da API com uma ferramenta como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/).

### 1. Acessando o Console do Banco (H2)

Para verificar os dados sendo salvos, acesse o console web do H2:

1.  Abra seu navegador e vá para: **`http://localhost:8080/h2-console`**
2.  Preencha os campos de login exatamente como no arquivo `application.properties`:
    * **Driver Class:** `org.h2.Driver`
    * **JDBC URL:** `jdbc:h2:mem:testdb`
    * **User Name:** `sa`
    * **Password:** `password`
3.  Clique em **Connect**. Você verá as tabelas `ALUNO`, `DEPARTAMENTO`, `FUNCIONARIO` e `PROFESSOR` prontas para serem consultadas.

### 2. Endpoints da API

Aqui estão todos os endpoints disponíveis para teste.

#### 🎓 Alunos (`/api/alunos`)

* **`GET /api/alunos`**: Lista todos os alunos.
* **`GET /api/alunos/{id}`**: Busca um aluno específico pelo ID.
* **`POST /api/alunos`**: Cria um novo aluno.
    * **Body (JSON):**
    ```json
    {
      "nome": "João da Silva",
      "matricula": "2025001",
      "curso": "TSI"
    }
    ```
* **`PUT /api/alunos/{id}`**: Atualiza um aluno existente.
    * **Body (JSON):**
    ```json
    {
      "nome": "João da Silva Alterado",
      "matricula": "2025001",
      "curso": "Engenharia de Software"
    }
    ```
* **`DELETE /api/alunos/{id}`**: Deleta um aluno pelo ID.

---

#### 🏛️ Departamentos (`/api/departamentos`)

* **`GET /api/departamentos`**: Lista todos os departamentos.
* **`GET /api/departamentos/{id}`**: Busca um departamento pelo ID.
* **`POST /api/departamentos`**: Cria um novo departamento.
    * **Body (JSON):**
    ```json
    {
      "nome": "Ciência da Computação",
      "sigla": "CC"
    }
    ```
* **`PUT /api/departamentos/{id}`**: Atualiza um departamento.
* **`DELETE /api/departamentos/{id}`**: Deleta um departamento.

---

#### 👨‍🏫 Professores (`/api/professores`)

* **`GET /api/professores`**: Lista todos os professores.
* **`GET /api/professores/{id}`**: Busca um professor pelo ID.
* **`POST /api/professores`**: Cria um novo professor (requer um departamento existente).
    * **Body (JSON):**
    ```json
    {
      "nome": "Dr. Hiago",
      "especialidade": "Sistemas Distribuídos",
      "departamento": {
        "id": 1 
      }
    }
    ```
    *(**Nota:** Primeiro crie um departamento para obter um `id` válido.)*
* **`PUT /api/professores/{id}`**: Atualiza um professor.
* **`DELETE /api/professores/{id}`**: Deleta um professor.

---

#### 👥 Funcionários (`/api/funcionarios`)

* **`GET /api/funcionarios`**: Lista todos os funcionários.
* **`GET /api/funcionarios/{id}`**: Busca um funcionário pelo ID.
* **`POST /api/funcionarios`**: Cria um novo funcionário (requer um departamento existente).
    * **Body (JSON):**
    ```json
    {
      "nome": "Maria Coordenadora",
      "cargo": "Coordenadora de Curso",
      "salario": 5000.00,
      "departamento": {
        "id": 1
      }
    }
    ```
    *(**Nota:** Primeiro crie um departamento para obter um `id` válido.)*
* **`PUT /api/funcionarios/{id}`**: Atualiza um funcionário.
* **`DELETE /api/funcionarios/{id}`**: Deleta um funcionário.