# Tem mesa livre?

## Descrição

Este projeto tem como objetivo fornecer um meio de disponibilizar para aluguel salas comerciais que estiverem livre. Assim como também realiza o aluguel das respectivas salas comerciais. Gerencia salas livres e as reservas feitas por usuários interessados nas salas. 

## Tecnologias

- Servidor linux ubuntu 18.10
- Java 8
- Java EE 7
- EJB 3
- CDI
- JAX-RS
- JPA
- Hibernate
- Maven
- PostgreSql 10
- PgAdmin 3 (client para postgres)
- Jboss EAP 7
- AngularJs
- MaterializeCss

# Instalação

A seguir, um passo a passo de como instalar em uma máquina com sistema operacinal ubuntu 18.10 zerada.


```
# Instalação do Java 8

sudo add-apt-repository ppa:webupd8team/java

sudo apt-get update

sudo apt-get install oracle-java8-installer

# Instalação do PostgreSql 10

sudo apt-get install postgresql-10

sudo -u postgres psql postgres

postgres=# alter user postgres with password 'senha';

postgres=# \q

# Instalação do Jboss eap 7. Na raiz do repositório tem o arquivo jboss-eap-7.0.0-installer.jar. O instalador irá realizar algumas perguntas com o intuito de criar um usuario e senha para acessar o admin do jboss.

java -jar jboss-eap-7.0.0-installer.jar

# Instalação do maven

sudo apt-get install maven

# Instalação do client para acessar o banco de dados postgres
sudo apt-get install pgadmin3

```

#Configuração

## PostgresSql

- Abra o pgadmin3 e vamos criar uma nova conexão.

- Em nova conexão, devemos informar um nome para a conexão, o host (se for local, então localhost), usuário postgres e a senha que foi definida durante a instalação do postgresql anteriormente.

- Crie uma base de dados chamada workspace.

- Em src/test/resources existe o arquivo ddl.sql

- Rode o conteúdo do arquivo ddl.sql para criar a base de dados

## Jboss EAP

- Na raiz do repositório git, existe um arquivo chamado standalone.xml, como exemplo de configuração completa. Substitua o seu arquivo standalone.xml que se encontra em JBOSS-HOME/standalone/configuration por este ou então copia apenas a tag datasources deste arquivo, que é a configuração do banco de dados no jboss.

- Startar o jboss através do comando:

```
# Navegue atá a pasta da instalação do JBOSS-HOME/bin e execute:

./standalone.sh


```

- Acesse o endereço http://localhost:9990 e informe o usuario e senha criado anteriormente na instalação do jboss eap 7.

- Na aba deployment, devemos adicionar o arquivo postgresql-9.4-1200-jdbc41.jar que se encontra na raiz do repositório git.

- Na aba Configuration - SubSytems - Datasources - Non-XA. Se você optou por copiar do standalone.xml que se encontra no repositório basta selecionar o banco de dados workspace e inserir a senha do seu banco de dados. Caso contrario, você pode criar um novo banco de dados clicando em add. Testa se a conexão esta ok selecionando a opção Test Connection.

## Instalação da aplicação

Faça o clone do projeto, navegue até a pasta raiz do projeto, aonde se encontra o arquivo pom.xml e execute:

```
# Execute o maven para instalar a aplicação

mvn clean

mvn install

```

Com o jboss startado, vá até a aba deployment, e adicione o arquivo workspace.war que se encontra na pasta target que foi gerada pelo comando maven no seu projeto.

Para acessar a aplicação basta digitar no navegador: http://localhost:8080/workspace 


