import java.util.ArrayList;

public class DotCom {
    private ArrayList<String> locationCells;    // Хранит координаты ячеек корабля на поле: например А3
    private String name;    // Имя сайта

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationCells(ArrayList<String> locationCells) { // Сеттер
        this.locationCells = locationCells;
    }

    public String checkYourself(String userInput) { // Принимает пользовательский ввод и проверяет на попадание в одну из своих ячеек
        String result = "Мимо.";

        int index = locationCells.indexOf(userInput); // Проверяем содержится ли ячейка введенная пользователем внутри ArrayList
        if (index >= 0) { // Если содержится, удаляем ячейку из листа
            locationCells.remove(index);

            if (!locationCells.isEmpty()) { // Если еще остались ячейки, веозвращаем "Попал"
                result = "Попал.";
            } else {
                result = "Потопил."; // Если ячеек корабля не осталось, возвращаем "Потопил"
                System.out.println("Вы потопили " + name);
            }
        }

        return result;
    }
}
