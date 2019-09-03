
/*
Du skall skriva ett program som konsumerar / använder sig av den REST WS som finns på
http://date.jsontest.com/ och som returnerar datum och klockslag. Tjänsten finns beskriven på
https://www.jsontest.com/.

Skriv ett Java-program som anropar denna WS och skriva ut datum och tid som det får i svaret. Ta
med förklarande kodkommentarer som visar vad programmet gör. (Du får använda vilken teknik du
vill för implementationen.)
 */

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UppgiftA {
    public static void main(String[] args) {
        String urlString = "http://date.jsontest.com/"; //Hämtar strängen
        URL url;   //Deklarerar url metoden
        try {
            url = new URL(urlString);  //initierar http strängen till url variabeln
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  //Öppnar instansens// (tunneln) med url
            connection.setRequestMethod("GET");
            print(connection);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void print(HttpURLConnection connection){
        if(connection != null){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                while((input = br.readLine()) != null){
                    System.out.println(input);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

