# `Projeto Agrix - Java com Spring Boot`

## Visão Geral
Este projeto, foca na simulação de um produto solicitado por uma empresa fictícia chamada "AgroTech".
A "AgroTech" é uma empresa especializada em tecnologias para melhorar a eficiência no cultivo de plantações. Isso visa
reduzir o desperdício de recursos em geral e de alimentos em específico, fazendo um uso mais responsável da terra
disponível para o plantio.
O primeiro produto dessa empresa será o "Agrix", um sistema que permitirá a gestão e o monitoramento das fazendas
participantes, suas respectivas plantações e o controle dos fertilizantes utilizados.
O projeto Agrix representa uma aplicação Java baseada no ecossistema Spring, que aborda aspectos fundamentais para o
desenvolvimento de software, incluindo as características das APIs RESTful, gerenciamento de dependências com inversão de
controle, persistência de dados, manipulação de erros, Dockerização, segurança e seguindo o padrão de design MVC
Architecture.

## Tecnologias utilizadas
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Devtools e actuator
- JWT(Jason Web Token)
- JUnit
- H2 Database
- Gerenciamento de dependência com Maven
- Formatação de código com GoogleStyle
- MySQL
- Workbench MySQL
- Postman(clientes REST)
- Docker
- Linux
- Git
- GitHub

## Especificações da aplicação
<details>
<summary>🗄️ Descrição do banco de dados e entidades</summary><br>

Especificações do banco de dados:

![Modelo de tabelas](images/agrix-tabelas-fase-b.png)

Nesses modelos, temos as seguintes tabelas:
- `farm`: representa uma fazenda
- `crop`: representa uma plantação, e está em relacionamento `n:1` ("muitos para um") com a tabela `farm`
    - Esta tabela recebeu alguns campos a mais, que guardam datas, e que precisarão ser considerados durante o desenvolvimento da Fase B.
- `fertilizer`: esta nova tabela representa um fertilizante, e está em um relacionamento `n:n` ("muitos para muitos") com a tabela `crop`. Esse relacionamento é realizado através da tabela `crop_fertilizer`.
</details>

<details>
<summary>🗄️ Execução local do projeto</summary><br>

Para executar, clone esse repositório com o comando:

    git clone git@github.com:Leandro-Bertholini/agrix_java-spring.git


Entre na pasta do projeto e instale as dependências:

    mvn install

**OBS:** Certifique-se de ter o Docker instalado na sua máquina.

Suba a aplicação com o comando:

    docker-compose up

Banco de dados apontado para a porta:

    3306:3306

**OBS:** Certifique-se de ter alguma ferramenta para solicitação HTTP instalado na sua máquina.

Acesse a aplicação na rota:

    http://localhost:8080/


</details>



## Requisitos para a construção do projeto

### 1. Criado uma API para controle de fazendas com a rota POST /farms

<details>
  <summary>Criado uma API para gerenciamento de fazendas que inclua a rota POST</summary><br />

Neste requisito, foi criado a base para gerenciamento de fazendas da sua API, utilizando
Spring, Spring Boot, Spring Web e Spring Data.

Foi criado também a primeira rota:

- `/farms` (`POST`)
    - deve receber via corpo do POST os dados de uma fazenda (veja abaixo para os
      dados de requisição e resposta)
    - deve salvar uma nova fazenda a partir dos dados recebidos
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da fazenda criada. O `id` da fazenda deve estar incluso na resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:
```json
{
  "name": "Fazendinha",
  "size": 5
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```
</details>

</details>


### 2. Criado a rota GET /farms

<details>
  <summary>Criado a rota GET /farms da API, para retornar todas as fazendas cadastradas </summary><br />

Criado a rota:

