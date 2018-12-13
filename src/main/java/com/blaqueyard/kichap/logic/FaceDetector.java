//package com.blaqueyard.kichap.logic;
//
//
///**
// * Created by admin on 11/10/18.
// */
//
///**
// * Fredrick Oluoch
// * http://www.blaqueyard.com
// * 0720106420 | 0722508906
// * email: menty44@gmail.com
// */
//
//import org.opencv.core.*;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;
//
//public class FaceDetector
//{
//    public static void main(String[] args)
//    {
//
//        // For proper execution of native libraries
//        // Core.NATIVE_LIBRARY_NAME must be loaded before
//        // calling any of the opencv methods
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // Face detector creation by loading source cascade xml file
//        // using CascadeClassifier.
//        // the file can be downloade from
//        // https://github.com/opencv/opencv/blob/master/data/haarcascades/
//        // haarcascade_frontalface_alt.xml
//        // and must be placed in same directory of the source java file
//        CascadeClassifier faceDetector = new CascadeClassifier();
//        faceDetector.load("haarcascade_frontalface_alt.xml");
//
//        // Input image
//        Mat image = Imgcodecs.imread("face.jpg");
//
//        // Detecting faces
//        MatOfRect faceDetections = new MatOfRect();
//        faceDetector.detectMultiScale(image, faceDetections);
//
//        // Creating a rectangular box showing faces detected
//        for (Rect rect : faceDetections.toArray())
//        {
//            Imgproc.rectangle(image, new Point(rect.x, rect.y),
//                    new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0));
//        }
//
//        // Saving the output image
//        String filename = "Ouput.jpg";
//        Imgcodecs.imwrite("\\"+filename, image);
//    }
//}
