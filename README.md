# Documentação da API - Gerenciador de Tarefas

## Como Executar a API

Para executar a API, você precisa ter o Docker e o Docker Compose instalados na sua máquina.

### Passos para Execução

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/obrunoapolinario/todo-list-back
   cd todo-list-back
   ```
2. **Configurar credênciais (Opcional):**
   Configurar as credenciais do banco de dados no arquivo `docker-compose.yml` e `application-properties` caso queira trocar as url's de conexão com o banco de dados.
3. **Suba o container do banco de dados**
    ```bash
    docker-compose up -d
    ```
4. **Executar a API a partir da sua IDE:**
- Abra o projeto na sua IDE
- Certifique-se de que o banco de dados esteja em execução.
- Localize a classe principal `TodoListApplication.java` no pacote `com.newm.todo_list`.
- Clique com o botão direito na classe e selecione a opção para executar (Run) ou use o atalho correspondente (Shift + F10 no IntelliJ).
4. **Acessar a API:**
    A api estará disponivel em `http://localhost:8080/tarefas`

Aqui está a documentação específica de cada endpoint da sua API do Gerenciador de Tarefas. Essa seção pode ser adicionada à documentação existente para fornecer detalhes completos sobre como usar cada um dos endpoints.

# Documentação da API - Gerenciador de Tarefas

## Endpoints

### 1. Criar Tarefa

- **Método:** `POST`
- **URL:** `/tarefas`
- **Descrição:** Cria uma nova tarefa.
- **Requisição:**
    - **Body:** Um objeto JSON representando a tarefa a ser criada.
        ```json
        {
          "titulo": "Título da Tarefa",
          "descricao": "Descrição da Tarefa",
          "status": "NAO_INICIADO" // opcional, padrão é "NAO_INICIADO"
        }
        ```
- **Resposta:**
    - **Status 201 Created**
    - **Body:**
        ```json
        {
          "id": "UUID_DA_TAREFA",
          "titulo": "Título da Tarefa",
          "descricao": "Descrição da Tarefa",
          "status": "NAO_INICIADO"
        }
        ```

### 2. Listar Tarefas

- **Método:** `GET`
- **URL:** `/tarefas`
- **Descrição:** Retorna uma lista contendo todas as tarefas.
- **Resposta:**
    - **Status 200 OK**
    - **Body:**
        ```json
        [
          {
            "id": "UUID_DA_TAREFA",
            "titulo": "Título da Tarefa",
            "descricao": "Descrição da Tarefa",
            "status": "NAO_INICIADO"
          },
          ...
        ]
        ```

### 3. Buscar Tarefa por ID

- **Método:** `GET`
- **URL:** `/tarefas/{id}`
- **Descrição:** Retorna os detalhes de uma tarefa específica, identificada pelo seu ID.
- **Parâmetros:**
    - `id`: UUID da tarefa que deseja buscar.
- **Resposta:**
    - **Status 200 OK**
    - **Body:**
        ```json
        {
          "id": "UUID_DA_TAREFA",
          "titulo": "Título da Tarefa",
          "descricao": "Descrição da Tarefa",
          "status": "NAO_INICIADO"
        }
        ```
    - **Status 404 Not Found** (se a tarefa não for encontrada)

### 4. Atualizar Tarefa

- **Método:** `PUT`
- **URL:** `/tarefas/{id}`
- **Descrição:** Atualiza todos os campos de uma tarefa específica.
- **Parâmetros:**
    - `id`: UUID da tarefa que deseja atualizar.
- **Requisição:**
    - **Body:** Um objeto JSON representando a tarefa atualizada.
        ```json
        {
          "titulo": "Novo Título da Tarefa",
          "descricao": "Nova Descrição da Tarefa",
          "status": "EM_ANDAMENTO" // opcional
        }
        ```
- **Resposta:**
    - **Status 200 OK**
    - **Body:**
        ```json
        {
          "id": "UUID_DA_TAREFA",
          "titulo": "Novo Título da Tarefa",
          "descricao": "Nova Descrição da Tarefa",
          "status": "EM_ANDAMENTO"
        }
        ```
    - **Status 404 Not Found** (se a tarefa não for encontrada)

### 5. Atualizar Tarefa Parcialmente

- **Método:** `PATCH`
- **URL:** `/tarefas/{id}`
- **Descrição:** Atualiza alguns campos de uma tarefa específica.
- **Parâmetros:**
    - `id`: UUID da tarefa que deseja atualizar.
- **Requisição:**
    - **Body:** Um objeto JSON com os campos que deseja atualizar.
        ```json
        {
          "status": "EM_ANDAMENTO" // opcional
        }
        ```
- **Resposta:**
    - **Status 200 OK**
    - **Body:**
        ```json
        {
          "id": "UUID_DA_TAREFA",
          "titulo": "Título da Tarefa",
          "descricao": "Descrição da Tarefa",
          "status": "EM_ANDAMENTO"
        }
        ```
    - **Status 404 Not Found** (se a tarefa não for encontrada)

### 6. Deletar Tarefa

- **Método:** `DELETE`
- **URL:** `/tarefas/{id}`
- **Descrição:** Deleta uma tarefa específica.
- **Parâmetros:**
    - `id`: UUID da tarefa que deseja deletar.
- **Resposta:**
    - **Status 204 No Content** (se a tarefa foi deletada com sucesso)
    - **Status 404 Not Found** (se a tarefa não for encontrada)

### Front-end

Para acessar o código-fonte do front-end, visite o repositório no GitHub: [Front-end do Gerenciador de Tarefas](https://github.com/obrunoapolinario/todo-list-front).

## Estrutura da Aplicação

A aplicação é construída usando Spring Boot e utiliza o PostgreSQL como banco de dados. Aqui estão os principais pacotes e suas responsabilidades:

## Pacote com.newm.todo_list

- `TodoListApplication.java`: Classe principal da aplicação que inicializa o Spring Boot.

## Pacote com.newm.todo_list.services

- `TarefaService.java`: Contém a lógica de negócios para manipular tarefas. As principais funcionalidades incluem:
    - Criar nova tarefa.
    - Listar todas as tarefas.
    - Buscar tarefa por ID.
    - Atualizar tarefa existente.
    - Deletar tarefa.

## Pacote com.newm.todo_list.repositories

`TarefaRepository.java`: Interface que estende JpaRepository, fornecendo métodos para interagir com a tabela de tarefas no banco de dados.

Pacote com.newm.todo_list.domain.tarefa

- `Tarefa.java`: Entidade que representa uma tarefa no banco de dados.
- `TarefaDTO.java`: Data Transfer Object (DTO) para a tarefa, usado para transferir dados entre camadas.
- `StatusTarefa.java`: Enum que define os possíveis status de uma tarefa.

Pacote com.newm.todo_list.controllers

- `TarefaController.java`: Controlador REST que define os endpoints da API para gerenciar tarefas. Os principais endpoints incluem:
    - POST /tarefas: Criar uma nova tarefa.
    - GET /tarefas: Listar todas as tarefas.
    - GET /tarefas/{id}: Buscar tarefa por ID.
    - PUT /tarefas/{id}: Atualizar tarefa existente.
    - PATCH /tarefas/{id}: Atualizar tarefa parcialmente.
    - DELETE /tarefas/{id}: Deletar tarefa.

## Configurações do Banco de Dados

As configurações do banco de dados são definidas no arquivo application.properties e application-dev.properties:

O banco de dados PostgreSQL deve estar em execução na porta 5432.
As credenciais de acesso ao banco são as seguintes:
    Usuário: admin
    Senha: admin
