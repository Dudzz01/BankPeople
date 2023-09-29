package User;

import BankPast.Bank;


import java.util.Scanner;

public abstract class Client
{
    protected String name;
    protected long numberOfAccount;

    protected int password;

    protected double moneyOfAccount;

    protected int moneyTax;
    public Client(String name, long numberOfAccount, int password, double moneyOfAccount, Bank bank)
    {
        this.name = name;
        this.numberOfAccount = numberOfAccount;
        this.password = password;
        this.moneyOfAccount = moneyOfAccount;
        bank.getListClient().add(this);


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfAccount() {
        return numberOfAccount;
    }

    public void setNumberOfAccount(int numberOfAccount) {
        this.numberOfAccount = numberOfAccount;
    }

    public int getPassword()
    {
        return password;
    }

    public void setPassword(int password)
    {
        this.password = password;
    }

    public double getMoneyOfAccount()
    {
        return moneyOfAccount;
    }

    public void setMoneyOfAccount(double moneyOfAccount)
    {
        this.moneyOfAccount = moneyOfAccount;
    }

    public boolean transferMoneyToOtherAccount(Client clientToReceiveMoney,double quantOfMoneyToTransfer)
    {
        if(moneyOfAccount<=0 || (moneyOfAccount - quantOfMoneyToTransfer) < 0)
        {
            System.out.printf("\nTransferencia cancelada pois o cliente não tem dinheiro suficiente para transferir a quantia desejada\n");
            return false;
        }

        clientToReceiveMoney.moneyOfAccount += quantOfMoneyToTransfer;
        this.moneyOfAccount -= quantOfMoneyToTransfer;
        System.out.printf(String.format("\nTransferencia realizada de %s para %s no valor de %f\n",name,clientToReceiveMoney.name,quantOfMoneyToTransfer));
        return true;
    }

    public void showInformationOfMyAccount()
    {
        Scanner reader = new Scanner(System.in);


        String textInfo = """
                          \nName: %s
                          Number Account: %d
                          Money: %f\n
                          """.formatted(name,numberOfAccount,moneyOfAccount);

        System.out.printf(textInfo);

        System.out.printf("\nDeseja ver a sua senha? Digite 0 para não e 1 para sim\n");
        short optionSeePassword = reader.nextShort();

        switch (optionSeePassword)
        {
            case 0:

                break;
            case 1:
                System.out.printf("\nSenha: \n"+ password);
                break;
            default:
                System.out.printf("\nComando invalido, tente novamente mais tarde\n");
                break;
        }


    }
}
