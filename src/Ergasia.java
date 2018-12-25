import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ergasia {





    // menu
    public static void menu() {
        int selection;
        String city;
        boolean control = true;
        Scanner input = new Scanner(System.in);

        while (control) {
            System.out.println("Welcome to my Application");
            System.out.println("-------------------------");
            System.out.println("1 - Show Weather ");
            System.out.println("2 - About me");
            System.out.println("3 - Exit");
            System.out.print("\nPress : ");


            selection = input.nextInt();
            input.nextLine();

            switch (selection) {
                case 1:
                    System.out.print("Enter the City: ");
                    city = input.next();
                    input.nextLine();
                    //String Validation
                    Pattern pattern = Pattern.compile("\\s");
                    Matcher matcher = pattern.matcher(city);
                    boolean found = matcher.find();
                    if(found){
                        city = city.replace("\\s","%20");
                    }
                    //End String Validation
                    System.out.println(getWeatherForCity(city));
                    break;
                case 2:
                    System.out.println("***********\n" +
                            "Application Info:\n" +
                            "This is an Amazing Application\n" +
                            "Made By Nickolas Benakis\n" +
                            ":)"+"\n***********");
                    break;
                case 3:
                    System.out.println("Bye Bye :)");
                    control = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }


    }


    // get Weather
    public static String getWeatherForCity(String city) {
        //  String name, country, description, res;
        //  Float temp,humidity,windSpeed;
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;



        try {
            // setting Url
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="
                    + city
                    + "&appid="
                    + "c7eb6d72320feed6f49470cab2537dd6"
            );
            // setting Connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();





            // Read Response
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }

            // ftiaxnw to output na einai filiko gia ton user
////            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(new );

            return sb.toString();

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Invalid City");

        } catch (java.net.ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PatternSyntaxException e){
            e.printStackTrace();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
        return "";
    }

}
