
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Optional.ofNullable(sc).orElseThrow();

        String[] line1 = sc.next().split(" ");

        if (line1.length != 4) {
            throw new IllegalArgumentException("Line 1 is not correct items");
        }

        int cardN = Integer.parseInt(line1[0]);
        if (cardN < 2 || cardN > 7) {
            throw new IllegalArgumentException("CardN is out of range number");
        }

        int projectM = Integer.parseInt(line1[1]);
        if (projectM < 2 || projectM > 8) {
            throw new IllegalArgumentException("projectM is out of range number");
        }

        int chargeK = Integer.parseInt(line1[2]);
        if (chargeK < 2 || chargeK > 5) {
            throw new IllegalArgumentException("chargeK is out of range number");
        }

        int turnT = Integer.parseInt(line1[3]);
        if (turnT != 1000) {
            throw new IllegalArgumentException("turnT must be 1000");
        }

        int currentTurn = 0;

        List<String[]> cardList = new ArrayList<String[]>();
        for (int i = 0; i < cardN; i++) {
            String[] lineN = sc.next().split(" ");
            cardList.set(i, lineN);
        }

        List<String[]> projectList = new ArrayList<>(projectM);
        for (int i = 0; i < projectM; i++) {
            String[] lineM = sc.next().split(" ");
            projectList.add(lineM);
        }

        while (currentTurn < turnT) {

            int useCardNumber = 0;
            String userCardType = "";
            int targetProjectNumber = 0;
            long targetProjectWorkVolume = Long.valueOf(projectList.get(0)[0]);
            long useWorkVolume = 0;
            for (int i = 0; i < cardN; i++) {
                String currentCardType = cardList.get(i)[0];

                switch (currentCardType) {
                    case "0":
                        long currentCardWorkVolume = Long.valueOf(cardList.get(i)[1]);
                        if (userCardType != "1" && useWorkVolume < currentCardWorkVolume) {
                            useCardNumber = i;
                            userCardType = currentCardType;
                            useWorkVolume = currentCardWorkVolume;

                            for (int j = 0; j < projectM; j++) {
                                long currentProjectWorkVolume = Long.valueOf(projectList.get(j)[0]);
                                if (currentProjectWorkVolume < targetProjectWorkVolume) {
                                    targetProjectNumber = j;
                                    targetProjectWorkVolume = currentProjectWorkVolume;
                                }
                            }
                        }
                        break;

                    case "1":
                        currentCardWorkVolume = Long.valueOf(cardList.get(i)[1]);
                        if (userCardType != "1") {
                            useCardNumber = i;
                            userCardType = currentCardType;
                            useWorkVolume = currentCardWorkVolume;
                        } else {
                            if (useWorkVolume < currentCardWorkVolume) {
                                useCardNumber = i;
                                userCardType = currentCardType;
                                useWorkVolume = currentCardWorkVolume;
                            }
                        }
                        break;
                    case "2":
                        if (userCardType != "0" && userCardType != "1") {
                            useCardNumber = i;
                            userCardType = currentCardType;
                            targetProjectNumber = 0;
                        }
                        break;
                    case "3":
                        if (userCardType != "0" && userCardType != "1") {
                            useCardNumber = i;
                            userCardType = currentCardType;
                            targetProjectNumber = 0;
                        }
                        break;
                    case "4":
                        if (userCardType != "0" && userCardType != "1") {
                            useCardNumber = i;
                            userCardType = currentCardType;
                            targetProjectNumber = 0;
                        }
                        break;
                    default:
                        break;
                }

            }

            System.out.println(useCardNumber + " " + targetProjectNumber);
            System.out.flush();

            long currentMoney = 0;
            List<String[]> suggestionCardList = new ArrayList<String[]>();

            String[] input = sc.next().split(" ");

            if (input.length == 1) {
                currentMoney = Long.valueOf(input[0]);

            } else if (input.length == 2) {
                for (int i = 0; i < projectM; i++) {
                    projectList.set(i, input);
                    input = sc.next().split(" ");
                }

            } else {
                for (int i = 0; i < chargeK; i++) {
                    suggestionCardList.set(i, input);
                    input = sc.next().split(" ");
                }

                for (int i = 0; i < suggestionCardList.size(); i++) {
                    int getCardNumber = 0;
                    String getCardType = "";
                    long getWorkVolume = 0;
                    long getCost = 0;

                    long currentCost = Long.valueOf(suggestionCardList.get(i)[2]);
                    if (currentMoney > currentCost) {
                        String currentCardType = suggestionCardList.get(i)[0];
                        long currentWorkVolume = Long.valueOf(suggestionCardList.get(i)[1]);
                        switch (currentCardType) {
                            case "0":
                                if (getCardType != "1") {
                                    if (getWorkVolume < currentWorkVolume) {
                                        getCardNumber = i;
                                        getCardType = currentCardType;
                                        getWorkVolume = currentWorkVolume;
                                        getCost = currentCost;
                                    }
                                }
                                break;
                            case "1":
                                if (getCardType != "1") {
                                    getCardNumber = i;
                                    getCardType = currentCardType;
                                    getWorkVolume = currentWorkVolume;
                                    getCost = currentCost;
                                } else if (getWorkVolume < currentWorkVolume) {
                                    getCardNumber = i;
                                    getCardType = currentCardType;
                                    getWorkVolume = currentWorkVolume;
                                    getCost = currentCost;
                                }
                                break;
                            case "2":
                                if (getCardType != "0" && getCardType != "1"
                                        && getCost > currentCost) {
                                    getCardNumber = i;
                                    getCardType = currentCardType;
                                    getWorkVolume = currentWorkVolume;
                                    getCost = currentCost;
                                }
                                break;
                            case "3":
                                if (getCardType != "0" && getCardType != "1"
                                        && getCost > currentCost) {
                                    getCardNumber = i;
                                    getCardType = currentCardType;
                                    getWorkVolume = currentWorkVolume;
                                    getCost = currentCost;
                                }
                                break;
                            case "4":
                                if (getCardType != "0" && getCardType != "1"
                                        && getCost > currentCost) {
                                    getCardNumber = i;
                                    getCardType = currentCardType;
                                    getWorkVolume = currentWorkVolume;
                                    getCost = currentCost;
                                }
                                break;
                            default:
                                break;
                        }
                        System.out.println(getCardNumber);
                        System.out.flush();
                        String[] addCardInfo = { getCardType, String.valueOf(getWorkVolume) };
                        cardList.set(useCardNumber, addCardInfo);
                        currentTurn++;
                    }
                }
            }
        }

    }
}
