# Gerênciamento de Cadastros

API REST desenvolvida com o objetivo de praticar e aprimorar habilidades em desenvolvimento backend com Java e Spring Boot.
O sistema é focado em **cadastros diversos**, inicialmente de usuários, mas com a estrutura preparada para suportar múltiplas entidades e relacionamentos entre elas.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL** (banco de dados relacional)
- **Flyway** (para versionamento e controle de migrações de banco de dados)
- **MapStruct** (para mapeamento entre entidades e DTOs)
- **Jakarta Validation** (validações nos DTOs)
- **Springdoc OpenAPI 3 / Swagger** (para documentação da API)
- **Lombok**
- **Postman** (para testes da API)

---

## 🧠 Funcionalidades e Padrões Aplicados

- ✅ Organização por camadas: **Controller**, **Service**, **Repository** e **DTOs**
- ✅ Utilização de **records** Java para os DTOs, com validações via anotações
- ✅ Tratamento global de exceções com `@ControllerAdvice`, cobrindo erros como:
  - `400 Bad Request`
  - `404 Not Found`
  - `409 Conflict`
  - `500 Internal Server Error`
- ✅ Customização dos retornos de erro com diferentes DTOs por status HTTP
- ✅ Documentação clara com exemplos via Swagger/OpenAPI
- ✅ Mapeamento de objetos utilizando **MapStruct**, separando claramente regras de conversão entre DTOs e entidades
- ✅ Migrations com **Flyway** para versionamento do schema do banco
- ✅ Testes com Postman durante o desenvolvimento

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos

- Java 17+
- MySQL instalado e em execução
- Maven instalado

### Configuração do Banco

1. Crie um banco de dados no MySQL:
```sql
CREATE DATABASE cadastro;
 ```
Atualize o arquivo application.properties com suas credenciais:

```
spring.datasource.url=jdbc:mysql://localhost:3306/cadastro
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```
O Flyway será responsável por rodar automaticamente as migrations assim que o projeto for iniciado.


Rodando a aplicação

```
mvn spring-boot:run
```
A aplicação estará disponível em:
http://localhost:8080/usuarios

📘 Documentação Swagger
Acesse a documentação gerada automaticamente em:
http://localhost:8080/swagger-ui.html

### 📌 Próximos Passos
 Criar uma interface web responsiva para consumo da API

 Adicionar novas entidades com relacionamentos (OneToMany, ManyToOne, etc)

 Implementar autenticação e autorização (JWT ou Spring Security)

 Criar testes automatizados (JUnit + Mockito)


### 🤝 Contribuição
Este projeto está em fase de desenvolvimento contínuo, com o objetivo de aprendizado e evolução pessoal. Sugestões e contribuições são sempre bem-vindas!

## 🧑‍💻 Desenvolvido por

- Vitor de Souza Ramos
- Estudante de Ciência da Computação na UNESC
- [GitHub](https://github.com/vitor-sramos).
- [LinkedIn](https://www.linkedin.com/in/vitor-ramos-934302349).
