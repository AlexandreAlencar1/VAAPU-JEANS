# Backend do Projeto

Este é o backend do projeto, responsável por fornecer serviços e interações com o banco de dados. Siga as instruções abaixo para configurar e executar o backend.

## Pré-requisitos

- [Docker](https://www.docker.com/) instalado
- Ambiente de desenvolvimento com suporte a Java (se aplicável)

## Configuração do Banco de Dados

1. Certifique-se de que o Docker esteja instalado e em execução em seu sistema.
2. Navegue até a raiz do projeto onde está localizado o arquivo `docker-compose.yaml`.
3. Execute o seguinte comando para iniciar o MySQL:

    ```bash
    docker-compose up
    ```

    Isso iniciará o contêiner do MySQL usando as configurações fornecidas no arquivo `docker-compose.yaml`.

4. Abra um terminal no contêiner do MySQL (pode ser usado uma ferramenta como MySQL Workbench ou via linha de comando). Crie as tabelas necessárias com os campos desejados.

## Executando o Backend

1. Navegue até a raiz do projeto.
2. Abra o projeto em seu ambiente de desenvolvimento, se necessário.
3. Vá para a classe principal (geralmente chamada `Main` ou algo semelhante) e execute a aplicação. Isso iniciará o servidor backend.

Se tudo foi configurado corretamente, o backend agora está em execução e pronto para receber solicitações.

## CRUD e Operações no Banco de Dados

1. Certifique-se de que o banco de dados está em execução (passos acima).
2. Use um cliente MySQL ou ferramenta de linha de comando para criar tabelas e inserir dados conforme necessário.

Lembre-se de que as operações CRUD devem ser implementadas no código do backend. Consulte a documentação do seu framework ou biblioteca para obter informações específicas sobre como realizar operações de banco de dados.

ATENÇÃO: qualquer duvída entrar em contato com o gerente!
