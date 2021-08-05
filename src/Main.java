
public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CityUtils.getCitiesFromFile();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(elapsed);
    }

    private static double getTime() {
        for (int i = 0; i < 20; i ++) { //прогрев JVM
            CityUtils.getCitiesFromFile();
        }
        int count = 1000; //первоначальное кол-во повтора выполнения testMethod

        while(true) {
            long begin =  System.nanoTime();

            for (int i = 0; i < count; i ++)
                CityUtils.getCitiesFromFile();

            long end = System.nanoTime();

            if ((end - begin) < 1000000000) { //Прогон тестов пока суммарное выполнения count раз
                count *= 100000;              //testMethod`a не будет равно несколько секунд
                continue;
            }

            return (double)(end - begin) / count;
        }
    }
}
