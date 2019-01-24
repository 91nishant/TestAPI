/*
 *
 *  * Copyright (c) 2017 ZIH Corp and/or its affiliates. All rights reserved
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.nikumar.testapi.googlemap;

import java.io.Serializable;

/**
 * A class used to represent an individual setting from a diagdameon module
 */

public class Setting implements Serializable {

    private String mName;
    private String mType;
    private String mValue;
    private String[] mList;

    public Setting(String name, String type, String[] list){
        mName = name;
        mType = type;
        mList=list;
    }

    public String getName(){
        return mName;
    }

    public String getType(){
        return mType;
    }

    public String getValue(){
        return mValue;
    }

    public String[] getListOptions(){
        return mList;
    }

    public boolean setValue(int value){

        /*if(!(mType.equals(DiagdaemonController.MODULE_CONFIG_TYPE_INT) || mType.equals(DiagdaemonController.MODULE_CONFIG_TYPE_LIST))){
            return false;
        }*/

        mValue = value+"";

        return true;
    }

    public boolean setValue(String value){
        /*if(!mType.equals(DiagdaemonController.MODULE_CONFIG_TYPE_STRING)){
            return false;
        }*/
        if(value==null){
            mValue="";
        }
        else {
            mValue = value;
        }
        return true;
    }

    public boolean setValue(boolean value){
        /*if(!mType.equals(DiagdaemonController.MODULE_CONFIG_TYPE_BOOLEAN)){
            return false;
        }*/

        if(value){
            mValue="true";
        }
        else{
            mValue="false";
        }
        return true;
    }
}
