import java.util.ArrayList;

public class DotComBust {
    GameHelper helper = new GameHelper();
    ArrayList<DotCom> dotComList = new ArrayList<>();
    int numOfGuesses = 0;

    private void setUpGame() {
        DotCom one = new DotCom();  // Создаем 3 корабля, даем им имена, помещаем их в ArrayList
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToy.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);

        System.out.println("Ваша цель - потопить три сайта!"); // Инструкции
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Попытайтесь потопить их за минимальное количество ходов.");

        for (DotCom dotCom : dotComList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotCom.setLocationCells(newLocation);
        }
    }
    private void startPlaying() {
        while (!dotComList.isEmpty()) {
            String userGuess = helper.getUserInput("Сделайте ход: ");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо.";
        for (DotCom dotCom : dotComList) {
            result = dotCom.checkYourself(userGuess);

            if (result.equals("Попал.")) {
                break;
            }
            if (result.equals("Потопил.")) {
                dotComList.remove(dotCom);
                break;
            }
        }
        System.out.println(result);

    }
    private void finishGame() {
        System.out.println("Все сайты ушли ко дну.");
        if (numOfGuesses <= 18) {
            System.out.println("Это заняло у Вас " + numOfGuesses + " попыток.");
            System.out.println("Вы молодец.");
        } else {
            System.out.println("Это заняло у Вас много времени и " + numOfGuesses + " попыток.");
            System.out.println("Чут-чуть не молодец.");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}
