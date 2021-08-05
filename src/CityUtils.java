import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CityUtils {

    private final static String PATH = "G:\\JProjects\\Данные\\city_ru.csv";

    /**
     * Загрузка данных о городах в массив
     *
     * @return массив с данными о городах
     */
    public static List<City> getCitiesFromFile() {
        List<City> cities = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(PATH))){
            while(sc.hasNext()){
                String line = sc.nextLine();
                cities.add(toCityFromString(line));
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return cities;
    }

    /**
     * Вывод в консоль списка городов
     *
     * @param cities список городов
     */
    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

    /**
     * Разбор строки с данными о городе
     *
     * @param line строка с данными
     * @return {@link City}
     */
    public static City toCityFromString(String line){

        String[] arr = new String[5];
        StringBuilder bf = new StringBuilder(line);
        for(int i = 0; i < 5; i++){
            arr[i] = bf.substring(bf.lastIndexOf(";") + 1);
            bf.delete(bf.lastIndexOf(";"), bf.length());

        }

        return new City(arr[4], arr[3], arr[2], Integer.parseInt(arr[1]), arr[0]);


    }


}
