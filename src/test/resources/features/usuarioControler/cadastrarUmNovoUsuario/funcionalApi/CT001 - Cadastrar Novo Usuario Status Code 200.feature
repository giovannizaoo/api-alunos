Feature: Cadastro de novo usuário na API de alunos

  Como um administrador do sistema
  Quero cadastrar novos usuários via API
  Para que eles possam acessar os recursos disponíveis na plataforma

  Scenario: CT001 - Cadastrar um novo usuario com sucesso retornando status code 200

    Given que seja iniciada a chamada a url base da funcionalidade api alunos
    When preencho todos os campos obrigatorios do endpoint cadastrar um novo usuario
    Then a API do enpoint cadastrar um novo usuario devera retornar status code 200 com os dados esperados no corpo da resposta