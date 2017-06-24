package com.tibaes.livroreceitasqlite.util;

import android.support.annotation.Nullable;

import com.tibaes.livroreceitasqlite.model.Receita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Juliana Tibães on 09/06/2017.
 * LivroReceitaSQLite
 * Classe para conectar com o meu webservice,
 * fazendo todos os médotos CRUD
 */

public class WebClient {

    public void insere(String json) {
        String endereco = Util.IP+":8080/WebServiceRest/rest/service/cadastrar";
        setJSONToAPI(json, endereco);
    }

    public void atualiza(String json) {
        String endereco = Util.IP+":8080/WebServiceRest/rest/service/alterar";
        setJSONToAPI(json, endereco);
    }

    public void exclui(Integer id) {
        String endereco = Util.IP+":8080/WebServiceRest/rest/service/excluir/"+id;
        deleteFromAPI(endereco);
    }

    public String getTodasReceitas(){
        String endereco = Util.IP+":8080/WebServiceRest/rest/service/todasReceitas";
        return  getJSONFromAPI(endereco);
    }

    public String buscaPorId(Integer id) {
        String endereco = Util.IP+":8080/WebServiceRest/rest/service/getReceita/"+id;
        return getJSONFromAPI(endereco);
    }

    @Nullable
    private String setJSONToAPI(String json, String _url) {
        try {
            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getJSONFromAPI(String _url) {
        String retorno = "";

        try {
            URL url = new URL(_url); // recebe o url que quero conectar
            int codigoResposta; // código de resposta para saber o retorno da conexao com o endereço
            HttpURLConnection conexao; // objeto que irá de fato conectar com o endereço
            InputStream inputStream;

            conexao = (HttpURLConnection) url.openConnection();

            // setar os parâmetros do protocolo http
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conexao.getInputStream();
            } else {
                inputStream = conexao.getErrorStream();
            }

            // converte a stream retornada para uma String com os
            // dados do json
            retorno = converterInputStreamToString(inputStream);
            inputStream.close();
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    private static String converterInputStreamToString(InputStream inputStream){

        StringBuffer buffer = new StringBuffer(); // recebe os dados externos
        try{
            BufferedReader bufferedReader; // ler os dados do buffer
            String linha; // construir as linhas do json

            bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            /** para cada dado do bufferReader,
             * que recebeu a stream de dados
             * ele adiciona na linha e linha no buffer
             */
            while((linha = bufferedReader.readLine())!=null){
                buffer.append(linha);
            }

            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

    private String deleteFromAPI(String _url)  {
        try {
            URL url = new URL(_url);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conexao.setRequestMethod("DELETE");

            System.out.println(conexao.getResponseCode());
            conexao.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }





    /*public String buscaTodos() {
        String endereco = Util.IP+":8080/WebServiceRest/rest/service/todasReceitas";
        return getJSONFromAPI(endereco);
    }*/






}
