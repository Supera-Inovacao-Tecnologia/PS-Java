# Avaliação Java


## Descrição

  O teste consiste em construir a camada de serviço de um pseudo ecommerce de games mobile utilizando Java

## Como executar os testes
  
  O projeto usa o maven warper (mvnw).
  Para executar os testes de exemplo basta o comando abaixo:
  ```sh
  ./mvnw clean test
  ```

## Requisitos

  - Existe um exemplo de carga de banco de dados em memória em [ProductDAoExampleTest.java](./src/test/java/br/com/supera/game/store/ProductDAoExampleTest.java)
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

  - O prazo para entrega do projeto é até 23:59 do dia 15/02/2021.
  - A API pode ser HTTP, Restful, WSDL/SOAP, HATEOAS ou gRCP mas deverá ser documentado no [README.md](.) como executar/compilar/empacotar o projeto e quais os endpoints solicitados nos requisitos acima. 
  - A versão do Java pode ser atualizada para 11 ou 14.
  - Não existe restrição de framework (EE, Spring, Quarkus etc) mas será melhor avaliado se justificado no [README.md](.) os motivos da decisão.


### Assim que concluir o teste, nos encaminhe a url do repositório onde o teste foi escrito e o pacote { *.jar, *.war, *.ear ...} do projeto através [deste formulário](https://forms.gle/4aAwcaG8Qw6KZQgs9)
