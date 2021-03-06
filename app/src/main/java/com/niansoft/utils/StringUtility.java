package com.niansoft.utils;

import static com.niansoft.utils.CustomLogger.printError;
import static com.niansoft.utils.CustomLogger.printVerbose;

public class StringUtility {
	private static final String TAG = "StringUtility";

	/*
	 * By default the string operations and validation will be case-insensitive
	 */
	private static boolean mCaseSensitive = false;

	public static boolean setCaseSensitivity(boolean aIsSensitive) {
		mCaseSensitive = aIsSensitive;
		return true;
	}

	/**
	 * This method converts the given string to lower case
	 * 
	 * @param aString
	 * @return
	 */
	private static String convertToLowerCase(String aString) {
		return aString.toLowerCase();
	}

	/**
	 * This method converts the given string to upper case
	 * 
	 * @param aString
	 * @return
	 */
	private static String convertToUpperCase(String aString) {
		return aString.toUpperCase();
	}

	/**
	 * This method can be used to reverse a String
	 * 
	 * @param aString - String to be reversed
	 * @return lString - reversed String
	 */
	public static String reverseString(String aString) {
		String lString = "";
		for (int index = aString.length() - 1; index >= 0; index--) {
			lString = lString + aString.charAt(index);
		}
		return lString;
	}

	/**
	 * This method can be used to check whether a given String is palindrome. A flag
	 * is used as a second parameter to specify the validation criteria to include
	 * case sensitivity
	 * 
	 * @param aString
	 * @return True if validation of Palindrome is successful
	 */
	public static boolean isPalindrome(String aString) {
		if (mCaseSensitive) {
			return aString.equals(reverseString(aString));
		} else {
			return aString.equalsIgnoreCase(reverseString(aString));
		}
	}

