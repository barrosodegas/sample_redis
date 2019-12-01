Para rodar o container com docker-compose da imagem do Redis para cache distribuído de aplicação.


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

Para acessar a API:

1 - URL e Dados de acesso
  * http://localhost:8180/customers

2 - Executar as chamadas do item abaixo no Postman ou qualquer ferramenta de sua preferência

3 - URLs para manipulação de produtos pela API no container:
  * Consulta:    GET / Lista todos os clientes
  * Consulta:    GET /<ID do cliente> Recupera um cliente pelo ID
  * Remoção:     DELETE /<ID do cliente> Remove um cliente pelo ID
  * Adição:      POST / Cria um novo cliente
  * Atualização: PUT / Atualiza um cliente
