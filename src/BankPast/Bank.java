package BankPast;

import User.Client;
import User.KindClients.ClientCommon;
import User.KindClients.ClientGold;

import java.util.*;

public class Bank
{
    private List<Client> listClient = new ArrayList<Client>();


    public List<Client> getListClient() {
        return listClient;
    }

    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
    }

    public void CreateAccount()
    {
        Scanner reader = new Scanner(System.in);
        Random randNumber = new Random();

        String name;
        long numberOfAccount;
        int password;
        double moneyOfAccount;
        short kindAccount = 2;

        System.out.printf("\nVamos criar sua conta agora!\n");
        System.out.printf("\nInsira o nome co mpleto na sua conta\n");
        name = reader.nextLine();
        System.out.printf("\nO numero da sua conta será gerado de forma automática\n");
        numberOfAccount = Math.abs(randNumber.nextLong());
        System.out.printf("\nNumero da sua conta: "+numberOfAccount+" .Anote esse número em algum local, será necessário para fazer login\n");
        System.out.printf("\nA sua senha será gerada de forma automática para sua seguranca\n");
        password = Math.abs(randNumber.nextInt());
        System.out.printf("\nSua senha: "+password+" .Anote esse número em algum local, será necessário para fazer login\n");

        try
        {
            System.out.printf("\nInsira o dinheiro da sua conta, caso digite um valor inválido, sua conta será estabelecida com 0 reais como padrão\n");
            moneyOfAccount = reader.nextDouble();

        }
        catch (InputMismatchException ex)
        {
            moneyOfAccount = 0;
            System.out.println(String.format("Valor da sua conta: %f",moneyOfAccount));

        }
        reader.nextLine();

        while (kindAccount!= 0 && kindAccount != 1) {
            try
            {
                System.out.printf("\nDigite 0 para criar uma conta comum(Taxa de 30 reais na transferencia)\nDigite 1 para criar uma conta Gold (Taxa de 10 reais na transferencia)\n");
                kindAccount = reader.nextShort();
            }
            catch (InputMismatchException ex)
            {

                System.out.println("Comando invalido, digite novamente");
            }
            reader.nextLine();
        }

        switch (kindAccount)
        {
            case 0:
                ClientCommon clientCommon = new ClientCommon(name,numberOfAccount,password,moneyOfAccount,this);
                break;
            case 1:
                ClientGold clientGold = new ClientGold(name,numberOfAccount,password,moneyOfAccount,this);
                break;
            default:
                System.out.printf("\nErro na criacao da conta, tente novamente mais tarde\n");
                break;
        }


    }

    public Client LoginAccount(String name, int password, long numberOfAccount)
    {
        for(Client client: listClient)
        {
            if(name.equals(client.getName()) && password == client.getPassword() && numberOfAccount == client.getNumberOfAccount())
            {
                System.out.printf("\nLogin realizado!\n");
                return client;
            }
        }
        System.out.printf("\nArgumento inválido, tente novamente mais tarde\n");
        return null;
    }

    public void ShowAccounts()
    {
        for(Client client:listClient)
        {
            System.out.printf(String.format("\nNome: %s || Número da conta: %d\n",client.getName(),client.getNumberOfAccount()));
        }
    }


    public Client SearchAccount(String name, long numberOfAccount)
    {
        for(Client client: listClient)
        {
            if(name.equals(client.getName()) && numberOfAccount == client.getNumberOfAccount())
            {
                System.out.printf("\nConta encontrada!\n");
                return client;
            }
        }
        System.out.printf("\nConta não encontrada\n");
        return null;
    }
}
