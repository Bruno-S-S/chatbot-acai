DROP TABLE IF EXISTS chatbot;

CREATE TABLE IF NOT EXISTS MENSAGEM(
    id                      INT IDENTITY(1,1)   PRIMARY KEY,
    mensagem                VARCHAR(150)        NOT NULL,
    opcoes                  VARCHAR(100)
);