- `/farms` (`GET`)
    - deve retornar uma lista de todas as fazendas. O `id` da fazenda deve estar
      incluso na resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "name": "Fazendinha",
    "size": 5.0
  },
  {
    "id": 2,
    "name": "Fazenda do Júlio",
    "size": 2.5
  }
]
```

</details>

</details>

### 3. Criado a rota GET /farms/{id}

<details>
  <summary>Criado a rota GET /farms da API, para retornar as informações de uma fazenda</summary><br />

Criado a rota:

- `/farms/{id}` (`GET`):
    - deve receber um `id` pelo caminho da rota e retornar a fazenda com esse `id`. O `id` da
      fazenda deve estar incluso na resposta.
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

```json
{
  "id": 3,
  "name": "My Cabbages!",
  "size": 3.49
}
```

</details>

</details>

### 4. Criado a rota POST /farms/{farmId}/crops

<details>
  <summary>Criando a rota POST /farms/{farmId}/crops para criação de plantações</summary><br />

Neste requisito, foi criado a rota criação de plantações. As plantações estão em um relacionamento `n:1` com as fazendas.

A rota criada é:
- `/farms/{farmId}/crops` (`POST`)
    - deve receber o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - deve receber via corpo do POST os dados da plantação (veja abaixo para os dados de requisição
      e resposta)
    - deve salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da plantação criada. A resposta deve incluir o `id` da plantação e
          o `id` da fazenda, mas não deve incluir os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.
    -
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

</details>

### 5. Criado a rota GET /farms/{farmId}/crops

<details>
  <summary>Criada a rota GET /farms/{farmId}/crops para listar as plantações de uma fazenda</summary><br />

Neste requisito, foi criado a rota para listar as plantações de uma fazenda. A rota criada é:
- `/farms/{farmId}/crops` (`GET`):
    - deve receber o `id` de uma fazenda pelo caminho
    - deve retornar uma lista com todas as plantações associadas à fazenda
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  }
]
```

</details>

</details>

### 6. Criado a rota GET /crops

<details>
  <summary>Criado a rota GET /crops para listar todas as plantações cadastradas</summary><br />

A rota criada é:
- `/crops` (`GET`)
    - deve retornar uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "farmId": 2
  }
]
```

</details>

</details>

### 7. Criado a rota GET /crops/{id}

<details>
  <summary>Criado a rota GET /crops/{id} para retornar as informações de uma fazenda</summary><br />

A rota criada é:
- `/crops/{id}` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho da rota
    - caso exista a plantação com o `id` recebido, deve retornar os dados da plantação. A resposta
      deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os
      dados da fazenda.
    - caso não exista uma plantação com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "farmId": 2
}
```

</details>

</details>

### 8. Criado um Dockerfile para sua aplicação

<details>
  <summary>Criado um Dockerfile multi-estágio configurando a imagem Docker da sua aplicação</summary><br />

Finalmente, você deve construir um `Dockerfile` para rodar a sua aplicação no Docker.

Sobre o `Dockerfile`:

- Deve ser multi-estágio
- O primeiro estágio deve se chamar `build-image` e deve ser utilizado para a construção do pacote da sua aplicação, contendo:
    - Um diretório de trabalho (workdir) chamado `/to-build-app`
    - A cópia dos arquivos necessários
    - A instalação das dependências utilizando Maven
        - Aqui, se quiser você pode utilizar o goal `dependency:go-offline` do Maven, que vai baixar todas as dependências e pode ajudar o Docker a criar um cache que agilize o processo de re-criação da imagem.
    - A construção do pacote JAR utilizando Maven com o goal `package`
- O segundo estágio deve ser utilizado para a construção da imagem final, contendo:
    - Um diretório de trabalho (workdir) chamado `/app`
    - A cópia dos arquivos necessários a partir da imagem do primeiro estágio
    - A exposição da porta `8080`
    - Um ponto de entrada (entrypoint) executando o pacote da aplicação


</details>

### 9. Foi criado testes com cobertura mínima de 80% das linhas da classe PersonService


Foi criado testes com cobertura mínima de 80% das linhas da classe PersonService</summary><br />


### 10. Ajuste da rota POST /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajustada da rota POST /farms/{farmId}/crops para utilizar campos com datas</summary><br />

Neste requisito, foi garantido que a rota para criação de plantações tenha os campos com data definidos abaixo.

A definição original da rota é:
- `/farms/{farmId}/crops` (`POST`)
    - deve receber o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - deve receber via corpo do POST os dados da plantação (veja abaixo para os dados de requisição
      e resposta)
    - deve salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da plantação criada. A resposta deve incluir o `id` da plantação e
          o `id` da fazenda, mas não deve incluir os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Foi necessário incluir dois atributos novos:
