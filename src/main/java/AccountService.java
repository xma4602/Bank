import errors.NoSuchAccountException;
import errors.NoSuchClientException;

import java.util.List;

public class AccountService {

    public static Account getAccount(long accountNumber) throws NoSuchAccountException {
    }

    public static List<Account> getAccounts(long clientNumber) throws NoSuchClientException {
    }
    public static int put(long number, int value) {
    }

    public static int take(long number, int value) {
    }

    public static Account open(long clientNumber) {
    }

    public static void close(long accountNumber) throws NoSuchAccountException {
    }

}
