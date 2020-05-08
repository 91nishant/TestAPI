/*
 *
 *  * Copyright (c) 2018 ZIH Corp and/or its affiliates. All rights reserved
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

package com.niansoft.testapi.googlemap;

import java.io.Serializable;

/**
 * A class used to represent a diagdaemon module's settings.
 */

public class Module implements Serializable {

    private String mName;
    private Setting[] settings;

    public Module(String name){
        mName=name;
    }

    public void setSettingsList(Setting[] set){
        settings = set;
    }

    public String getName() {
        return mName;
    }

    public Setting[] getSettings(){
        return settings;
    }

}