- `plantedDate`, representando a data em que a plantação foi semeada
- `harvestDate`, representando a data em qua a plantação foi ou está prevista para ser colhida

As datas devem ser recebidas e retornadas no formato ISO (`YYYY-MM-DD`). Sugerimos que você use o tipo `LocalDate`.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08",
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

</details>

### 11. Ajuste da rota GET /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajustada a rota GET /farms/{farmId}/crops para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/farms/{farmId}/crops` (`GET`):
    - deve receber o `id` de uma fazenda pelo caminho
    - deve retornar uma lista com todas as plantações associadas à fazenda
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-12-05",
    "harvestDate": "2023-06-08",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  }
]
```

</details>

</details>

### 12. Ajusta a rota GET /crops para utilizar datas

<details>
  <summary>Ajustada a rota GET /crops para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops` (`GET`)
    - deve retornar uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>

### 13. Ajuste da rota GET /crops/{id} para utilizar datas

<details>
  <summary>Ajuste da rota GET /crops/{id} para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops/{id}` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho da rota
    - caso exista a plantação com o `id` recebido, deve retornar os dados da plantação. A resposta
      deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os
      dados da fazenda.
    - caso não exista uma plantação com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.


<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "plantedDate": "2023-05-22",
  "harvestDate": "2024-01-10",
  "farmId": 2
}
```

</details>

</details>


### 14. Criado a rota GET /crops/search para busca de plantações

<details>
  <summary>Criado a rota GET /crops/search para busca de plantações a partir da data de colheita</summary><br />

A rota a ser criada é:
- `/crops/search` (`GET`)
    - deve receber dois parâmetros por query string para busca:
        - `start`: data de início
        - `end`: data de fim
    - deve retornar uma lista com as plantações nas quais o campo `harvestDate` esteja entre as data de início e de fim.
        - a comparação das datas deve ser inclusiva (ou seja, deve incluir datas que sejam iguais à de início ou à de fim)
    - a resposta deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>


### 15. Criado a rota POST /fertilizers

<details>
  <summary>Criado a rota POST /fertilizers para criação de um novo fertilizante</summary><br />

Neste requisito, temos a primeira rota para gerenciamento de fertilizantes.

Os fertilizantes estão em um relacionamento `n:n` com plantações.

A rota criada é:
- `/fertilizers` (`POST`)
    - deve receber via corpo do POST os dados de um fertilizante
    - deve salvar um novo fertilizante a partir dos dados recebidos
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados do fertilizante criado, incluindo seu `id`

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:

```json
{
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

</details>

</details>


### 16. Criado rota GET /fertilizers

<details>
  <summary>Criado a rota GET /fertilizers para listar todos os fertilizantes cadastrados</summary><br />

Neste requisito, temos a rota para listar todos os fertilizantes cadastrados. A rota a ser criada é:
- `/fertilizers` (`GET`):
    - deve retornar uma lista de todos os fertilizantes cadastrados, incluindo o `id` de cada.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Compostagem",
    "brand": "Feita em casa",
    "composition": "Restos de alimentos"
  },
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```
</details>

</details>


### 17. Criado a rota GET /fertilizers/{id}

<details>
  <summary>Criado a rota GET /fertilizers/{id} para pegar as informações de um fertilizante</summary><br />

Neste requisito, foi criado a rota para pegar as informações de um fertilizante. A rota a ser criada é:
- `/fertilizers/{fertilizerId}` (`GET`):
    - deve receber o `id` de um fertilizante pelo caminho da rota
    - caso exista o fertilizante com o `id` recebido, deve retornar seus dados, incluindo seu `id`
    - caso não exista um fertilizante com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

```json
{
  "id": 3,
  "name": "Adubo",
  "brand": "Feito pelas vaquinhas",
  "composition": "Esterco"
}
```

</details>

</details>


### 18. Criado a rota POST /crops/{cropId}/fertilizers/{fertilizerId}

<details>
  <summary>Criado a rota POST /crops/{cropId}/fertilizers/{fertilizerId} associando uma plantação com um fertilizante</summary><br />

