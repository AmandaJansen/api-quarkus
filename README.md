# code-with-quarkus

Este projeto utiliza Quarkus, o Java Framework. O projeto consiste em um sistema bancário simples com conceitos sobre API RESTful. O sistema permite a criação de contas correntes, depósitos, saques, transferências, exclusão e listagem de contas.

# Estrutura do Projeto
**org.acme:** Pacote contendo a classe ContaBancoResource, que define os endpoints da API.

**services:** Pacote contendo a implementação do serviço ContaCorrenteServiceImpl, que fornece as operações bancárias.

**models:** Pacote contendo as classes de modelo Cliente, ContaBancaria e ContaCorrente.

**GlobalExceptionHandler:** Pacote contendo as exceções personalizadas ContaInvalidaException e SaldoInsuficienteException para tratamento global de exceções na API.

# Endpoints

#### GET /contacorrente/contas
Retorna uma lista de todas as contas correntes no formato de texto.

#### POST /contacorrente
Cria uma nova conta corrente. Requer parâmetros nome e cpf no corpo da solicitação.

#### POST /contacorrente/sacar
Realiza um saque em uma conta corrente. Requer parâmetros numeroConta e valor no corpo da solicitação.

#### PATCH /contacorrente/transferir
Realiza uma transferência entre duas contas correntes. Requer parâmetros contaOrigem, contaDestino e valor no corpo da solicitação.

#### POST /contacorrente/depositar
Realiza um depósito em uma conta corrente. Requer parâmetros numeroConta e valor no corpo da solicitação.

#### DELETE /contacorrente/deletar
Exclui uma conta corrente. Requer parâmetro numeroConta no corpo da solicitação.

# Como Executar
Certifique-se de ter o Java instalado em sua máquina.
Compile e execute o projeto utilizando a sua IDE de escolha.
Os endpoints da API estarão disponíveis em http://localhost:8080/api/v1.


## Executando a aplicação em dev mode
```shell script
./mvnw compile quarkus:dev
```

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Sessão para guia relacionado...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
