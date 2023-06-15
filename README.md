# Trabalho Final - GCES

## Projeto: Travel Expense - TIS 5 (Back-end)
## Descrição:

O projeto escolhido é um sistema de gestão de viagens para pequenas e grandes organizações. Ele é separado em três componentes, sendo eles, um back-end em Java Spring, um front-end web e uma aplicação mobile. Toda a comunicação com o back-end é feita através de uma API Rest. Para este trabalho nós utilizamos o back-end em Java do projeto.

## Melhorias:
Foram realizadas correções de erros, remoção de código não usado, além da implementação de dois novos endpoints para facilitar o uso da API e uma alteração no sistema de autenticação.

### Integrantes e divisão de tarefas:
 - Carlos Júnior - Correções e remoção de código não utilizado
 - Ian Marcel - Testes de model
 - Kleyann Martins - Features e CI
 - Rafael Duarte - Correções, CI e CD
 - Lucas Lotti - Testes de controller e CI

## CI/CD:
Os workflows localizados em '.github/workflows' realizam, com o Docker, os testes projetados para o sistema a cada *pull request* na *branch* principal. Caso todos os testes sejam realizados sem nenhum erro, é criado um novo release com um *package* do projeto no repositório.

## Testes:
Testes desenvolvidos para as models:
 - Cliente
 - Despesa
 - Empresa
 - Endereco
 - Funcionario
 - Orcamento
 - Viagem

Para os testes de controller foi utilizada a estratégia de mocking nos seguintes itens:
 - AuthController
 - ClienteController
 - DespesaController
 - EmpresaController
 - FuncionarioController
 - OrcamentoController
 - ViagemController

Testes de DTO:
 - HeaderViagemDTO
 - UsuarioDTO


