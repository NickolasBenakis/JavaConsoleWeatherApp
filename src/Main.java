import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int userChoise;
        String city;
        Scanner input = new Scanner(System.in);

        userChoise = menu();

        if (userChoise == 1) {
            System.out.print("City :");
            city = input.next();
            getWeatherForCity(city);


        } else if (userChoise == 2) {
            System.out.println("\nMade by Nickolas Benakis\nBorn in 1993");
            System.out.println("******************************");
            System.out.println("\n");
            do {
                menu();
            }while (menu() ==2);



        } else if (userChoise == 3) {
            System.out.println("Bye Bye ");

        } else {
            System.out.println("Error");
        }


    }

    public static int menu() {
        int selection;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to my Application");
        System.out.println("-------------------------");
        System.out.println("1 - Show Weather ");
        System.out.println("2 - About me");
        System.out.println("3 - Exit");
        System.out.print("\nPress : ");

        selection = input.nextInt();

        return selection;
    }

    public static String getWeatherForCity(String city) {
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="
                    + city
                    + "&appid="
                    + "2a3c7eb60acb02a208a32f66e087284"
            );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
