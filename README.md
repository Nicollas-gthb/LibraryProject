
# 📚 Gerenciador de Troca de Livros

Projeto desenvolvido em Java puro, utilizando PostgreSQL e arquitetura MVC, com o objetivo de gerenciar livros, clientes e empréstimos por meio de um sistema de console interativo.

O projeto foi criado com foco em boas práticas, organização de código, separação de responsabilidades e clareza arquitetural, sendo ideal como projeto de estudo e portfólio para back-end.


### 🧠 Visão Geral

O sistema permite:

- 📖 Cadastro, listagem, atualização e remoção de livros

- 👤 Cadastro, listagem, atualização e remoção de clientes

- 🔄 Controle de empréstimos de livros, incluindo devolução

- ⚠️ Tratamento de erros com exceptions personalizadas

- 🗄️ Persistência de dados com PostgreSQL (JDBC)

Toda a interação é feita via terminal, por meio de menus intuitivos.

### 🛠️ Tecnologias Utilizadas

- ☕ Java (Java puro / console)

- 🐘 PostgreSQL

- 🔌 JDBC

- 🧱 Arquitetura MVC

- 🗃️ Padrão DAO

- 🚨 Exceptions personalizadas

- 🧰 IntelliJ IDEA


### 🗂️ Estrutura do Projeto
```
com.system
├── controller
│   ├── LivroController
│   ├── ClienteController
│   └── EmprestimoController
│
├── dao
│   ├── LivroDAO
│   ├── ClienteDAO
│   └── EmprestimoDAO
│
├── exceptions
│   ├── LivroNaoEncontradoException
│   ├── LivroIndisponivelException
│   ├── ClienteNaoEncontradoException
│   └── EmprestimoNaoEncontradoException
│
├── model
│   ├── Livro
│   ├── Cliente
│   └── Emprestimo
│
├── service
│   ├── LivroService
│   ├── ClienteService
│   └── EmprestimoService
│
├── view
│   ├── MenuView
│   ├── LivroView
│   ├── ClienteView
│   └── EmprestimoView
│
└── Main
```

### 🧩 Arquitetura (MVC)

O projeto segue o padrão Model–View–Controller, garantindo organização e fácil manutenção:

#### 📦 Model

- Representa as entidades do sistema (Livro, Cliente, Emprestimo)

- Contém apenas atributos e comportamentos básicos

#### 🖥️ View

- Responsável pela interação com o usuário (console)

- Exibe menus, lê entradas e mostra mensagens

- Não contém regras de negócio

#### 🎮 Controller

- Faz a ponte entre a View e o Service

- Encaminha as requisições do usuário

#### ⚙️ Service

- Contém as regras de negócio

- Realiza validações

- Lança exceptions personalizadas quando necessário

#### 🗄️ DAO

- Responsável pelo acesso ao banco de dados

- Executa operações SQL via JDBC

### 📋 Funcionalidades
📖 Livros

- Cadastrar livro

- Atualizar livro

- Listar livros

- Buscar livro por ID

- Remover livro

👤 Clientes

- Cadastrar cliente

- Atualizar cliente

- Listar clientes

- Buscar cliente por ID

- Remover cliente

🔄 Empréstimos

- Realizar empréstimo

- Listar empréstimos

- Buscar empréstimo por ID

- Devolver livro

- (Remoção disponível apenas para testes)

### 🚨 Tratamento de Erros

O projeto utiliza exceptions personalizadas para deixar os erros mais claros e controlados, como:

- ```LivroNaoEncontradoException```

- ```LivroIndisponivelException```

- ```ClienteNaoEncontradoException```

- ```EmprestimoNaoEncontradoException```

Essas exceptions:

- Interrompem apenas o fluxo da operação

- Não encerram o programa

- São tratadas na View, exibindo mensagens amigáveis ao usuário

### 🗄️ Banco de Dados

O projeto utiliza PostgreSQL como banco de dados relacional.

#### 📄 schema.sql

O arquivo ```schema.sql``` contém:

- Criação das tabelas

- Relacionamentos

- Restrições

- Chaves primárias e estrangeiras

O script pode ser executado diretamente pelo pgAdmin ou via linha de comando.

### ▶️ Como Executar o Projeto

#### 1. Clone o repositório:

```
git clone https://github.com/seu-usuario/seu-repositorio.git
```

#### 2. Crie o banco de dados no PostgreSQL

#### 3. Execute o schema.sql

#### 4. Configure a conexão JDBC no projeto:

- URL

- Usuário

- Senha

#### 5. Execute a classe Main

### 🔮 Possíveis Melhorias Futuras

- 🔐 Autenticação de usuários

- 📅 Controle de prazo e atraso de empréstimos

- 📊 Relatórios (livros mais emprestados, clientes ativos)

- 🖥️ Interface gráfica (JavaFX ou Swing)

- 🌐 API REST com Spring Boot

### 🧪 Objetivo do Projeto

- Este projeto foi desenvolvido com foco em:

- Consolidar conceitos de Java orientado a objetos

- Entender na prática MVC + DAO

- Trabalhar com PostgreSQL + JDBC

- Criar um CRUD completo e bem estruturado

- Servir como projeto de portfólio

#

![Static Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=coffeescript&logoColor=rgb(255%2C%20255%2C%20255))
![Static Badge](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=rgb(255%2C%20255%2C%20255))
![Static Badge](https://img.shields.io/badge/Intellij-FC3158?style=for-the-badge&logo=intellijidea&logoColor=rgb(255%2C%20255%2C%20255))
