# Avaliação Java - Camada de serviço para pseudo loja de games

## Descrição

  Esse projeto é sobre a camada de serviço para uma pseudo loja de games.
  Foi escrito utilizando a linguagem Java e a especificação JAX-RS do Java EE.
  Foi utilizada a implementação de referência, o Jersey. E testado no Tomcat v9.

## Por que REST

  A preferência pela arquitetura, ou filosofia, REST se dá em razão da existência de modernos frameworks especializados na construção 
  de aplicações frontend. Tal escolha ainda enforça o princípio de "separation of concerns". A criação de backends especializados naturalmente 
  leva-nos a refletir mais sobre requerimentos de segurança, validação de dados, escalabilidade, etc. Além disso, arquiteturas REST tornam um pouco mas flexível o reaproveitamento de código, endpoints. Enfim, o sistema como um todo se beneficia da separação de pricípios.

## Como executar os testes
  
  O projeto utiliza o Maven Wrapper, o que quer dizer que não será necessário a instalação do Maven na máquina, uma vez que a ferramenta é instalado em um diretório local ao projeto. Utilize o comando `mvnw clean test` para a execução da fase de testes. 
  
## Como executar os testes de produção

	Para a execução de testes nos endpoints, poderá ser utilizada a ferramenta Postman, Curl ou o próprio navegador.
	Após rodar o comando `mvnw clean package`, copie o arquivo `game-store.war` da pasta "target" que será gerada para a pasta "webapps" do servidor Tomcat(foram realizados testes apenas na versão 9). Inicie o servidor e pronto, os endpoints já estarão disponíveis para requests.

## Endpoints 
Por meio dos endpoints descritos abaixo, é possível interagir com as resouces. Não foram implementadas camadas de seguraça ou validação de dados devido ao tempo para entrega do projeto. 
	  
### `http://localhost:8080/game-store/products`

	| Métodos       | Descrição     |
	|:-------------:|:-------------| 
	| GET           | retorna uma lista de produtos que podem ser inseridos no carrinho do usuário | 
Para ordenação dos itens, pode ser utilizado o parâmetro `orderBy` com os possíveis valores: [name | price | score | id] 
	
### `http://localhost:8080/game-store/users/1`  
	
Dentro de /users, será possível interagir com as resources de um pseudo usuário de id 1. Não é possível criar mais usuários.  
	
	| Métodos       | Descrição     |
	|:-------------:|:-------------| 
	| GET           | retorna o usuário | 
 
### `http://localhost:8080/game-store/users/1/cart` 
 
	| Métodos       | Descrição     |
	|:-------------:|:-------------| 
	| GET           | retorna o carrinho atualizado | 
	| POST           | aceita objeto JSON no body do request e adiciona ao carrinho do usuário| 
	| DELETE           | aceita objeto JSON no body do request e remove ao carrinho do usuário| 

### `http://localhost:8080/game-store/users/1/cart/checkout` 

	| Métodos       | Descrição     |
	|:-------------:|:-------------| 
	| GET           | retorna o checkout atualizado com os produtos adicionados ao carrinho com seus preços para finalizar a compra | 
	| POST           | aceita objeto JSON no body do request e adiciona ao carrinho do usuário| 
	| DELETE           | aceita objeto JSON no body do request e remove ao carrinho do usuário| 
	
Para ordenação dos itens, pode ser utilizado o parâmetro `orderBy` com os possíveis valores: [name, price, score, id] 

## Requisitos
Java 8 instalado na máquina 
	
## Sobre a entrega
Como todo cógido, o projeto pode ser melhorado para atender requisitos adicionais como mais enpoints, segurança, escalabilidade. Algumas partes do código não estão bem comentadas, novamente, devido ao prazo de entrega.