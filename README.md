# API REST de Helpdesk

**Disciplina:** Técnicas de Integração de Sistemas  
**Equipe:** David Frota e Davi Rodrigues

## Descrição do Projeto
Esta API REST foi desenvolvida em Java com Spring Boot e PostgreSQL para gerenciar um sistema de Helpdesk (Suporte de TI). O projeto atende integralmente aos requisitos propostos, expondo dois recursos principais (**Usuários** e **Chamados**), cada um estruturado com pelo menos **7 campos de informação**[cite: 1]. 

A aplicação conta com um CRUD completo (operações de listar todos, mostrar por ID, criar, editar e apagar) para ambos os recursos, aplicando regras de integridade relacional, tratamento de *status codes* HTTP adequados e uma carga automática de dados iniciais[cite: 1].

---

## Tecnologias e Arquitetura
* **Linguagem:** Java 17[cite: 1]
* **Framework:** Spring Boot (com Spring Data JPA e Spring Web)
* **Banco de Dados:** PostgreSQL (Hospedado no Supabase)
* **Deploy/Hospedagem da API:** Railway
* **Controle de Versão:** GitHub[cite: 1]

---

## Estrutura dos Recursos

1. **Recurso `Usuario`:**
   * `id` (Identificador único gerado automaticamente)
   * `nome` (Nome completo do funcionário)
   * `email` (E-mail corporativo)
   * `telefone` (Número de contato)
   * `departamento` (Setor da empresa)
   * `cargo` (Função exercida)
   * `dataCadastro` (Data de registro no sistema)

2. **Recurso `Chamado`:**
   * `id` (Identificador único do ticket)
   * `titulo` (Resumo do problema)
   * `descricao` (Detalhamento do ocorrido)
   * `status` (Estado atual: ABERTO, EM_ANDAMENTO, RESOLVIDO)
   * `prioridade` (Nível de urgência: BAIXA, MEDIA, ALTA)
   * `dataAbertura` (Data e hora do registro)
   * `usuario` (Relacionamento Many-to-One: o funcionário responsável pelo chamado)

---

## Como Utilizar a API (Endpoints e Status Codes)

A aplicação retorna respostas em formato JSON acompanhadas dos códigos de status HTTP corretos para validação[cite: 1].

### Endpoints de Usuários (`/usuarios`)
* **`GET /usuarios`** — Lista todos os usuários cadastrados[cite: 1]. *(Retorna status `200 OK`)*[cite: 1]
* **`GET /usuarios/{id}`** — Mostra os detalhes de um usuário específico[cite: 1]. *(Retorna status `200 OK` ou `404 Not Found`)*[cite: 1]
* **`POST /usuarios`** — Cadastra um novo usuário no sistema[cite: 1]. *(Retorna status `201 Created`)*[cite: 1]
  * *Exemplo de Corpo (JSON):*
    ```json
    {
      "nome": "Ana Silva",
      "email": "ana@email.com",
      "telefone": "1111-1111",
      "departamento": "Vendas",
      "cargo": "Vendedora"
    }
    ```
* **`PUT /usuarios/{id}`** — Atualiza os dados de um usuário existente[cite: 1]. *(Retorna status `200 OK` ou `404 Not Found`)*[cite: 1]
* **`DELETE /usuarios/{id}`** — Remove um usuário e seus chamados associados[cite: 1]. *(Retorna status `204 No Content` ou `404 Not Found`)*[cite: 1]

### Endpoints de Chamados (`/chamados`)
* **`GET /chamados`** — Lista todos os chamados abertos no sistema[cite: 1]. *(Retorna status `200 OK`)*[cite: 1]
* **`GET /chamados/{id}`** — Mostra um chamado específico pelo ID[cite: 1]. *(Retorna status `200 OK` ou `404 Not Found`)*[cite: 1]
* **`GET /chamados/usuario/{usuarioId}`** — Rota customizada para listar todos os chamados abertos por um funcionário específico. *(Retorna status `200 OK`)*[cite: 1]
* **`POST /chamados`** — Cria um novo ticket de suporte vinculado a um usuário existente[cite: 1]. *(Retorna status `201 Created` ou `400 Bad Request` se o usuário não existir)*
  * *Exemplo de Corpo (JSON):*
    ```json
    {
      "titulo": "Internet lenta",
      "descricao": "A conexão cai frequentemente",
      "status": "ABERTO",
      "prioridade": "MEDIA",
      "usuario": {
        "id": 1
      }
    }
    ```
* **`PUT /chamados/{id}`** — Edita as informações de um chamado[cite: 1]. *(Retorna status `200 OK` ou `404 Not Found`)*[cite: 1]
* **`DELETE /chamados/{id}`** — Apaga um chamado do sistema[cite: 1]. *(Retorna status `204 No Content` ou `404 Not Found`)*[cite: 1]

---

## Carga Inicial de Dados
Para cumprir a exigência de popular a aplicação, o sistema possui uma classe de inicialização (`DataInitializer`) que insere automaticamente **3 registros de usuários** e **3 registros de chamados** assim que a aplicação é executada pela primeira vez, caso o banco esteja vazio.

---

## Como Rodar o Projeto Localmente

1. Clone o repositório em sua máquina[cite: 1]:
   ```bash
   git clone [https://github.com/davirodrigues222/helpdesk.git](https://github.com/davirodrigues222/helpdesk.git)

2. Abra o diretório do projeto em sua IDE
3. Configure as credenciais de conexão do seu banco PostgreSQL (Supabase) no arquivo src/main/resources/application.properties
4. Execute o projeto através da classe principal ApiApplication.java ou utilizando o Maven via terminal:
    ```bash
    ./gradlew bootRun

5. A API estará rodando na porta 8080 e pronta para ser testada utilizando ferramentas como o Postman ou o Bruno