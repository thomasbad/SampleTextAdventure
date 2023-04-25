package com.example.thenapofthekinginyellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class GameActivity extends AppCompatActivity {

    Button btn_North, btn_East, btn_West, btn_South, btn_Save;
    TextView tv_Chat, tv_StatusNum;

    static final int NUM_OF_ROOMS = 5;
    Room[] theDungeon;

    ArrayList<String> chatBox = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initTheDungeon();
        readDungeon();
        //displayRooms();

    }

    protected void initTheDungeon()
    {
        theDungeon = new Room[NUM_OF_ROOMS];
        for (int pos = 0; pos < NUM_OF_ROOMS; pos++)
        {
            theDungeon[pos] = new Room();
        }

    }


    public void readDungeon()
    {
        // Load the XML file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = null;
        try {
            doc = builder.parse(new InputSource(new FileInputStream(String.valueOf(R.xml.dungeon))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        // Get all the "chat" elements in the XML
        NodeList nodeList = doc.getElementsByTagName("chat");

        // Loop through the "chat" elements and store their values in the array
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            chatBox.set(i, element.getTextContent());
        }
    }


}