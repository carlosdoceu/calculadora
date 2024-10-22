# {{ repository.name }}

## Descrição
{{ repository.description }}

## Estrutura do projeto
- `src/calcudora/back`: Lógica de memória da calculadora
- `src/calculadora/tela`: Interface de usuário da calculadora

## Requisitos
- Java JDK 17+ (ou a versão compatível que você estiver utilizando)

## Como executar o projeto

### Via IDE:
1. Abra o projeto na sua IDE (como VSCode, Eclipse, IntelliJ).
2. Navegue até a classe principal e execute o arquivo `TelaCalculadora.java` que se encontra em `src/calculadora/tela`.

### Via linha de comando:
1. Compile o projeto a partir do diretório raiz:
   ```bash
   javac -d bin src/calculadora/tela/TelaCalculadora.java src/calculadora/back/Memoria.java src/calculadora/tela/*.java