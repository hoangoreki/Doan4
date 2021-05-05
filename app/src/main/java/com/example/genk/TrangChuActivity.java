package com.example.genk;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrangChuActivity extends AppCompatActivity {
    ListView lvTieude;
    Customadapter customadapter;
    ArrayList<Docbao> mangdocbao, arrayLink;
    EditText edtTimKiem;
    ImageButton refesh, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        edtTimKiem =  findViewById(R.id.edtTimKiem);
        refesh =  findViewById(R.id.refesh);
        back= findViewById(R.id.back);
        setClik();

        lvTieude =(ListView)findViewById(R.id.listviewTieuDe);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(TrangChuActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(TrangChuActivity.this, TrangChuActivity.class);
                startActivity(it);
            }
        });
        mangdocbao = new ArrayList<Docbao>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Readdata().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");
            }
        });
        lvTieude.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TrangChuActivity.this,webview.class);
                intent.putExtra("link",mangdocbao.get(position).link);
                startActivity(intent);
            }
        });
    }

    public void refresh(View view){          //refresh is onClick name given to the button
        onRestart();
    }

    @Override
    protected void onRestart() {

        // TODO Auto-generated method stub
        super.onRestart();
        Intent i = new Intent(TrangChuActivity.this,TrangChuActivity.class);  //your class
        startActivity(i);
        finish();

    }
    private void setClik() {


        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
                customadapter.sortTintuc(s);

            }
        });
    }

    class Readdata extends AsyncTask<String, Integer, String> {

            @Override
            protected String doInBackground(String... params) {
                return docNoiDung_Tu_URL(params[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                XMLDOMParser parser = new XMLDOMParser();
                Document document = parser.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                NodeList nodeListdescription= document.getElementsByTagName("description");
                String hinhanh="";
                String title ="";
                String link ="";
                for (int i =0; i<nodeList.getLength();i++){
                    String cdata = nodeListdescription.item(i+1).getTextContent();
                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher = p.matcher(cdata);
                    if(matcher.find()){
                        hinhanh = matcher.group(1);
                    }
                    Element element = (Element) nodeList.item(i);
                    title = parser.getValue(element,"title");
                    link = parser.getValue(element,"link");
                    mangdocbao.add(new Docbao(title,link,hinhanh));


                }
                customadapter = new Customadapter(TrangChuActivity.this, android.R.layout.simple_list_item_1,mangdocbao);
                lvTieude.setAdapter(customadapter);
                super.onPostExecute(s);
            }
        }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}