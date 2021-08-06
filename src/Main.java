import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        getCountCitiesInRegions(CityUtils.getCitiesFromFile()).forEach((k, v) -> System.out.println(k + " - " + v));
    }

    /**
     * Подсчет количества городов в разрезе регионов
     *
     * @param cities список городов
     * @return карта, где ключ регион, а значение количество городов
     */
    public static Map<String, Long> getCountCitiesInRegions(List<City> cities){
        return cities.stream()
                .collect(groupingBy(City::getRegion, mapping(City::getName, counting())));
    }

    /**
     * Сортировка списка городов по наименованию в алфавитном порядке
     *
     * @param cities список городов
     * @return  отсортированный массив с данными о городах
     */
    public static List<City> sortByName(List<City> cities){
        return cities.stream()
                .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
     *
     * @param cities список городов
     * @return отсортированный массив с данными о городах
     */
    public static List<City> sortByDistrictAndName(List<City> cities){
        return cities.stream()
                .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Преобразование списка городов список городов в массив и путем
     * перебора массива найти индекс элемента и значение с наибольшим количеством жителей города
     *
     * @param cities список городов
     */
    public static void printMaxAndIndex(List<City> cities){
        City[] arrCities =  cities.toArray(new City[cities.size()]);
        int maxIndex = 0;
        int maxValue = arrCities[0].getPopulation();

        for(int i = 1; i < cities.size(); i++){
            if(arrCities[i].getPopulation() > maxValue){
                maxValue = arrCities[i].getPopulation();
                maxIndex = i;
            }
        }

        System.out.println("[" + maxIndex + "] = " + maxValue);
    }


}
