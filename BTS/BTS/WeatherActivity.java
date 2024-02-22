package com.example.java;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//날씨를 파싱하는 액티비티
public class WeatherActivity extends Activity {
    TextView textview, textView2;
    Document doc = null;
    LinearLayout layout = null;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        textview = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        GetXMLTask task = new GetXMLTask();
        //     task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2623074000");

        //다음 Activity로 이동
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
                startActivity(intent); //액티비티 이동

            }
        });
    }

    public void onClick(View view){
        GetXMLTask task = new GetXMLTask();
        //     task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2623074000");

    }



    //private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            //*db이름과 버전 명시
            final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Place_DB.db", null, 1); //사용할 DB 선언

            String s = "";
            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("data");
            NodeList point = doc.getElementsByTagName("item");

            Node point_node = point.item(0); //data엘리먼트 노드
            Element point_fstElmnt = (Element) point_node;
            NodeList pointList = point_fstElmnt.getElementsByTagName("category");
            //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환
            String hour="24";
            String Day ="1";
            String Day2 ="0";
            String a = "null";

            Node node = nodeList.item(5); //data엘리먼트 노드
            Element fstElmnt = (Element) node;
            //날씨 데이터를 추출

            NodeList hourList = fstElmnt.getElementsByTagName("hour");
            NodeList dayList = fstElmnt.getElementsByTagName("day");

            if(Day.equals(dayList.item(0).getChildNodes().item(0).getNodeValue())
                    || Day2.equals(dayList.item(0).getChildNodes().item(0).getNodeValue())){


                NodeList nameList  = fstElmnt.getElementsByTagName("temp");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();
                s += "온도 = "+ ((Node) nameList.item(0)).getNodeValue() +" \n";


                NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
                //<wfKor>맑음</wfKor> =====> <wfKor> 태그의 첫번째 자식노드는 TextNode 이고 TextNode의 값은 맑음
                s += "부산 날씨 = "+  websiteList.item(0).getChildNodes().item(0).getNodeValue() +"\n";


                String value= ((Node) nameList.item(0)).getNodeValue();
                String wt=websiteList.item(0).getChildNodes().item(0).getNodeValue();
                double d=Double.parseDouble(value);

                //실내인 경우
                if(d<0.0||d>27.0||(wt.equals("눈")==true)||(wt.equals("비")==true||(wt.equals("비/눈")==true)||(wt.equals("소나기")==true))){
                    a = dbHelper.InResults();
                    //String placeList[] = a.split(",");
                }
                else //실내외인 경우
                {
                    a = dbHelper.InOutResults();
                    //String placeList[] = a.split(",");
                    textView2.setText(a);
                }


            }
            Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
            intent.putExtra("a",a);
            textview.setText(s);

            super.onPostExecute(doc);

        }


    }//end inner class - GetXMLTask
}