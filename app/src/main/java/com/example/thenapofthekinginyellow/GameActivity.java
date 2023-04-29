package com.example.thenapofthekinginyellow;


import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button btn_North, btn_East, btn_West, btn_South, btn_Save;
    TextView tv_Chat, tv_StatusNum;

    //player default status
    int currentPos = 0;
    int sanity = 100;
    boolean silverCoin = false;
    boolean stick = false;
    boolean key = false;

    static final int NUM_OF_ROOMS = 15;
    Room[] thedungeon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initTheDungeon();
        readXMLFile();
        initBtn();
        displayRooms();
        btnTrigger();
        specialRoomTrigger();
        madEnd();
    }

    protected void initTheDungeon()
    {
        thedungeon = new Room[NUM_OF_ROOMS];
        for (int pos = 0; pos < NUM_OF_ROOMS; pos++)
        {
            thedungeon[pos] = new Room();
        }

    }

    public void readXMLFile()
    {
        int pos = 0; // May be use this variable, to keep track of what position of the array of Room Objects.
        try
        {
            int room_count = 0;

            XmlResourceParser xpp = getResources().getXml(R.xml.dungeon);
            xpp.next();
            int eventType = xpp.getEventType();
            String elemtext = null;

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    String elemName = xpp.getName();
                    if (elemName.equals("dungeon"))
                    {
                        String titleAttr = xpp.getAttributeValue(null,"title");
                        String authorAttr = xpp.getAttributeValue(null,"author");

                    } // if (elemName.equals("dungeon"))

                    if (elemName.equals("room"))
                    {
                        room_count = room_count + 1;
                    }

                    if (elemName.equals("north"))
                    {
                        elemtext = "north";
                    }

                    if (elemName.equals("east"))
                    {
                        elemtext = "east";
                    }

                    if (elemName.equals("south"))
                    {
                        elemtext = "south";
                    }

                    if (elemName.equals("west"))
                    {
                        elemtext = "west";
                    }

                    if (elemName.equals("description"))
                    {
                        elemtext = "description";
                    }

                } // if (eventType == XmlPullParser.START_TAG)
                // You will need to add code in this section to read each element of the XML file
                // And then store the value in the current Room Object.
                // NOTE: This method initTheDungeon() creates and array of Room Objects, ready to be populated!
                // As you can see at the moment the data/text is displayed in the LogCat Window
                // Hint: xpp.getText()
                else if (eventType == XmlPullParser.TEXT)
                {
                    if (elemtext.equals("north"))
                    {
                        Log.w("ROOM", "north = " + xpp.getText());
                        thedungeon[room_count-1].setNorth( Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("east"))
                    {
                        Log.w("ROOM", "east = " + xpp.getText());
                        thedungeon[room_count-1].setEast(Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("south"))
                    {
                        Log.w("ROOM", "south = " + xpp.getText());
                        thedungeon[room_count-1].setSouth(Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("west"))
                    {
                        Log.w("ROOM", "west = " + xpp.getText());
                        thedungeon[room_count-1].setWest(Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("description"))
                    {
                        Log.w("ROOM", "description = " + xpp.getText());
                        thedungeon[room_count-1].setDescription( xpp.getText() );
                    }
                } // else if (eventType == XmlPullParser.TEXT)

                eventType = xpp.next();

            } // while (eventType != XmlPullParser.END_DOCUMENT)
        } // try
        catch (XmlPullParserException e)
        {
            Log.w("XML Parser Exception", "Error in XML Parsing" + e.getMessage());
        }
        catch (IOException e)
        {
            Log.w("IO Exception", "Reading Error in XML" + e.getMessage());
        }
    } // public void readXMLFile()

    protected void initBtn(){
        if (thedungeon[currentPos].getEast() >= 0){
            btn_East.setVisibility(View.VISIBLE);
        }else {
            btn_East.setVisibility(View.GONE);
        }

        if (thedungeon[currentPos].getNorth() >= 0){
            btn_North.setVisibility(View.VISIBLE);
        }else{
            btn_North.setVisibility(View.GONE);
        }

        if (thedungeon[currentPos].getWest() >= 0){
            btn_West.setVisibility(View.VISIBLE);
        }else{
            btn_West.setVisibility(View.GONE);
        }

        if (thedungeon[currentPos].getSouth() >= 0){
            btn_South.setVisibility(View.VISIBLE);
        }else {
            btn_South.setVisibility((View.GONE));
        }
    }

    public void displayRooms() {
        tv_Chat.setText(thedungeon[currentPos].getDescription());
        tv_StatusNum.setText(sanity);
    }

    public void btnTrigger(){
        btn_North.setOnClickListener(new View.OnClickListener() {
            int newPos = 0;
            @Override
            public void onClick(View v) {
                newPos = thedungeon[currentPos].getNorth();
                currentPos = newPos;
                displayRooms();
            }
        });

        btn_East.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = thedungeon[currentPos].getEast();
                currentPos = newPos;
                displayRooms();
            }
        });

        btn_West.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = thedungeon[currentPos].getWest();
                currentPos = newPos;
                displayRooms();
            }
        });

        btn_South.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = thedungeon[currentPos].getSouth();
                currentPos = newPos;
                displayRooms();
            }
        });
    }

    public void specialRoomTrigger(){
        if (currentPos == 2 || currentPos == 5 || currentPos == 10 || currentPos == 11){
            int hurtCount = new Random().nextInt(21);
            sanity = sanity - hurtCount;
            tv_StatusNum.setText(sanity);
        } else if (currentPos == 3 || currentPos == 8){
            int healCount = new Random().nextInt(21);
            sanity = sanity + healCount;
            tv_StatusNum.setText(sanity);
        } else if (currentPos == 9){
            silverCoin = true;
        } else if (currentPos == 12){
            key = true;
        } else if (currentPos == 14){
            stick = true;
        } else if (currentPos == 13) {
            if (silverCoin == true && key == true && stick == true){
                String winMsg = "You found you wake up on the bed, sweating all your back.\nYou can't really remember what is happened, but you glad that you have excape from the nightmare, and no one ever does it.\n\nCongratulations! You have Win the Game!";
                btn_Save.setVisibility(View.GONE);
                btn_South.setVisibility(View.GONE);
                btn_West.setVisibility(View.GONE);
                btn_East.setVisibility(View.GONE);
                btn_North.setVisibility(View.GONE);
                tv_Chat.setText(winMsg);
            } else {
                String badEnd = "You push the door behind that unspeakable darkness thing, and you found that you have come back to the first place, all your memory is gone, you don't remember what your have just done, the door you just pass though is also disappeared, you know you have trapped in a loop and you will soon forget about this too, which have drive you mad completely, lost in here, forever...\n\n Game Over: Try harder traveller, you must have miss something";
                btn_Save.setVisibility(View.GONE);
                btn_South.setVisibility(View.GONE);
                btn_West.setVisibility(View.GONE);
                btn_East.setVisibility(View.GONE);
                btn_North.setVisibility(View.GONE);
                tv_Chat.setText(badEnd);
            }
        }
    }

    protected void madEnd(){
        if (sanity <= 0){
            String loseMsg = "You can't hold it anymore, the things happens here make no sense, you've also being mad, join to the crown and worship the one you shouldn't resist\n\n GAME OVER";
            btn_Save.setVisibility(View.GONE);
            btn_South.setVisibility(View.GONE);
            btn_West.setVisibility(View.GONE);
            btn_East.setVisibility(View.GONE);
            btn_North.setVisibility(View.GONE);
            tv_Chat.setText(loseMsg);
        }
    }

}