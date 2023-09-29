package User.KindClients;

import BankPast.Bank;
import User.Client;

public class ClientGold extends Client
{
    public ClientGold(String name, long numberOfAccount, int password, double moneyOfAccount, Bank bank)
    {
        super(name,numberOfAccount,password,moneyOfAccount,bank);
        moneyTax = 10;
        System.out.printf(String.format("\nO %s é um cliente gold! Logo para realizar transfencias, pagará uma taxa adicional de %d reais\n",name,moneyTax));
    }
    @Override
    public boolean transferMoneyToOtherAccount(Client clientToReceiveMoney, double quantOfMoneyToTransfer)
    {
        boolean canUseTax;

        double moneyCopy = moneyOfAccount;
        moneyCopy-= (moneyTax+quantOfMoneyToTransfer);

        if(moneyCopy<0)
        {
            System.out.printf("\nA taxa cobrada pela transferencia nao pode ser paga\n");
            return false;
        }


        canUseTax = super.transferMoneyToOtherAccount(clientToReceiveMoney, quantOfMoneyToTransfer);

        if(canUseTax)
        {
            moneyOfAccount -=moneyTax;
        }

        return true;
    }
}
