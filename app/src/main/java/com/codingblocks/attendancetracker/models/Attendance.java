package com.codingblocks.attendancetracker.models;

import java.util.List;

/**
 * Created by HP on 02-10-2017.
 */

public class Attendance {

    String mBatchName;
    int mLectureNumber;
    String mPresentStudents,mAbsentStudents;

    public Attendance(String batchName, int lectureNumber, String presentStudents, String absentStudents){
        mBatchName = batchName;
        mLectureNumber = lectureNumber;
        mPresentStudents = presentStudents;
        mAbsentStudents = absentStudents;
    }

    public void setBatchName(String batchName){
        mBatchName = batchName;
    }

    public String getBatchName(){
        return mBatchName;
    }

    public void setLectureNumber(int lectureNumber){
        mLectureNumber = lectureNumber;
    }
     public int getLectureNumber(){
         return mLectureNumber;
     }

     public void setPresentStudents(String presentStudents){
         mPresentStudents = presentStudents;
     }

     public String getPresentStudents(){
         return mPresentStudents;
     }

    public void setAbsentStudents(String AbsentStudents){
        mPresentStudents = AbsentStudents;
    }

    public String getAbsentStudents(){
        return mAbsentStudents;
    }


}
