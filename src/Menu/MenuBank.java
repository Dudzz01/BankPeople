package Menu;

import BankPast.Bank;
import User.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuBank
{
    private Bank bank = new Bank();

    private Client clientCurrent;

    public void EnterAccount()
    {
        Scanner reader = new Scanner(System.in);
        short option = 24;
        System.out.printf("\nBem vindo ao BankPeople, siga as instrucoes abaixo!\n");
        do
        {
            while (option != 0 && option != 1 && option != 2)
            {
                System.out.printf("\nDigite 0 para encerrar o programa, Digite 1 para criar uma conta e Digite 2 para fazer um login\n");
                try
                {
                    option = reader.nextShort();
                }
                catch (InputMismatchException ex)
                {
                    System.out.println("Comando inválido, tente novamente");

                }
                reader.nextLine();

            }

            switch (option)
            {
                case 0:
                    System.out.printf("\nPrograma encerrado.\n");
                    System.exit(0);
                    break;
                case 1:
                    bank.CreateAccount();
                    this.EnterAccount();
                    break;
                case 2:
                    String name;
                    int password = 3;
                    long numberAccount = 4;
                    boolean repairPassword = false;
                    boolean repairNumberAccount = false;

                    reader.nextLine(); //Retirando Bug de Cache
                    System.out.printf("\nDigite o nome da sua conta\n");

                    name = reader.nextLine();

                    do
                    {
                        try
                        {
                            System.out.printf("\nDigite a senha da sua conta (Apenas números inteiros)\n");
                            repairPassword = false;
                            password = reader.nextInt();
                        }
                        catch (InputMismatchException ex)
                        {
                            reader.nextLine();
                            repairPassword = true;
                            System.out.println("Senhas com digitos inválidos (Caracteres espeiciais e letras), digite novamente");
                        }
                    }while (repairPassword == true);

                    do
                    {
                        try
                        {
                            System.out.printf("\nDigite o numero da sua conta\n");
                            repairNumberAccount = false;
                            numberAccount = reader.nextLong();
                        }
                        catch (InputMismatchException ex)
                        {
                            reader.nextLine();
                            repairNumberAccount = true;
                            System.out.println("Numero da conta com digitos inválidos (Caracteres espeiciais e letras), digite novamente");
                        }
                    }while (repairNumberAccount == true);



                    clientCurrent = bank.LoginAccount(name,password,numberAccount);

                    if(clientCurrent!=null)
                    {
                        BankOperations();
                    }
                    else
                    {
                        System.out.printf("\n Conta não encontrada, tente novamente\n");
                    }
                    this.EnterAccount();
                    break;
            }

        }while (option!=0);

    }



    public void BankOperations()
    {
        Scanner reader = new Scanner(System.in);
        short option = 0;
        System.out.printf("\nAgora você está no centro de operacoes da sua conta!\n");
        do
        {
            System.out.printf("\nDigite 0 para sair o programa, Digite 1 para transferir dinheiro a uma conta e Digite 2 para fazer ver os detalhes da sua conta\n");
            option = reader.nextShort();

            switch (option)
            {
                case 0:
                    clientCurrent = null;
                    EnterAccount();
                case 1:

                    String name;
                    long numberOfAccount;
                    double moneyTransfer;
                    Client clientToTransfer;

                    System.out.printf("\nObserve a lista de clientes para qual você pode transferir o seu dinheiro\n");
                    bank.ShowAccounts();
                    reader.nextLine();
                    System.out.printf("\nAgora, digite o nome da conta para qual o senhor(a) deseja transferir o dineiro\n");
                    name = reader.nextLine();
                    System.out.printf("\nDigite o numero da conta para qual o senhor(a) deseja transferir o dinheiro\n");
                    numberOfAccount = reader.nextLong();
                    System.out.printf("\nDigite a quantia que deseja transferir para a outra conta\n");
                    moneyTransfer = reader.nextDouble();

                    clientToTransfer = bank.SearchAccount(name,numberOfAccount);

                    if(clientToTransfer != null)
                    {
                        clientCurrent.transferMoneyToOtherAccount(clientToTransfer,moneyTransfer);
                    }
                    else
                    {
                        System.out.printf("\nConta para transferir não encontrada!\n");
                    }

                    break;
                case 2:
                    clientCurrent.showInformationOfMyAccount();
                    break;
            }

        }while (option!=0);
    }

}
