<p align="center">
    <picture>
      <img alt="Logo do projeto" src="https://github.com/user-attachments/assets/a4ea41fa-5fa5-4b21-9c08-2407912b0679">
    </picture>
</p>


O **Spotifei** é uma aplicação de streaming musical desenvolvida em **Java**, com interface gráfica em **Swing**, banco de dados **PostgreSQL**, e arquitetura **MVC**. O projeto permite que usuários escutem suas músicas favoritas, criem playlists personalizadas e interajam com o conteúdo musical, enquanto administradores podem gerenciar o sistema e visualizar estatísticas.

## 📌 Funcionalidades

### 👤 Usuário

* **Cadastro de Usuário**
  Informe nome, data de nascimento, username, senha e gênero para criar uma conta.

* **Login de Usuário**
  Acesse sua conta utilizando username e senha.

* **Buscar Músicas**
  Pesquise por músicas usando nome, artista ou gênero.

* **Visualizar Informações das Músicas**
  Exibe: nome da música, artista, gênero, duração, número de likes e dislikes, e foto.

* **Curtir/Descurtir Músicas**
  Interaja com as músicas marcando curtidas ou descurtidas e acesse o histórico de interações.

* **Gerenciar Playlists**

  * Criar novas playlists
  * Adicionar músicas
  * Remover músicas
  * Excluir playlists

* **Visualizar Histórico**
  Acompanhe as **10 últimas músicas pesquisadas**.

---

### 🔧 Administrador

* **Login de Administrador**

  * **LOGIN ADM:** `adm`
  * **SENHA ADM:** `123`

* **Gerenciamento de Conteúdo**

  * Cadastrar novas músicas
  * Excluir músicas existentes
  * Cadastrar artistas

* **Consultar Usuários**

  * Listar usuários cadastrados no sistema

* **Visualizar Estatísticas**

  * Top 5 músicas mais curtidas
  * Top 5 músicas mais descurtidas

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Banco de Dados:** PostgreSQL
* **Interface Gráfica:** Swing
* **Arquitetura:** MVC (Model-View-Controller)

---

## 📂 Estrutura do Projeto

```
/spotifei
├── /src
│   ├── /model
│   ├── /view
│   ├── /spotifei
│   ├── /cache
│   ├── /auth
│   ├── /DAO
│   ├── /utils
│   ├── /view.assets.fonts
│   ├── /view.assets.images
│   ├── /view.custom.Classes
│   ├── /view.custom.Dialogs
│   └── /controller
├── README.md
├── LICENSE
├── manifest.mf
└── .gitignore
```

---

## 🚀 Como Executar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/GabrielSantana013/Spotifei.git
   ```

2. **Configure o banco de dados PostgreSQL** com os scripts `creates.sql` e depois o `inserts.sql` (somente caso não consiga se conectar com o nosso db, que está disponível por conta do supabase).

3. **Compile e execute** a aplicação no seu ambiente Java com suporte a Swing.

4. **Inclua o driver PostgreSQL (`postgresql-42.7.5.jar`)** na pasta `libraries` para que as queries sejam executadas corretamente.

5. **ATENÇÃO PROFESSORES!**
   Coloque o arquivo `config.properties` no pacote `DAO` para que a aplicação funcione corretamente. Caso contrário, ela não irá funcionar.
   O arquivo está zipado juntamente com o código fonte no Moodle.

---

## 📌 Observações

* O sistema distingue usuários comuns e administradores.
* As curtidas e descurtidas são armazenadas por usuário e usadas para gerar estatísticas.
* O histórico é pessoal e limitado às últimas 10 buscas.

