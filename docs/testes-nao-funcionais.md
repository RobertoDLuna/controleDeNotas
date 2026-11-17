# Aspectos de Qualidade Não Funcionais — controleDeNotas

## 1. Desempenho
* **Risco ou preocupação:** Demora no cálculo e armazenamento ao registrar vários boletins de uma turma.
* **O que é medido ou observado:** Tempo total de execução dos testes automatizados via Maven (`mvn test`).
* **Critério de aceitação:** O processamento da suíte completa de testes e validações deve ocorrer em menos de **5 segundos**.

## 2. Segurança
* **Risco ou preocupação:** Usuário sem permissão (ex: perfil `ALUNO`) registrar ou alterar notas no sistema.
* **O que é medido ou observado:** Bloqueio das chamadas ao método `registrarBoletim` quando o parâmetro for diferente de `"PROFESSOR"`.
* **Critério de aceitação:** 100% das tentativas sem perfil `PROFESSOR` devem lançar `SecurityException` sem salvar registros no repositório.
