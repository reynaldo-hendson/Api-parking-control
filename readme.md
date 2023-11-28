<h1>Projeto: API Controle de vaga de estacionamento para apartamentos.</h1>
<hr>
<h3>Descrição do desafio</h3>
Projeto se trata de controle de estacionamento para um condomínio, abordando alguns conceitos de padrões de projeto como Singleton, Strategy, Facade e DTO em Java.
<hr>
<h3>Tecnologias Utilizadas</h3>

Para iniciar este projeto, foi utilizado o <a href="https://start.spring.io/">spring initializr</a> utilizando as opções:
<ul>
    <li>IDE IntelliJ</li>
    <li>Projeto: Maven </li>
    <li>Linguagem: Java 17</li>
    <li>Spring Boot: 3.0.5</li>
    <li>Dependências: Spring Web, Spring Data JPA, Lombok, H2 Database e SpringDoc</li>
   
Dependência adicionada manualmente no pom.xml: <a href="https://springdoc.org/v2/"> springdoc-openapi v2.1.0.</a>
</ul>
<hr>
<h3>Padrões e conceitos apresentados no projeto:</h3>
<ul>
<li>Singleton: Padrão Criacional que permite a criação de uma única instância de uma classe e fornecer um modo para recuperá-la.</li>
<li>Strategy: Padrão Comportamental com objetivo de simplificar a variação de algoritmos para a resolução de um mesmo problema.</li>
<li>Facade: Padrão Estrutural que visa prover uma interface que reduza a complexidade nas integrações com subsistemas.</li>
<li>DTO: Data Transfer Object (DTO) ou simplesmente Transfer Object é um padrão de projetos bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias ou processos de um sistema distribuído.</li>

A relação entre Padrões de Projetos com Java e o spring framework pode-se ser percebida em:

<li>Annotations @Autowired como padrão criacional Singleton.</li>
<li>Annotations @Service e @Repository como padrão comportamental Strategy.</li>
<li>Annotation @RestController como padrão estrutural Facade.</li>
</ul>

<hr>
<h3>Validações</h3>
<ul>
<li>Verifica se a vaga já está em uso na hora do cadastro.</li>
<li>Verifica se apartamento e bloco já existem no banco de dados.</li>
<li>Verifica se o veiculo já não se encontra em outra vaga cadastrada.</li>
</ul>

<hr>

### Utilize para acessar a interface com os endpoints no Swagger:

<a href="http://api-parking-control-production.up.railway.app/swagger-ui.html">Api-parking-control</a>

#### Utilize o git para clonar o repositório:
```
git clone https://github.com/reynaldo86/Api-parking-control.git	
```

<hr>
<h2>Links Úteis</h2>
<ul>
    <li><a href="https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.6.1&packaging=jar&jvmVersion=11&groupId=me.dio.academia&artifactId=academia-digital&name=academia-digital&description=Tutorial%20API%20RESTful%20modelando%20sistema%20de%20academia%20de%20gin%C3%A1stica&packageName=me.dio.academia.digital&dependencies=web,data-jpa,postgresql,validation,lombok">Spring Initializr</a></li>
    <li><a href="https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/common-application-properties.html">Common application properties</a></li>
    <li><a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories">Spring Data JPA - Reference Documentation</a></li>
    <li><a href="https://github.com/iluwatar/java-design-patterns">Java-design-patterns</a></li>
<li><a
       href="https://springdoc.org/">SpringDoc</a></li>

</ul>

