import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {
    private static final String ALPHABET = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    public int comCount = 0;                                     // Счетчик созданных сайтов

    public String getUserInput(String prompt) {                 //Предлагаем ввести координаты, принимаем ввод и отправляем его
        String inputLine = null;
        System.out.println(prompt + ": ");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            inputLine = reader.readLine();
            if (inputLine.length() == 0) {
                return null;
            }
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();        //Хранит координаты: А3 и т.д.
        String temp = null;                                     //Временная строка для конкатенации
        int[] coords = new int[comSize];                        // Координаты текущего сайта
        int attempts = 0;                                       // Счетчик текущих попыток
        boolean success = false;                                // Нашли или не нашли?
        int location = 0;                                       // Текущее начальное положение

        comCount++;
        int incr = 1;                                           //Горизонтальный инкремент
        if ((comCount % 2) == 1) {
            incr = gridLength;                                  //Вертикальный инкремент
        }

        while (!success & attempts++ < 200) {
            location = (int) (Math.random() * gridSize);        // Получаем случайную стартовую точку
            //System.out.println("Пробуем " + location);
            int x = 0;                                          //Энная позиция в сайта который нужно разместить
            success = true;                                     //Предполагаем успех

            while (success && x < comSize) {
                if (grid[location] == 0) {                      // Если ячейка еще не зенята
                    coords[x++] = location;                     //Сохраняем местоположение
                    location += incr;                           // Пробуем следующую ячейку
                    if (location >= gridSize) {                 // Выход за рамки вниз
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) { //Выход за рамки вправо
                        success = false;
                    }
                } else {                                        // Местоположение уже занято
                    //System.out.println("Используется: " + location);
                    success = false;
                }
            }

        }

        int x = 0;
        int row = 0;
        int column = 0;
       // System.out.println("/n");
        while (x < comSize) {
            grid[coords[x]] = 1;                                //Помечаем ячейки главной сетки как использованые
            row =  coords[x] / gridLength;                      //Получаем значение строки
            column = coords[x] % gridLength;                    //Получаем значение столбца
            temp = String.valueOf(ALPHABET.charAt(column));     // Преобразуем полученое в строковый символ
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
            //System.out.println("coord " + x + " = " + alphaCells.get(x-1)); // Показываем координаты на консоли
        }
        return alphaCells;

    }
}
