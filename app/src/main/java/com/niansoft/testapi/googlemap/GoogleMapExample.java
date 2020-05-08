package com.niansoft.testapi.googlemap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.niansoft.testapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static com.niansoft.utils.CustomLogger.printError;
import static com.niansoft.utils.CustomLogger.printVerbose;


public class GoogleMapExample extends AppCompatActivity {
    private static final String TAG = "GoogleMapExample";

    Button mPostOnMap;

    public final static String MODULE_CONFIG_TYPE_INT = "int";

    /**
     * String used to denote a string type configuration
     */
    public final static String MODULE_CONFIG_TYPE_STRING = "string";

    /**
     * String used to denote a boolean type configuration
     */
    public final static String MODULE_CONFIG_TYPE_BOOLEAN = "boolean";

    /**
     * String used to denote a list type configuration
     */
    public final static String MODULE_CONFIG_TYPE_LIST = "list";

    private final static String JSON_NAME = "name";
    private final static String JSON_TYPE = "type";
    private final static String JSON_VALUE = "value";
    private final static String JSON_LIST = "list";
    private final static String JSON_CONFIG = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_example);
        mPostOnMap = findViewById(R.id.post_on_map);
        mPostOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.post_on_map));
                postOnMap();
                // Temporary code, just for test purpose
                extraWork();
            }

            private void extraWork() {
                Module[] modules = getSettings();
                printVerbose(TAG, "After");
                print(modules);
            }
        });
    }

    private void postOnMap() {
        double latitude  = 23.369612;
        double longitude =  85.862112;
        printVerbose(TAG, "postOnMap, latitude :", latitude, ", longitude :", longitude);
        Intent nt = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + latitude + "," + longitude));
        startActivity(nt);
    }

    private static void print(Module[] modules) {
        if (null != modules) {
            for (int i = 0; i < modules.length; i++) {
                if (null != modules[i])
                    printVerbose(TAG, "index[" + i + "] : " + modules[i].getName());
                else printVerbose(TAG, "index[" + i + "] : " + modules[i]);
            }
        } else {
            printVerbose(TAG, "Empty list");
        }
    }

    public Module[] getSettings() {
        //String message = getRawConfig();
        printVerbose(TAG, "getSettings");
        String message = "{\"RxLogger Settings\": {\"settings\": [{\"name\": \"Enable notifications\", \"type\": \"boolean\", \"value\": \"true\", \"list\": []}]}," +
                " \"QxdmModule\": {\"settings\": [{\"name\": \"Enable Module\", \"type\": \"boolean\", \"value\": \"false\", \"list\": []}]}," +
                " \"LTS\": {\"settings\": [{\"name\": \"Enable notifications\", \"type\": \"boolean\", \"value\": \"true\", \"list\": []}]}," +
                " \"Kernel\": {\"settings\": [{\"name\": \"Enable notifications\", \"type\": \"boolean\", \"value\": \"true\", \"list\": []}]}," +
                " \"Logcat\": {\"settings\": [{\"name\": \"Enable notifications\", \"type\": \"boolean\", \"value\": \"true\", \"list\": []}]}," +
                " \"ANR\": {\"settings\": [{\"name\": \"Enable notifications\", \"type\": \"boolean\", \"value\": \"true\", \"list\": []}]}}";
        if(message != null) {
            return makeSettings(message);
        }
        return null;
    }

    /**
     * @deprecated
     */
    public static Module[] makeSettings(String message){
        printVerbose(TAG, "makeSettings");
        Module[] modules = null;
        Setting[] settings;
        Setting curSet;
        String[] optionList;

        try {
            JSONArray module;
            JSONArray list;

            JSONObject root = new JSONObject(message);
            modules = new Module[root.length()];

            int x =0;
            String curModule;
            for(Iterator<String> i = root.keys(); i.hasNext(); x++) {
                curModule = i.next();

                module = root.getJSONObject(curModule).getJSONArray(JSON_CONFIG);
                modules[x] = new Module(curModule);

                settings = new Setting[module.length()];
                modules[x].setSettingsList(settings);

                for(int y = 0; y < module.length(); y++) {
                    list = module.getJSONObject(y).getJSONArray(JSON_LIST);
                    if(list!=null) {
                        optionList = new String[list.length()];
                        for(int z = 0; z < optionList.length; z++) {
                            optionList[z] = list.getString(z);
                        }
                    }
                    else{
                        optionList = new String[0];
                    }

                    curSet = new Setting(module.getJSONObject(y).getString(JSON_NAME), module.getJSONObject(y).getString(JSON_TYPE),optionList);
                    settings[y]=curSet;

                    if(curSet.getType().equals(MODULE_CONFIG_TYPE_INT) || curSet.getType().equals(MODULE_CONFIG_TYPE_LIST)) {
                        curSet.setValue(Integer.parseInt(module.getJSONObject(y).getString(JSON_VALUE)));
                    }
                    else if(curSet.getType().equals(MODULE_CONFIG_TYPE_STRING)) {
                        curSet.setValue(module.getJSONObject(y).getString(JSON_VALUE));
                    }
                    else if(curSet.getType().equals(MODULE_CONFIG_TYPE_BOOLEAN)) {
                        if( module.getJSONObject(y).getString(JSON_VALUE).equals("true")){
                            curSet.setValue(true);
                        }
                        else{
                            curSet.setValue(false);
                        }
                    }
                }
            }
            printVerbose(TAG, "Before");
            print(modules);
            sortModules(modules, 1);
        } catch(JSONException e) {
            printError(TAG,"Error Parsing json string",e);
            modules=null;
        } catch (Exception e) {
            printError(TAG,"Exception",e);
        }

        return modules;
    }

    /**
     * Sort the Module array alphabetically based on their names, starting from index value as specified by startIndex
     * @param modules - Module array to be sorted
     * @param startIndex - Starting index i.e. indexes before this will be left untouched
     */
    private static void sortModules(Module[] modules, int startIndex) {
        if (null != modules && startIndex >= 0) {
            Map<String, Module> moduleMap = new TreeMap<>();
            for (int index = startIndex; index < modules.length; index++){
                if (null != modules[index]) moduleMap.put(modules[index].getName(), modules[index]);
            }
            Iterator it = moduleMap.entrySet().iterator();
            for (int index = startIndex; it.hasNext() && index < modules.length; index++) {
                Map.Entry pair = (Map.Entry)it.next();
                modules[index] = (Module)pair.getValue();
            }
        } else {
            printVerbose(TAG, "Empty list");
        }
    }

}
