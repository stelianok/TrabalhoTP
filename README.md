## Trabalho TPI

Sistema de gerenciamento de estoque de uma loja de skate e acessórios fictícia.
Projeto desenvolvido como trabalho semestral da disciplina Técnicas de Programação. (Unesp)

## Tabelas
- Products
  - id (int PK)
  - name (String)
  - description (String)
  - category_id (FK)
  - price (Double(10,2))
  - quantity (Int)
  - added_at (String)
  - supplier_id (FK)
- Categories
  - id (int PK)
  - name (String)
  - Description (String)
- Suppliers
  - id (int PK)
  - name (String)
  - address (String)
  - phone (String)
  - email (String)


## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
