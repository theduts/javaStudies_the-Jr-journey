# 🚗 Locadora Dutra - CRUD de Carros em Java

Este projeto é um "little CRUD" (Create, Read, Update, Delete) de console (CLI) desenvolvido como parte de um estudo focado nos fundamentos da linguagem Java e nos pilares da Programação Orientada a Objetos (POO).

O sistema simula o gerenciamento básico de uma locadora de veículos, permitindo cadastrar, listar, buscar, atualizar e remover carros.

## 🎓 Conceitos Praticados

Este projeto foi uma "caixa de areia" para aplicar e solidificar conceitos-chave do Java Intermediário:

### 1. Programação Orientada a Objetos (POO)

* **Encapsulamento:** Todos os atributos da classe `Carro` são `private` (embora no nosso estudo tenhamos deixado `default`), com acesso controlado por métodos `public` (Getters e Setters).
* **Polimorfismo:** Sobrescrita (`@Override`) do método `toString()` na classe `Carro` para permitir impressões formatadas e legíveis (`System.out.println(carro)`).
* **Abstração (Discussão):** Exploramos a diferença entre **Interfaces** ("Contratos") e **Classes Abstratas** ("Kits") e como elas seriam usadas para evoluir o projeto (ex: `abstract class Veiculo`).

### 2. Estrutura de Dados (Collections Framework)

* **`ArrayList` vs. `HashMap`:** O projeto foi **refatorado** de uma `List` para um `Map<String, Carro>`, usando o **Modelo** como chave.
* **Otimização de Busca:** A refatoração para `Map` mudou a complexidade de busca de O(n) (lenta, percorrendo um loop) para **O(1)** (instantânea, usando `.get(chave)`).

### 3. Java 8+ (Lambdas e Streams)

* **Programação Declarativa:** Substituímos loops `for`/`if` pela API de Streams.
* **Exemplo:** A busca por marca foi implementada com `.values().stream().filter(...).forEach(...)`.

### 4. Tratamento de Exceções (Robustez)

* **Exceções Customizadas:** Criamos a `CarroNaoEncontradoException` (uma *Checked Exception*) para evitar o retorno de `null`.
* **`throw` vs. `throws`:** Usamos `throw` para "lançar" o erro no método `busca()` e `throws` para "avisar" o compilador.
* **`try/catch`:** O compilador nos *forçou* a tratar o caminho infeliz (carro não encontrado), tornando o código mais seguro e evitando `NullPointerException`.

### 5. Design de Código

* **Separação de Responsabilidades (SoC):** O código é dividido em classes com papéis claros:
    * `Main`: Orquestrador e loop principal.
    * `Carro`: Modelo de dados (Entidade).
    * `CRUD`: Lógica de negócios (Serviço).
    * `Menu` / `Tools`: Classes de utilidade/visualização (`static`).
* **Injeção de Dependência (Estudo):** Discutimos e praticamos como "injetar" dependências (como o `Scanner` ou o `Map`) via construtor, em vez de criar instâncias dentro das classes.

## 🚀 Funcionalidades

O menu principal permite:
1.  **Ver todos os carros:** Lista o estoque completo (usando `mapa.values()`).
2.  **Pesquisar carro:** Busca um carro pelo modelo (usando `mapa.get()`).
3.  **Cadastrar carro:** Adiciona um novo carro ao mapa (usando `mapa.put()`).
4.  **Editar carro:** Altera os dados de um carro existente (usando `busca()` e `setters`).
5.  **Remover carro:** Remove um carro (usando `mapa.remove()`).
6.  **Buscar por marca:** Filtra e exibe carros de uma marca específica (usando `stream().filter()`).

## 🛠️ Tecnologias

* Java (JDK 17+)
* Java Collections Framework (`Map`, `HashMap`, `List`)
* Java Streams API & Lambdas (Java 8+)
* Java Exception Handling

## 🏁 Como Executar

1.  Certifique-se de ter o JDK (Java Development Kit) instalado.
2.  Compile todos os arquivos `.java`.
3.  Execute a classe `Main` (ex: `java Main`).
4.  Interaja com o menu via console.
