/*
    Dice.java
    Models the dice used in the game.
    Note this Dice model is from zybooks 4.2

    Casey Hird
    C12932552
    crhird@clemson.edu
 */

package com.zybooks.hirdhw2;

import java.util.Random;

// Dice class from zybooks 4.2
public class Dice {
    public static int LARGEST_NUM = 6;
    public static int SMALLEST_NUM = 1;

    private int mNumber = SMALLEST_NUM;
    private int mImageId;
    private Random mRandomGenerator;

    /*
        Dice constructor. Creates a dice object on number "number".
        Pre: number is a number in 1-6
        Post: a dice is created showing "number"
     */
    public Dice(int number) {
        setNumber(number);
        mRandomGenerator = new Random();
    }

    /*
        Returns the current Dice number.
        Pre: Dice has been created.
        Post: mNumber is returned
     */
    public int getNumber() {
        return mNumber;
    }

    /*
        Sets the Dice number.
        Pre: Number should be 1-6.
        Post: is number is in the specified range, sets mNumber=number
        and sets the appropriate drawable image for the number.
     */
    public void setNumber(int number) {
        if (number >= SMALLEST_NUM && number <= LARGEST_NUM) {
            mNumber = number;
            switch (number) {
                case 1:
                    mImageId = R.drawable.dice_1;
                    break;
                case 2:
                    mImageId = R.drawable.dice_2;
                    break;
                case 3:
                    mImageId = R.drawable.dice_3;
                    break;
                case 4:
                    mImageId = R.drawable.dice_4;
                    break;
                case 5:
                    mImageId = R.drawable.dice_5;
                    break;
                case 6:
                    mImageId = R.drawable.dice_6;
                    break;
            }
        }
    }

    /*
        Returns the current image id.
        Pre: Dice has been created.
        Post: mImageId is returned
     */
    public int getImageId() {
        return mImageId;
    }

    /*
        Rolls the Dice.
        Pre: Dice has been created.
        Post: mNumber has a random value in its specified range.
     */
    public void roll() {
        setNumber(mRandomGenerator.nextInt(LARGEST_NUM) + 1);
    }
}
