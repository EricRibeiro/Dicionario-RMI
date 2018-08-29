# Dicionario-RMI

Uma aplicação simples com o objetivo de explorar os conceitos principais do Java RMI.

## Explicação

 O programa foi dividido em dois pacotes principais, cada um com suas respectivas responsabilidades.

#### Cliente

- ##### Dialog

 Contém os componentes gráficos da aplicação. 

- ##### DicionarioClient

 Realiza a conexão com o servidor RMI, invoca os seus métodos e utiliza os componentes gráficos para capturar as inserções do usuário.

#### Servidor

- ##### Arquivo

 Responsável pela criação da coleção originada do arquivo JSON e da escrita da estrutura alterada no arquivo.

- ##### Palavra

 Modelo abstrato da entidade do problema.

- ##### Dicionario

 Interface remota do método provido pelo RMI.

- ##### DicionarioServant
 
 Implementação da interface responsável pela adição, consulta e remoção de palavras.

- ##### DicionarioServer

 Servidor que realiza o bind do serviço do Dicionário e o disponibiliza para os clientes.
 
 ## Instruções
 
 A aplicação é dependente da biblioteca [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) para escrever o arquivo JSON a partir da coleção de *Palavras* e para fazer o processo inverso. 
 
 O Maven pode ser utilizado para fazer o download das dependências a partir do *pom.xml* presente no diretório raiz ou elas podem ser importadas manualmente.
 
 **É necessário executar o *rmiregistry* no diretório do código compilado para executar a aplicação.**
 
