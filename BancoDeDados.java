import java.sql.Connection;

 
import java.sql.DriverManager;


import java.sql.ResultSet;


import java.sql.SQLException;

import java.util.Scanner;

public class BancoDeDados implements InterfaceBancoDeDados {
  
    private Connection connection;
    public static void main(String[] args) {

        BancoDeDados bd = new BancoDeDados();
        bd.conectar("jdbc:mysql://localhost:3306/reuniao", "root", "") ;
        bd.consultar("SELECT * FROM pessoa");
       
        bd.inserirAlterarExcluir("INSERT INTO pessoa(nome,email,cargo) values ('Adriana Santos','adri_s@yahoo.com', 'Diretora')");

        bd.desconectar();

        


    }




    @Override
    public void conectar(String db_url, String db_user, String db_password)  {

        try{
            System.out.println("Iniciando conexão");
           
            connection= DriverManager.getConnection(db_url, db_user, db_password);
           
            System.out.println("Conectado ao BD");
    
        }catch (SQLException e) {
    
            
            System.out.println("Nao foi possível conectar ao BD"+e);
            
            }
       
    }

    @Override
    public void desconectar() {
        try {
            connection.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
        }
    }


        
       
    

    @Override
    public void consultar(String db_query) {
        try{
            java.sql.Statement statement = connection.createStatement();
        
    
            ResultSet resultSet = statement.executeQuery(db_query);
        while(resultSet.next()){

        System.out.println(resultSet.getString(1)+ " " +resultSet.getString(2)+ " " +resultSet.getString(3)+ " " + resultSet.getString(4)+ " "  );



        }

     
     }catch (SQLException e) {
    
            
        System.out.println("Nao foi possível conectar ao BD"+e);
        
        }
    }







    @Override
    public int inserirAlterarExcluir(String db_query) {
       int linhas=0;
        try{
            java.sql.Statement statement = connection.createStatement();
        
            linhas = statement.executeUpdate(db_query);
            System.out.println("Dados inseridos com sucesso");
     
     }catch (SQLException e) {
    
            
        System.out.println("Nao foi possível inserir, alterar ou excluir"+" "+e);
        
        }
        
        return linhas;
        
        
        
     
    }
    
    



}
