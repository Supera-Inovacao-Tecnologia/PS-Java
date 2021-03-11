# Avaliação Java - Camada de serviço para pseudo loja de games

## Descrição

  Esse projeto é sobre a camada de serviço para uma pseudo loja de games.
  Foi escrito utilizando em Java uitlizando a especificação JAX-RS do Java EE.
  Foi utilizada a implementação de referência, o Jersey. E testado no Tomcat v9.


## Por que REST

  A preferência pela arquitetura, ou filosofia, REST se dá em razão da existência de modernos frameworks especializados na construção 
  de aplicações frontend. Tal escolha ainda enforça o princípio de "separation of concerns". A criação de backends especializados naturalmente 
  leva-nos a refletir mais sobre requerimentos de segurança, validação de dados, escalabilidade, etc. Além, arquiteturas REST torna um pouco mas flexível o reaproveitamento de código, endpoints. Enfim, o sistema como um todo se beneficia da separação de pricípios.

## Como executar os testes
  
  O projeto utiliza o Maven Wrapper, o que quer dizer que não será necessário a instalão do Maven na máquina, uma vez que a ferramenta é instalado em um diretório local ao projeto. 
  Utilize o comando `mvnw clean test` para a execução da fase de testes. 
  
## Como executar os testes de produção

### Endpoints  
  
  
  Por meio dos endpoints descritos abaixo, é possível interagir com as resouces de um pseudo usuário 
  
  
  
  O projeto usa o maven warper (mvnw).
  Para executar os testes de exemplo basta o comando abaixo:
  ```sh
  ./mvnw clean test
  ```

## Requisitos

  - Existe um exemplo de carga de banco de dados em memória em [ProductDaoExampleTest.java](./src/test/java/br/com/supera/game/store/ProductDaoExampleTest.java)
  - Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente
  - O usuário poderá adicionar e remover produtos do carrinho
  - O usuário poderá ordenar os produtos por preço, popularidade (score) e ordem alfabética.
  - A cada produto adicionado, deve-se somar R$ 10,00 ao frete.
  - O frete é grátis para compras acima de R$ 250,00 (sem o frete dos demais produtos).

## O que iremos avaliar

Levaremos em conta os seguintes critérios:

  - Cumprimento dos requisitos
  - Qualidade do projeto de API e fluidez da DX
  - Organização do código e boas práticas
  - Domínio das linguagens, bibliotecas e ferramentas utilizadas
  - Organização dos commits
  - Escrita e cobertura de testes

## Sobre a entrega

  - O prazo para entrega do projeto é até 11:59 do dia 09/03/2021.
  - A API pode ser HTTP, Restful, WSDL/SOAP, HATEOAS ou gRCP mas deverá ser documentado no [README.md](.) como executar/compilar/empacotar o projeto e quais os endpoints solicitados nos requisitos acima. 
  - A versão do Java pode ser atualizada para 11 ou 14.
  - Não existe restrição de framework (EE, Spring, Quarkus etc) mas será melhor avaliado se justificado no [README.md](.) os motivos da decisão.


### Assim que concluir o teste, nos encaminhe a url do repositório onde o teste foi escrito e o pacote { *.jar, *.war, *.ear ...} do projeto através [deste formulário](https://forms.gle/YHF1UtxsBmBGWnie7)
