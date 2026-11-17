# controleDeNotas

Projeto Final da disciplina de **Testes de Software** (Curso Técnico), desenvolvido em Java com JUnit 5, GitHub Actions e fluxo colaborativo no GitHub.

## Integrantes
* **Integrante 1:** Roberto de Luna Silva
* **Integrante 2:** Desireê da Costa Oliveira

## Descrição
O **controleDeNotas** é um sistema simples em Java para lançamento de notas e frequência escolar, cálculo de média, definição da situação do aluno e armazenamento em memória com validação de perfil de acesso.

## Principais Regras de Negócio
* **R1:** Notas devem estar entre `0.0` e `10.0`.
* **R2:** Média >= 7.0 (`APROVADO`), entre `4.0` e `6.9` (`RECUPERACAO`) e < 4.0 (`REPROVADO`).
* **R3:** Frequência < 75% resulta em `REPROVADO_POR_FALTA`.
* **R4:** Matrículas duplicadas são rejeitadas.
* **R5:** Apenas o perfil `PROFESSOR` pode lançar notas.

*(Veja mais detalhes em `docs/regras-de-negocio.md`)*

## Tecnologias Utilizadas
* **Java 11**
* **Apache Maven**
* **JUnit 5 (5.8.2)**
* **IntelliJ IDEA 2021.3.1**
* **Git, GitHub e GitHub Actions**

## Como Executar o Projeto
1. Abra o projeto `controleDeNotas` no **IntelliJ IDEA 2021.3.1** (`File > Open` e selecione a pasta do projeto).
2. Aguarde o Maven carregar as dependências.
3. Abra a classe `src/main/java/com/escola/notas/Main.java` e clique no ícone verde de **Play** ao lado do método `main`.

## Como Executar os Testes
* **Pelo IntelliJ IDEA:** Clique com o botão direito na pasta `src/test/java` e selecione **Run 'All Tests'**.
* **Pelo Terminal:** Execute o comando:
  ```bash
  mvn test