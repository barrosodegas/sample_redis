# CRUD API Kotlin e Redis

- CRUD Api REST feita em Kotlin com a utilização do Redis para a exemplificação de cache distrbuído.
- O Redis, que é uma solução open source para armazenamento de estrutura de dados em memória, o qual pode ser utilizada como banco de dados, cache ou message broker.
- Uma vez que seu Redis estiver no ar, você pode replicar as configurações feitas nesta API par as demais APIs de seu projeto e poder  compartilhar qualquer informação entre eles.
- Neste projeto, utilizo como exemplo dados de cliente que, geralmente, não costumam ser atualizados com muita frequência e que sempre são utilziados em várias partes de um sistema.
- Também foi adicionada a possibilidade de consulta com GraphQL

## Preparando o ambiente e rodando a API

Ambiente: 

* Ubuntu 18.04 LTS ou Mac
* Instalar o openjdk8, docker-ce e o docker-compose
* Seu host deve conseguir se comunicar com o host do redis. Para fins de testes, basta que estejam na mesma rede.


Executando:

1 - Executar o arquivo docker-compose deste diretório para baixar a imagem e preparar a imagem a rede interna:
  * docker-compose build

2 - Executar o arquivo docker-compose deste diretório para subir os containers:
  * docker-compose up -d

3 - Observar que neste caso, a porta de acesso a API é: 8180, e a porta de acesso ao Redis é: 6379

Compilando o projeto

- Para gerar o executável da API: Pelo CMD, dentro do diretório do projeto: mvn clean package -DskipTests -U -T 4
- O executável .jar será criado em target/

Acessando a API

1 - URL REST base
  * http://localhost:8180/customers

2 - Executar as chamadas do item abaixo no Postman ou qualquer ferramenta de sua preferência

3 - URLs para manipulação de produtos pela API no container:
  * Consulta:    GET / Lista todos os clientes
  * Consulta:    GET /<ID do cliente> Recupera um cliente pelo ID
  * Remoção:     DELETE /<ID do cliente> Remove um cliente pelo ID
  * Adição:      POST / Cria um novo cliente
  * Atualização: PUT / Atualiza um cliente
  
4 - URL GraphQL Gui
  * http://localhost:8180/gui
