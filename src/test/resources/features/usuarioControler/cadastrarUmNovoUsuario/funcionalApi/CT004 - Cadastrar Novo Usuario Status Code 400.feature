#// Desconsiderado pois api está sendo desenvolivda no momento
#Feature: Cadastro de novo usuário na API de alunos
#
#  Como um administrador do sistema
#  Quero cadastrar novos usuários via API
#  Para que eles possam acessar os recursos disponíveis na plataforma
#
#  Scenario: CT004 - Cadastrar um novo usuario utizando um cpf de um usuario já cadastrado retornando status code 400
#
#    Given que seja iniciada a chamada a url base da funcionalidade api alunos
#    When preencho todos os campos obrigatorios do endpoint cadastrar um novo usuario utilizando um cpf ja cadastrado em outro usuario
#    Then a API do enpoint cadastrar um novo usuario devera retornar status code 400 com os dados esperados no corpo da resposta negativo