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
    Após isto, será possível rodar mvn clean install a partir do root do projeto ou dos projetos sac-command e sac-query