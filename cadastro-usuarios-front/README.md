# Frontend — Cadastro de Usuários

Frontend React + TypeScript + Vite para consumir a API Spring Boot de cadastro de usuários.

## Requisitos

- Node.js 20+ recomendado
- npm
- API Spring Boot acessível

## Executar localmente

1. Extraia a pasta e abra o terminal dentro de `cadastro-usuarios-front`.
2. Instale as dependências:

   ```bash
   npm install
   ```

3. Crie um arquivo `.env` baseado no `.env.example` e ajuste a URL da API:

   ```env
   VITE_API_URL=http://18.227.107.252:8080
   ```

4. Inicie o frontend:

   ```bash
   npm run dev
   ```

5. Abra o endereço local exibido pelo Vite, normalmente `http://localhost:5173`.

## Funcionalidades

- Listar usuários (`GET /usuarios`)
- Cadastrar usuário (`POST /usuarios`)
- Editar usuário (`PUT /usuarios/{id}`)
- Excluir usuário (`DELETE /usuarios/{id}`)
- Buscar por nome, e-mail ou UUID
- Estados de carregamento, mensagens de erro e confirmação

## Atenção: CORS

Como o frontend roda em uma origem diferente da API (por exemplo, `localhost:5173` e `18.227.107.252:8080`), o backend precisa permitir CORS para a origem do frontend. Se o navegador exibir um erro de CORS, configure o Spring Boot para permitir a origem exata do frontend e os métodos `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`, além dos headers necessários.

A API está usando HTTP no exemplo. Para uso público real, configure HTTPS e não trate esta configuração de demonstração como ambiente de produção.

## Observação sobre edição

O formulário de edição envia `usuario`, `email` e `senha` para `PUT /usuarios/{id}`. A senha é obrigatória se as validações do DTO de atualização exigirem esse campo.
