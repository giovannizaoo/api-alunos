Feature: Cadastro de novo usuário na API de alunos

  Como um administrador do sistema
  Quero cadastrar novos usuários via API
  Para que eles possam acessar os recursos disponíveis na plataforma

  Scenario Outline: CT003 - Validar cadastro de usuário com diferentes entradas
    Given que seja iniciada a chamada a url base da funcionalidade api alunos
    When preencho os campos obrigatórios do endpoint com os seguintes dados:
      | nomeCompleto | <nomeCompleto> |
      | email        | <email>        |
      | cpf          | <cpf>          |
      | senha        | <senha>        |
    Then a API deverá retornar status code <statusCode>

    Examples:
      | nomeCompleto | email                             | cpf         | senha         | statusCode | mensagem |
      | João Silva   | joao.silva@e2etreinamentos.com.br | 21731682077 | Hbda66778899! | 200        |          |

#// Desconsiderado pois api está sendo desenvolivda no momento

#  Scenario Outline: CT004 - Validar cadastro de usuário com diferentes entradas
#    Given que seja iniciada a chamada a url base da funcionalidade api alunos
#    When preencho os campos obrigatórios do endpoint com os seguintes dados de um usuario ja cadastrado:
#      | nomeCompleto | <nomeCompleto> |
#      | email        | <email>        |
#      | cpf          | <cpf>          |
#      | senha        | <senha>        |
#    Then a API deverá retornar status code <statusCode>
#    And o corpo da resposta devera conter a mensagem de erro <mensagem>
#
#    Examples:
#      | nomeCompleto | email                             | cpf         | senha         | statusCode | mensagem             |
#      | João Silva   | joao.silva@e2etreinamentos.com.br | 21731682077 | Hbda66778899! | 400        | "CPF já cadastrado." |
