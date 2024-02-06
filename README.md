# Teste Java Developer

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

Este projeto é uma API usando **Java, Java Spring, AWS SES, H2 como banco de dados.** 

Essa API foi desenvolvida por mim para o teste de desenvolvedor backend da TGID.

## Table of Contents

- [Instalação](#Instalação)
- [Uso](#usaUsoge)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Implementações](#Implementações)
- [Agradecimentos](#Agradecimentos)

## Instalação

1. Clone este repositório:

```bash
git clone git@github.com:gabriel-marchese/java-teste.git
```

2. Instale as dependencia com o Maven

## Uso

1. Inicia a aplicação com o maven Maven
```bash
mvn spring-boot:run
```
2. A API é acessada por http://localhost:8080


## API Endpoints
A API Funciona da seguinte forma:

**POST Cliente**
```markdown
Post /cliente/register - Primeiramente deve ser cadastrado o cliente, cadastre o email que deseja receber a confirmação.
```
```json
{
"cpf": "111.111.111-11",
"email": "adicioneseuEmail@gmail.com"
}
```

**POST Empresa**
```markdown
Post /empresa/register - Agora cadastre uma Empresa.
```
```json
{
	"cnpj": "11.111.111/0001-11",
	"taxa": 0,
	"saldo": 100
}
```

**GET Cliente**
```markdown
GET /cliente - Você pode listar todos os usuários cadastrados.
```
```json
[
    {
    "cpf": "111.111.111-11",
    "email": "adicioneseuEmail@gmail.com"
    },
    {
    "cpf": "222.222.222-22",
    "email": "adicioneseuEmail2@gmail.com"
    }
]
```

**GET Empresa**
```markdown
GET /empresa - Você pode listar todas as empresas cadastradas.
```
```json
[
    {
	"cnpj": "11.111.111/0001-11",
	"taxa": 0,
	"saldo": 100
    },
   {
	"cnpj": "22.222.222/0002-22",
	"taxa": 0,
	"saldo": 200
    }
]
```

**POST Saque**
```markdown
POST /cliente/withdraw - Você pode fazer um saque contanto que o valor passado não seja maior qque o saldo da empresa.
```
```json
{
  "value": 10,
  "cpf": "111.111.111-11",
  "cnpj": "11.111.111/0001-11"
}
```

**POST Deposito**
```markdown
POST /cliente/deposit - Você pode fazer um deposito.
```
```json
{
  "value": 100,
  "cpf": "111.111.111-11",
  "cnpj": "11.111.111/0001-11"
}
```


## Database
Esse projeto ultiliza o H2Database, como o foco não é salvar os dados em um banco de dados, a ultilização do H2 facilita o desenvolvimento.

## Implementações

Não consegui realizer os testes a tempo então ficaria como proxima implementação, e como implementações futuras, adicionar a funcionalidade de diferentes taxas, fazer a verificação do CPF e do CNPJ se estão escritas da forma esperada, fazer o deploy da aplicação. Sei que salvar as chaves da AWS não é uma boa pratica mas não queria fazer vocês perderem tempo configurando, vou deletar o usuario depois da avaliação do teste.

## Agradecimentos

Agradeço pela oportunidade, fiz o meu melhor neste tempo e estou satisfeito com esse resultado, se puderem gostaria de de receber feedbacks sobre o que posso melhorar ou algo que fiz bem, agradeço.