# Exemplo de Envio e Recimento de dados via TCP/IP e Inserção no banco de dados MySQL


## Tecnologias

- Java 11 ou superior
- MySQL
- Maven

## Informações Gerais

Este projeto mostra um exemplo de envio e recimento de dados via TCP/P e Inserção dos dados no banco de dados MySQL.

Os dados enviados e recebidos segue a estrutura:

1 byte (com um conjunto de 8 bits com informações ainda a serem definidas em cada bit)
2 bytes (um valor inteiro)
4 bytes (um valor real)


Os dados são recebidos como bytes e então convertidos para a classe DataCLP e então enviados para o MySQL.

A tabela usada foi criada com o seguinte SQL:


```sql
CREATE TABLE data_clp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bit_set TINYINT,
    sensor1 INT,
    sensor2 FLOAT
);
```


A simulação (DataSimulator) irá executar por 5 segundos gerando DataCLP's aleatórios e salvando-os no banco.


## Configurando o mysql

Se tiver o docker instalado, acesse: https://hub.docker.com/repository/docker/osdeving/mysql-dev/general

Siga as instruções e então:

```
$ docker exec -it mysql8 mysql -uadmin -p
[digite a senha 123456]

mysql> create database clp_alex;

mysql> use database clp_alex;

mysql> CREATE TABLE data_clp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bit_set TINYINT,
    sensor1 INT,
    sensor2 FLOAT
);
```

Se não tiver o docker instalado, instale e execute o mysql na máquina e crie o banco e o database.


## Como Testar

Faça o clone do projeto:

```
git clone https://github.com/osdeving/tcp-request-and-save-to-db.git
```

Compile como:

```
mvn clean package
```

E rode com:

```
java -jar target/tcp-request-and-save-to-db-1.0-SNAPSHOT.jar
```





## Codificado por Willams "will" Sousa




