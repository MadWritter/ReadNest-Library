<h1>ReadNest</h1>
ReadNest é uma API para uma biblioteca virtual feita em Java com Quarkus.<br>
Seu objetivo é simular o cadastro de livros e empréstimo/aluguel dos mesmos.<br><br>

Minha concentração pessoal no desenvolvimento dessa API é trabalhar com o conceito
de EDD(Event-Driven Development) e dar os primeiros passos com o mesmo, trabalhando
apenas com o conceito, sem frameworks ou algo que abstraia isso por baixo.<br>

Posteriormente, irei fazer uma refatoração da estrutura para abordagem com Kafka.

Na parte de <b>Estrutura</b> fica destacado como a API foi desenhada, enquanto em <b>Recursos nescessários</b> fica o que foi utilizado para construir a API.<br>
<h2>Estrutura</h2>
A API terá 3 entidades:

 - Livro
 - Autor
 - Cliente

Cada Livro possui um Autor, além de uma quantidade em estoque, cada Cliente pode alugar um ou mais destes livros e devolver em um prazo determinado.

Além das entidades, a API terá os seguintes endpoints:

 - /cliente (GET) - retorna todos os clientes cadastrados
 - /cliente/{id} (GET) - retorna o cliente cadastrado
 - /login (POST) - endpoint para autenticação do usuário, retornando o token JWT que será usado nos recursos.
 - /cliente (POST) - cadastra um novo cliente
 - /cliente (PUT) - atualiza um cliente existente
 - /cliente (DELETE) - deve fazer a exclusão lógica do cliente
 - /livro (GET) - retorna o acervo de livros
 - /livro/{id, nome, categoria} (GET) - deve retornar baseado no filtro informado
 - /livro (POST) - cadastra um novo livro
 - /livro (PUT) - atualiza um livro cadastrado
 - /livro (DELETE) - faz a exclusão lógica do livro cadastrado
 - /alugar/{id do usuário}&&{id ou nome do livro} (POST) - faz o aluguel de um livro cadastrado
 - /devolver{id do usuário}&&{id ou nome do livro} (POST) - faz a devolução de um livro alugado

O objetivo dessa API é testar os conhecimentos de EDD(Event-Driven Development) com Quarkus, designando os serviços a serem reativos a eventos(requisições).<br>
<h2>Recursos Nescessários</h2>
A API foi construída com os seguintes recursos:<br><br>

 - JDK 21
 - Maven 3.9.9
 - Quarkus 3.25
 - H2 Database (Banco SQL em memória)
 - Autenticação via JWT
 - Hibernate ORM com Panache (JPA)
 - Hibernate Validator (Bean Validation).
 - Junit 5 para testes automatizados.
<br>
<b>OBS</b>: em breve irei adicionar o link para o Postman com todas as requisições configuradas.
