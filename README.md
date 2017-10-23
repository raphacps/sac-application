**O que está contido no projeto?**
    
  Postgres - Banco de dados
  RabbitMQ - Broker de mensageria
  Netflix OSS com Spring Cloud
  Zuulproxy - client load balancer
  
**Como foi implementado**
  
    Foi implementado utilizando arquitetura de microserviços com CQRS 
    

**Como iniciar a aplicação**

Premissas:

    1 -  docker e docker-compose instalados

**Como iniciar a aplicação:**
  
    * execute docker-compose up -d
    * Abra o browser e digite localhost
 
**Para compilar**

    mvn clean install -N no root do projeto
    Após isto, será possível rodar mvn clean package a partir do root do projeto 
    ou dos projetos sac-command e sac-query
    
    Obs.: para rodar mvn clean install, subir antes a infraestrutura (docker-compose up -d) 
          para testes de integração.
          
    
**Acesso às aplicações**

    Eureka: localhost:8761
    RabbitMQ: localhost:15672 (user: guest, password: guest)
    Postgres: localhost:5432 (user: postgres, password: postgres)
    Zuulproxy: localhost:8080/routes