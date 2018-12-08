package com.example.nikumar.testapi.commonutility;

import android.util.Log;

public class CustomLogger {
    private static final String TAG = "CustomLogger";
    private static final String APP_TAG = "TestApi";
    public static final boolean VERBOSE = true;
    public static final boolean DEBUG = true;
    public static final boolean INFO = true;
    public static final boolean WARNING = true;
    public static final boolean ERROR = true;

    /**
     * Method to be used by the apps to print verbose logs from the TTS Test App
     *
     * @param aMessage
     * @return void
     *
     */
    public static void printVerbose(Object... aMessage) {
        if (VERBOSE) Log.v(APP_TAG, combineMessage(aMessage));
    }

    /**
     * Method to be used by the apps to print debug logs from the TTS Test App
     *
     * @param aMessage
     * @return void
     *
     */
    public static void printDebug(Object... aMessage) {
        if (DEBUG) Log.d(APP_TAG, combineMessage(aMessage));
    }

    /**
     * Method to be used by the apps to print info logs from the TTS Test App
     *
     * @param aMessage
     * @return void
     *
     */
    public static void printInfo(Object... aMessage) {
        if (INFO) Log.i(APP_TAG, combineMessage(aMessage));
    }

    /**
     * Method to be used by the apps to print warning logs from the TTS Test App
     *
     * @param aMessage
     * @return void
     *
     */
    public static void printWarning(Object... aMessage) {
        if (WARNING) Log.w(APP_TAG, combineMessage(aMessage));
    }

    /**
     * Method to be used by the apps to print error logs from the TTS Test App
     *
     * @param aMessage
     * @return void
     *
     */
    public static void printError(Object... aMessage) {
        if (ERROR) Log.e(APP_TAG, combineMessage(aMessage));
    }

    /**
     * Method to be used to get a single String by combining all individual messages
     * within an object array
     *
     * @param aMessage
     * @return void
     *
     */
    private static String combineMessage(Object[] aMessage) {
        String lLogMessage = "";
        for (int i=aMessage.length, j=0; i>0 && null != aMessage[j]; i--, j++) lLogMessage = lLogMessage + " " + aMessage[j].toString();
        return lLogMessage;
    }
}
