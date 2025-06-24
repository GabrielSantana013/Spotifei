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

## 📂 Estrutura do Projeto

```
/spotifei
├── /src/main
│ ├── /model
│ ├── /view
│ ├── /spotifei
│ ├── /cache
│ ├── /auth
│ ├── /DAO
│ ├── /utils
│ ├── /view.assets.fonts
│ ├── /view.assets.images
│ ├── /view.custom.Classes
│ ├── /view.custom.Dialogs
│ └── /controller
├── /target
│ ├── /classes/view/assets
│ ├── /maven-archiver
│ └── /maven-status/maven-compiler-plugin/compile/default-compile
├── README.md
├── LICENSE
├── manifest.mf
├── pom.xml
└── .gitignore
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 11 ou superior
- Maven 3.6.0 ou superior
- PostgreSQL configurado (ou acesso ao banco Supabase)

### Método 1: Usando Maven (Recomendado)

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/GabrielSantana013/Spotifei.git
   cd Spotifei

2. **Configure o banco de dados PostgreSQL** com os scripts `creates.sql` e depois o `inserts.sql` (somente caso não consiga se conectar com o nosso db, que está disponível por conta do supabase).

3. **Compile e execute** a aplicação no seu ambiente Java com suporte a Swing.

4. **ATENÇÃO PROFESSORES!**
   Coloque o arquivo `config.properties` no pacote `DAO` para que a aplicação funcione corretamente. Caso contrário, ela não irá funcionar.
   O arquivo está zipado juntamente com o código fonte no Moodle.

---

## 📦 Rodando pelo Maven

Se preferir usar o Maven para compilar e executar, siga esses passos:

Certifique-se de ter o Maven instalado e configurado no seu sistema, caso não tenha você pode instalar com:

```bash
sudo apt install maven
```

E checar a instalação com:

```bash
mvn -v
```

No terminal, dentro da pasta do projeto, rode para compilar:

``` bash
mvn clean package
```
Isso vai gerar dois JARs dentro da pasta target:

    spotifei.jar — JAR simples sem dependências.

    spotifei-completo.jar — JAR executável com todas as dependências embutidas.

Para executar o JAR com todas as dependências:

```bash
java -jar target/spotifei-completo.jar
```

## 📌 Observações

* O sistema distingue usuários comuns e administradores.
* As curtidas e descurtidas são armazenadas por usuário e usadas para gerar estatísticas.
* O histórico é pessoal e limitado às últimas 10 buscas.
---

## 👥 Autores
<table align="center"> <tr> <td align="center"> <a href="https://github.com/GabrielSantana013" target="_blank"> <img src="https://github.com/GabrielSantana013.png" width="200"/> <br /> <sub><b>Gabriel Santana</b></sub> </a> </td> <td align="center"> <a href="https://github.com/PedroSchneider1" target="_blank"> <img src="https://github.com/PedroSchneider1.png" width="200"/> <br /> <sub><b>Pedro Schneider</b></sub> </a> </td> </tr> </table>


## 🛠️ Tecnologias Utilizadas 
<p align="left"> <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java"/> <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/> <img src="https://img.shields.io/badge/Swing-6DB33F?style=for-the-badge&logo=java&logoColor=white" alt="Swing"/> <img src="https://img.shields.io/badge/MVC%20Architecture-007ACC?style=for-the-badge&logo=archlinux&logoColor=white" alt="MVC"/> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/> </p>
