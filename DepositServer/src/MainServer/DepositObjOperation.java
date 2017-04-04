package MainServer;

import java.util.List;
import java.util.stream.Collectors;

class DepositObjOperation {
    String addDeposit(String jsonStr) {
        DepositObj depositObj = JsonSerialize.deserialzeFromClient(jsonStr);
        if (JsonSerialize.loadFromJsonFile().stream().anyMatch(p -> p.getAccountId() == depositObj.getAccountId())) {
            return "account id already exist";
        } else {
            JsonSerialize.saveToJsonFile(depositObj);
            return "ok, the deposit was added ...";
        }
    }

    String getAllSum() {
        double allSum = JsonSerialize.loadFromJsonFile().stream().mapToDouble(DepositObj::getAmountDeposit).sum();
        return String.valueOf(allSum);
    }

    String getCountDeposits() {
        return String.valueOf(JsonSerialize.loadFromJsonFile().size());
    }

    String getInfoAccount(String accountId) {
        long id = Long.parseLong(accountId);
        if (JsonSerialize.loadFromJsonFile().stream().anyMatch(p -> p.getAccountId() == id)) {
            return JsonSerialize.loadFromJsonFile().stream().filter(p -> p.getAccountId() == id).findFirst().get().toString();
        }
        return ("not found record with next account id: " + id);
    }

    String getInfoDepositor(String depositor) {
        List<DepositObj> depositObjList;
        if (JsonSerialize.loadFromJsonFile().stream().anyMatch(p -> p.getDepositor().equals(depositor))) {
            depositObjList = JsonSerialize.loadFromJsonFile().stream().filter(p -> p.getDepositor().
                    equals(depositor)).
                    collect(Collectors.toList());
        } else {
            return "current depositor not found";
        }
        return JsonSerialize.serialzeDepositList(depositObjList);
    }

    String getDepositByType(String type) {
        List<DepositObj> depositObjList;

        depositObjList = JsonSerialize.loadFromJsonFile().stream().filter(p -> p.getTypeDeposit().name().
                equals(type)).
                collect(Collectors.toList());
        if (depositObjList.size() == 0)
            return "records with current deposit type are not found";

        return JsonSerialize.serialzeDepositList(depositObjList);
    }

    String getDepositByBank(String bank) {
        if (JsonSerialize.loadFromJsonFile().stream().anyMatch(p -> p.getName().equals(bank))) {
            return JsonSerialize.serialzeDepositList(
                    JsonSerialize.loadFromJsonFile().stream().filter(p -> p.getName().equals(bank)).collect(Collectors.toList()));
        }
        return "records with current name of bank are not found";
    }

    String deleteAccount(String accountId) {
        long id = Long.parseLong(accountId);
        if (JsonSerialize.loadFromJsonFile().stream().anyMatch(p -> p.getAccountId() == id)) {
            List<DepositObj> depositObjList = JsonSerialize.loadFromJsonFile();
            DepositObj delObj = depositObjList.stream().filter(p -> p.getAccountId() == id).findFirst().get();
            if (depositObjList.contains(delObj)) {
                depositObjList.remove(depositObjList.indexOf(delObj));
                JsonSerialize.saveToJsonFile(depositObjList);
                return "account was remove ... ";
            }
        }
        return "account not found";
    }
}