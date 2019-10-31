DROP TABLE IF EXISTS agenda;

CREATE TABLE pessoa (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nome VARCHAR (250) NOT NULL,
  cpf VARCHAR (250) NOT NULL,
  idade INT (3) NOT NULL,
  num_dependentes INT (3) NOT NULL,
  contrinuicao_previdencial NUMBER (100) NOT NULL,
  total_rendimentos NUMBER (100) NOT NULL,
  desconto NUMBER (100) NOT NULL,
  valor_imposto NUMBER (100) NOT NULL,
  total_pagar NUMBER (100) NOT NULL,
  tipo_imposto CHAR (1) NOT NULL,
);

CREATE TABLE imposto (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  porcentagem NUMBER (100) NOT NULL,
  variavel NUMBER (250) NOT NULL,
);