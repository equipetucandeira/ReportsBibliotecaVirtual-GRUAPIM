# 📊 Report Service - Biblioteca Virtual

Este microsserviço é responsável por gerar **relatórios estatísticos** com base nos empréstimos (reservas) realizados na aplicação **Biblioteca Virtual**.

---

## 📦 Visão Geral

O `report-service` foi extraído do monólito original como parte da evolução arquitetural da aplicação, seguindo os princípios de **Domain-Driven Design** (DDD) e **arquitetura orientada a microsserviços**.

Este serviço consome eventos via **RabbitMQ** enviados pelo serviço principal (`reservation-service`) e armazena localmente os dados necessários para geração de relatórios.

---

## 🧱 Arquitetura

- Tipo: Microsserviço (Spring Boot)
- Comunicação: Mensageria (RabbitMQ)
- Banco de Dados: MySQL
- Build: Maven
- Porta padrão: `8081`

---

## 🗂️ Funcionalidades

### 🎯 Geração de Relatórios

- **Livros mais emprestados**
- **Total de empréstimos em um período**
- **Total de usuários distintos em um período**
- **Livro mais emprestado em um intervalo**

---

## 📩 Comunicação via RabbitMQ

Este serviço **escuta mensagens** da fila `reservation.queue`. As mensagens representam eventos de novas reservas criadas e são persistidas localmente para posterior análise.

- **Exchange**: `reservation.exchange`
- **Routing Key**: `reservation.create`
- **Queue**: `reservation.queue`

### Exemplo de payload recebido:
```json
{

  "reservationId": 42,
  "bookId": 7,
  "bookTitle": "Dom Quixote",
  "userId": 123,
  "reservationStartDate": "2025-07-01",
  "reservationEndDate": "2025-07-10"

}
```

## 📊 Endpoints REST
Obs: o relatório é gerado com base nos dados recebidos pela fila.

| Método | Caminho                                                            | Descrição                                         | Retorno                 |
| ------ | ------------------------------------------------------------------ | ------------------------------------------------- | ----------------------- |
| GET    | `/api/admin/report/mostBorrowed`                                   | Retorna os livros mais emprestados                | `List<MostBorrowedDTO>` |
| GET    | `/api/admin/report/libUse?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` | Retorna relatório de uso da biblioteca no período | `LibUseDTO`             |

## 📄 DTOs
### MostBorrowedDTO
```java
String title;
Long total;
```

### LibUseDTO
```java
Long totalLoans;
Long totalUsers;
MostBorrowedDTO mostLoans;
```

## ⚙️ Configuração (application.properties)
```
server.port=8081
spring.application.name=reports

# RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin
queue.name=emprestimos.queue

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/reports
spring.datasource.username=user
spring.datasource.password=user
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Reserva API (caso precise consumir via REST no futuro)
reservation.api.url=http://localhost:8080
```

## 🐳 Docker (RabbitMQ + MySQL)
```yaml
services:
  rabbitmq:
    image: rabbitmq:management
    container_name: rabbitmq
    ports:
      - 5672:5672
      - 15672:15672
    environment:
      RABBITMQ_DEFAULT_USER: admin
      RABBITMQ_DEFAULT_PASS: admin
    volumes:
      - rabbitmq-data:/var/lib/rabbitmq/

  mysql:
    image: mysql:8.0
    container_name: mysql
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: reports
      MYSQL_USER: user
      MYSQL_PASSWORD: user
    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  rabbitmq-data:
  mysql-data:

```

## 🛠️ Build & Run

```
# Buildar o projeto
mvn clean install

# Executar o serviço
mvn spring-boot:run
```

## 🧠 Equipe & Créditos
Projeto desenvolvido por Equipe Tucandeira, como parte da disciplina de Arquitetura de Software.

## 📚 Referências
- Spring Boot

- RabbitMQ

- Docker

- Domain-Driven Design (DDD)

- Microservices Architecture

