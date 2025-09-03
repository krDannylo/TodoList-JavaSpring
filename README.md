# Todo List Application (WIP)

## Descrição

Este é um projeto de aplicação de lista de tarefas (todo list) desenvolvido em Java com Spring Boot. A aplicação permite criar, ler, atualizar e deletar (CRUD) usuários e suas respectivas tarefas.

## Estrutura do Código

O código está organizado em pacotes de acordo com a funcionalidade:

* `todoList.user`: pacote que contém as classes relacionadas ao usuário, incluindo a entidade `UserModel`.
* `todoList`: pacote que contém as classes principais da aplicação, incluindo a classe `TodoListApplication`.

## Dependências e Bibliotecas

A aplicação utiliza as seguintes dependências e bibliotecas:

* Spring Boot
* Maven
* Hibernate
* Lombok

## Execução do Projeto

Para executar o projeto, é necessário ter o Maven instalado e configurado. Em seguida, execute o comando `mvn spring-boot:run` na raiz do projeto.

## Endpoints

A aplicação expõe os seguintes endpoints:

* `POST /users`: cria um novo usuário

## Modelo de Dados

O modelo de dados é composto por uma única entidade `UserModel`, que tem as seguintes propriedades:

* `id`: identificador único do usuário (gerado automaticamente)
* `name`: nome do usuário
* `username`: username do usuário
* `password`: senha do usuário