Neste requisito, temos a criaação da rota para criar a associação entre uma plantação e um fertilizante. A rota a ser criada é:
- `/crops/{cropId}/fertilizers/{fertilizerId}` (`POST`)
    - deve receber tanto o `id` da plantação quanto o `id` do fertilizante pelo caminho da rota
    - o corpo da requisição será vazio
    - deve fazer a associação entre o fertilizante e a plantação
    - em caso de sucesso, deve retornar o status HTTP 201 (CREATED) com a mensagem `Fertilizante e plantação associados com sucesso!` no corpo da resposta
    - caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.
    - caso não exista um fertilizante com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de resposta para a rota `/crops/1/fertilizers/2` (supondo que exista uma plantação com `id = 1` e um fertilizante com `id = 2`):

```text
Fertilizante e plantação associados com sucesso!
```

</details>

</details>


### 19. Criado a rota GET /crops/{cropId}/fertilizers

<details>
  <summary>Cria a rota GET /crops/{cropId}/fertilizers para listar os fertilizante associados a uma plantação</summary><br />

Neste requisito, foi criado a rota para listar os fertilizante associados a uma plantação. A rota a ser criada é:
- `/crops/{cropId}/fertilizers` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho
    - deve retornar uma lista com todas os fertilizantes associados à plantação
    - caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/2/fertilizers` (supondo que exista uma plantação com `id = 2`):

```json
[
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```

</details>

</details>



### 20. Criado a rota POST /persons

<details>
  <summary>Cria a rota POST /persons para salvar novas pessoas no banco</summary><br />

Foi criado uma rota para integrar a API com o código que foi adquirido e testado na fase anterior, localizado no pacote `com.betrybe.agrix.ebytr.staff`.

A definição da rota é:
- `/persons` (`POST`)
    - deve receber no corpo da requisição:
        - `username`
        - `password`
        - `roles` (conforme definido no enum `Role`, disponibilizado com o código)
    - deve criar a pessoa com os dados passados
    - deve responder com os campos `id`, `username` e `role` (mas não `password`)

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/persons`:

```json
{
  "username": "zerocool",
  "password": "senhasecreta",
  "role": "ADMIN"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "username": "zerocool",
  "role": "ADMIN"
}
```

</details>

</details>

### 21. Adiciona a autenticação no projeto

<details>
  <summary>Adicionado a autenticação no projeto, incluindo uma rota para login que retorna um JWT</summary><br />

Neste requisito foi configurado o Spring Security e implementado a autenticação por usuário e senha.

Pontos avaliados:
1. Garantir acesso público (ou seja, desprotegido) aos endpoints:
    - POST `/persons` (criado acima, para permitir cadastro de novas pessoas)
    - POST `/auth/login` (será criado abaixo, para permitir login)
2. Criar a rota POST `/auth/login`:
    - deve receber o `username` e `password` no corpo da requisição
    - deve validar os dados passados utilizando as pessoas que foram criadas pela rota `/persons`
    - caso os dados estejam incorretos, deve retornar status 403
    - caso os dados estejam corretos, deve retornar um campo `token` contendo um JWT gerado

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/auth/login` (suppondo que os dados estejam corretos):

```json
{
  "username": "zerocool",
  "password": "senhasecreta"
}
```

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZ3JpeCIsInN1YiI6Im1ycm9ib3QiLCJleHAiOjE2ODk5ODY2NTN9.lyha4rMcMhFd_ij-farGCXuJy-1Tun1IpJd5Ot6z_5w"
}
```

</details>

</details>

### 22. Limitar acesso à rota GET /farms

<details>
  <summary>Foi limitado o acesso à rota GET /farms para pessoa autenticada com role(função) correto</summary><br />

Neste requisito limitamos o acesso à rota GET `/farms` para que apenas uma pessoa autenticada com role `USER`, `MANAGER` ou `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

### 23. Limitar acesso à rota GET /crops

<details>
  <summary>Limitado o acesso à rota GET /crops para a pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/crops` para que apenas uma pessoa autenticada com role `MANAGER` ou `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

### 24. Limitar acesso à rota GET /fertilizers

<details>
  <summary>Foi Limitado o acesso à rota GET /fertilizers para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/fertilizers` para que apenas uma pessoa autenticada com role `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

## Criação do projeto

- Trybe - Curso de Desenvolvimento Web

## Desenvolvimento da aplicação
- Leandro Bertholini