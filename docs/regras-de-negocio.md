# Regras de Negócio — controleDeNotas

* **R1 — Limite de Notas:** Cada nota (`nota1` e `nota2`) deve estar entre `0.0` e `10.0`. Valores fora dessa faixa são rejeitados com `IllegalArgumentException`.
* **R2 — Média e Classificação:** A média aritmética é `(nota1 + nota2) / 2`.
  * Média >= 7.0: `APROVADO`
  * Média >= 4.0 e < 7.0: `RECUPERACAO`
  * Média < 4.0: `REPROVADO`
* **R3 — Frequência Mínima:** A frequência válida vai de `0%` a `100%`. Se a frequência for menor que `75%`, a situação será `REPROVADO_POR_FALTA`, independentemente da média.
* **R4 — Unicidade de Matrícula:** Não é permitido cadastrar dois boletins com a mesma matrícula.
* **R5 — Controle de Acesso (Segurança):** Apenas usuários com perfil `PROFESSOR` podem lançar notas. Outros perfis (`ALUNO` ou nulo) recebem `SecurityException`.

---

## Registro de Teste de Regressão

* **Comportamento protegido:** Aprovação de aluno com média exata `7.0` e frequência mínima `75%` (Regra R2).
* **Alteração simulada:** Alteração acidental na classe `BoletimService` trocando `if (media >= 7.0)` por `if (media > 7.0)`.
* **Teste que detectou o defeito:** `definirSituacao_comMediaExataSeteEFrequenciaSetentaECinco_deveAprovar()` em `BoletimServiceTest`. O teste falhou ao receber `"RECUPERACAO"` no lugar de `"APROVADO"`, garantindo a correção imediata.
