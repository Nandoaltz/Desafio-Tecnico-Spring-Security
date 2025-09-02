## MVP Spring Security

- Projeto desenvolvido seguindo a arquitetura em camadas, com o Spring Security implementado para autenticação baseada em JWT (JSON Web Tokens).
- Para a persistência de dados, foi utilizado PostgreSQL em containers Docker, permitindo tanto a execução do banco de
  dados com volumes persistentes quanto a orquestração conjunta com o container da aplicação, garantindo portabilidade e consistência do ambiente.
- O Swagger foi integrado ao projeto para documentação da API, oferecendo uma descrição
  detalhada dos modelos de entidade e dos endpoints, facilitando o entendimento e a utilização da aplicação.
- Foram aplicadas coberturas de testes unitários utilizando Mockito, verificando o correto funcionamento
  das operações básicas de CRUD e do fluxo de autenticação/login.

- [x]  • Permita inclusão de novos clientes.
- [x]  • Permita a atualização de clientes existentes.
- [x]  • Permita a exclusão de clientes existentes.
- [x]  • Permita a listagem dos clientes de forma paginada.
- [x]  • Permita buscas por atributos cadastrais do cliente.
- [x]  • É necessário também que cada elemento retornado pela API contenha (além das informações de cadastro) a idade calculada a partir da data de nascimento.
- [x]  • Utilize Swagger para documentação/especificação da API.
- [x]  • Utilize Spring Security e JWT para segurança da aplicação.
- [x]  • Utilize Spring Data JPA para abstração da camada de acesso a dados. 
- [x]  • Utilização de boas práticas de código.
- [x]  • Utilização de padrões de projeto.
- [x]  • Cobertura de testes (Unitários, Integrados e/ou de comportamento).
- [x]  • Design de API REST. Bônus - Para ir além das expectativas, desenvolva os itens abaixo, conforme sentir necessidade:
- [x]  • Empacote sua aplicação com Docker.
