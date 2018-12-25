import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Map;
import java.lang.String;

import org.json.JSONObject;


public class Ergasia {

    public static void main(String[] args){
        menu();
    }

    // menu
    public static void menu() {
        int selection;
        String city;
        boolean control = true;
        Scanner input = new Scanner(System.in);

        while (control) {
            System.out.println("\nWelcome to my Application");
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
                    if (found) {
                        city = city.replace("\\s", "%20");
                    }
                    //End String Validation
                    System.out.println(getWeatherForCity(city));
                    break;
                case 2:
                    System.out.println("***********\n" +
                            "Application Info:\n" +
                            "This is an Amazing Application\n" +
                            "Made By Nickolas Benakis\n" +
                            ":)" + "\n***********");
                    break;
                case 3:
                    System.out.println("Bye Bye :)");
                    control = false;
                    break;
                default:
                    System.out.println("Invalid option, Try again please");
            }
        }


    }


    // get Weather
    public static String getWeatherForCity(String city) {
        String name, country, res;
        String description;
        double temp, windSpeed;
        int tempCelcious, humidity;
        BufferedReader rd = null;
        String inputLine = null;


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
            StringBuffer response = new StringBuffer();
            while ((inputLine = rd.readLine()) != null) {
                response.append(inputLine + '\n');
            }
            //rd.close();

            //Read Json Response
            JSONObject myResponse = new JSONObject(response.toString());
            name = myResponse.getString("name");
            country = myResponse.getJSONObject("sys").getString("country");
            temp = myResponse.getJSONObject("main").getDouble("temp") ;
            tempCelcious = (int) Math.floor(temp -273.15);
            humidity = (int) myResponse.getJSONObject("main").getDouble("humidity");
            windSpeed = myResponse.getJSONObject("wind").getDouble("speed");
            description = myResponse.getJSONArray("weather").getJSONObject(0).getString("description");
//            System.out.println("Description : "+ description);
//            System.out.println("City : " + name);
//            System.out.println("Country : " + country);
//            System.out.println("temp : " + temp);
//            System.out.println("Humidity : " + humidity);
//            System.out.println("Wind speed : " + windSpeed);
            String finalRes ="/////////////////////\n"+"*****"+"Search Results For "+city+" \n*****Description : "
                    +description+"\n*****Country : "
                    +country+"\n*****Temp : "
                    +tempCelcious+" Celcious \n*****Humidity : "
                    +humidity+" % \n*****WindSpeed : "
                    +windSpeed+" mph"
                    +"\n/////////////////////";
            return finalRes;

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Invalid City");

        } catch (java.net.ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("OPA re");
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}