	/**
	 * 
	 * @param firstString
	 * @param secondString
	 * @return
	 */
	public static boolean isAnagram(String firstString, String secondString) {
		char ch;
		if (!mCaseSensitive) {
			firstString = convertToLowerCase(firstString);
			secondString = convertToLowerCase(secondString);
		}
		StringBuilder letters = new StringBuilder();
		if (firstString.length() == secondString.length()) {
			for (int i = firstString.length() - 1; i >= 0; i--) {
				ch = firstString.charAt(i);
				if (letters.toString().indexOf(ch) == -1) {
					if (countCharacters(firstString, ch) != countCharacters(secondString, ch))
						return false;
					else
						letters.append(ch);
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Count number of usage of specified character in the given string
	 * 
	 * @param aString
	 * @param ch
	 * @return
	 */
	public static int countCharacters(String aString, char ch) {
		if (!mCaseSensitive) {
			if ((int) ch >= 97)
				aString = convertToUpperCase(aString);
			else
				aString = convertToLowerCase(aString);
		}
		int count = 0;
		for (int i = aString.length() - 1; i >= 0; i--)
			if (ch == aString.charAt(i))
				count++;
		return count;
	}

	/**
	 * Count number of usage of specified substring in the given string
	 * 
	 * @param aString
	 * @param aSubString
	 * @return
	 */
	public static int countSubStrings(String aString, String aSubString) {
		int count = 0, lengthString = aString.length(), lengthSubString = aSubString.length();
		if (!mCaseSensitive) {
			aString = convertToLowerCase(aString);
			aSubString = convertToLowerCase(aSubString);
		}
		if (lengthSubString > lengthString) {
			count = -1;
		} else if (lengthSubString == lengthString) {
			if (aSubString.equals(aString))
				count = 1;
		} else {
			for (int i = 0; i < lengthString; i++) {
				for (int j = 0; i < lengthString && j < lengthSubString; i++) {
					if (aString.charAt(i) == aSubString.charAt(j)) {
						if (++j == lengthSubString) {
							count++;
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		return count;
	}

	public static String[] sortStringArray(String[] aStringArray) {
		String[] sortedArray = aStringArray.clone();
		String temp;
		int lengthArray = sortedArray.length;
		/*if (!mCaseSensitive) {
			for (int i = 0; i < lengthArray; i++)
				sortedArray[i] = convertToLowerCase(sortedArray[i]);
		}*/
		for (int i = 0, j = 0, count = 0; i < (lengthArray - 1); i++) {
			if (j < sortedArray[i].length() && j < sortedArray[i+1].length()) {
				System.out.print("\nPoint 1     ");
				if ((int) sortedArray[i].charAt(j) > (int) sortedArray[i + 1].charAt(j)) {
					System.out.print("Point 2     ");
					if (j==0) {
						System.out.print("Point 3     ");
						temp = sortedArray[i];
						sortedArray[i] = sortedArray[i + 1];
						sortedArray[i + 1] = temp;
						count++;
					} else {
						if (sortedArray[i].charAt(j-1) == sortedArray[i + 1].charAt(j-1)) {
							System.out.print("Point 4     ");
							temp = sortedArray[i];
							sortedArray[i] = sortedArray[i + 1];
							sortedArray[i + 1] = temp;
							count++;
						}
					}
				}
			}
			if (i == (lengthArray - 2)) {
				System.out.print("Point 5    ");
				if (count != 0) {
					i = -1;
					j++;
					count = 0;
				} else {
					break;
				}
			}
		}
		return sortedArray;
	}

	/**
	 * This method can be used to search a substring in an array of Strings
	 * 
	 * @param aStringArray
	 * @param aSubString
	 * @return matching substring-array
	 */
	public static String[] searchStringInArray(String[] aStringArray, String aSubString) {
		int lengthArray = aStringArray.length;
		String[] copyArray = aStringArray.clone();
		String[] matchedArray = new String[lengthArray];
		if (!mCaseSensitive) {
			for (int i = 0; i < lengthArray; i++)
				copyArray[i] = convertToLowerCase(copyArray[i]);
			aSubString = convertToLowerCase(aSubString);
		}
		for (int i = 0, j = 0; i < lengthArray; i++) {
			if (countSubStrings(copyArray[i], aSubString) > 0)
				matchedArray[j++] = aStringArray[i];
		}
		return matchedArray;
	}

    /**
     * This method can be used to fetch characters starting after the substring
     * present inside the line till it finds the delimiter
     *
     * Example:
     * subString("Name(Rohan), Contact(81939), Gender(Male)", "Contact(", ")")
     * should return a string "81939"
     *
     * @param line
     * @param subString
     * @param delimiter
     * @return String
     */
    public static String subString(String line, String subString, char delimiter) {
        printVerbose(TAG, "subString");
        int i=0, j=0;
        try {
            for (; i<(line.length()-1); i++) {
                if (line.charAt(i) == subString.charAt(j)) {
                    int count = 1;
                    i++;
                    j++;
                    while(i<line.length() && j<subString.length() && line.charAt(i) == subString.charAt(j)) {
                        count++;
                        i++;
                        j++;
                    }
                    if (count == subString.length()) {
                        StringBuilder outString = new StringBuilder();
                        while (i<line.length()) {
                            if (line.charAt(i) == delimiter) return outString.toString();
                            else outString.append(line.charAt(i));
                            i++;
                        }
                    } else {
                        j=0;
                    }
                }
            }
        } catch(Exception e) {
            printError(TAG, "Exception: " + e.getMessage());
        }
        return "Not Found";
    }

    public static String truncateIntegers(String line) {
        int i=0;
        try {
            for (; i<(line.length()-1); i++) {
                if (!isInt(line.charAt(i))) {
                    break;
                }
            }
            return line.substring(i);
        } catch(Exception e) {
            printError(TAG, "Exception: " + e.getMessage());
        }
        return "Not Found";
    }

    private static boolean isInt(char ch) {
        try {
            Integer.parseInt(String.valueOf(ch));
        } catch (NumberFormatException e) {
            printVerbose(TAG, "NumberFormatException: " + e.getMessage());
            return false;
        }
        return true;
    }
}
