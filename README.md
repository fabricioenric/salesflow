# SalesFlow - Sistema de Gestão de Pedidos e Vendas

## 📌 Visão Geral
O **SalesFlow** é uma aplicação web desenvolvida para facilitar a gestão de pedidos de clientes e monitoramento de vendas de forma eficiente. O sistema permite que administradores, vendedores e clientes interajam de maneira segura, garantindo controle total sobre os pedidos e uma visão gerencial negocial completa.

---

## 🚀 Tecnologias Utilizadas
O projeto utiliza as seguintes tecnologias:

- **Frontend:** [React.js](https://reactjs.org/);
- **Backend:** [Spring Boot (Java)](https://spring.io/projects/spring-boot); 
- **Banco de Dados:** [PostgreSQL](https://www.postgresql.org/);
- **Autenticação:** [JWT](https://jwt.io/);
- **CI/CD:** [GitHub Actions](https://github.com/features/actions);
- **Containerização:** [Docker](https://www.docker.com/) & [Docker Compose](https://docs.docker.com/compose/);
- **Hospedagem:** [Heroku](https://www.heroku.com/);

---

## 🔑 Funcionalidades Principais
✔️ **Autenticação e Controle de Acesso**  
- Login utilizando **JWT**;  
- Perfis diferenciados (**Administrador, Cliente e Vendedor**).  

✔️ **Gestão de Pedidos**  
- Registro e acompanhamento de pedidos com **status** (*Em andamento, Finalizado, Cancelado*);  
- Consulta rápida de vendas realizadas.  

✔️ **Visão Gerencial**  
- Gráficos dinâmicos sobre faturamento e volume de pedidos;  
- Identificação de clientes mais ativos.  

✔️ **CI/CD e Deploy Automatizado**  
- Pipeline configurada para deploy contínuo; 
- Publicação automática em um ambiente na nuvem.  

---

## ⚙️ Como Executar o Projeto

Para executar o projeto localmente, é necessário ter o **Docker** e o **Docker Compose** instalados.

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/salesflow.git
    cd salesflow
    ```

2.  **Suba os containers:**
    Na raiz do projeto, execute o comando abaixo. Ele irá construir as imagens do backend e do frontend, e iniciar todos os serviços (incluindo o banco de dados).

    ```bash
    docker-compose up --build
    ```

3.  **Acesse a aplicação:**
    *   **Frontend:** A aplicação estará disponível em [http://localhost:80](http://localhost:80)
    *   **Backend (API):** A API estará disponível em [http://localhost:8080](http://localhost:8080)

Para parar todos os serviços, pressione `Ctrl + C` no terminal onde o `docker-compose` está rodando, ou execute `docker-compose down` em outro terminal.
