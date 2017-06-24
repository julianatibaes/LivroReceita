package com.tibaes.livroreceitasqlite.util;

import com.tibaes.livroreceitasqlite.model.Receita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana Tibães on 09/06/2017.
 * LivroReceitaSQLite
 */

public class ReceitaConverter {

    public String converteParaJSON(List<Receita> receitaList) {
        JSONStringer js = new JSONStringer();
        try {
            js.array();
            for (Receita receita : receitaList) {
                js.object();
                if(receita.getId()!=null)
                    js.key("id").value(receita.getId());
                js.key("nome").value(receita.getNome());

                js.key("nivelDificuldade").value(receita.getNivelDificuldade());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }

    public String converteParaJSON(Receita receita) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            // para aproveitar no alterar
            if(receita.getId()!=null)
                js.key("id").value(receita.getId());
            js.key("nome").value(receita.getNome());
            js.key("nivelDificuldade").value(receita.getNivelDificuldade());
            js.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }

    public Receita recebeJSON(String json){
        try{
            Receita receita = new Receita();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("");

            JSONObject objArray = array.getJSONObject(0);
            JSONObject obj = objArray.getJSONObject("receita");

            //Atribui os objetos que estão nas camadas mais altas (na primeira camada de nós)
            receita.setId(obj.getInt("id"));
            receita.setNome(obj.getString("nome"));
            receita.setNivelDificuldade((float) obj.getDouble("nivelDificuldade"));

            return receita;

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Receita> jsonToListaReceita(String json){
        try{
            List<Receita> receitaList = new ArrayList<>();

            // recebe a string com os dados do json e joga
            // no objeto do tipo JSONArray ( que trabalha com
            // o objeto que um array de json )
            JSONArray jsonArray = new JSONArray(json);

            for(int i=0; i < jsonArray.length();i++) {
                // pega o dado do objeto do array de de json na posição i
                JSONObject receitaJSON =  jsonArray.getJSONObject(i);

                Receita receita = new Receita();
                receita.setId(receitaJSON.getInt("id"));
                receita.setNome(receitaJSON.getString("nome"));
                receita.setNivelDificuldade((float) receitaJSON.getDouble("nivelDificuldade"));

                receitaList.add(receita);
            }
            return receitaList;
        }catch(JSONException e){
            // caso não consiga converter o json para a lista
            e.printStackTrace();
            return null;
        }
    }


/*

    private Receita json(String json){
        try {
            PessoaObj pessoa = new PessoaObj();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;

            JSONObject objArray = array.getJSONObject(0);

            JSONObject obj = objArray.getJSONObject("user");
            //Atribui os objetos que estão nas camadas mais altas
            pessoa.setEmail(obj.getString("email"));
            pessoa.setUsername(obj.getString("username"));
            pessoa.setSenha(obj.getString("password"));
            pessoa.setTelefone(obj.getString("phone"));
            data = new Date(obj.getLong("dob")*1000);
            pessoa.setNascimento(sdf.format(data));

            //Nome da pessoa é um objeto, instancia um novo JSONObject
            JSONObject nome = obj.getJSONObject("name");
            pessoa.setNome(nome.getString("first"));
            pessoa.setSobrenome(nome.getString("last"));

            //Endereco tambem é um Objeto
            JSONObject endereco = obj.getJSONObject("location");
            pessoa.setEndereco(endereco.getString("street"));
            pessoa.setEstado(endereco.getString("state"));
            pessoa.setCidade(endereco.getString("city"));

            //Imagem eh um objeto
            JSONObject foto = obj.getJSONObject("picture");
            pessoa.setFoto(baixarImagem(foto.getString("large")));

            return pessoa;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

   /* private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    */
}
