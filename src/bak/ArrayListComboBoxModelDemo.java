package bak;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;


public class ArrayListComboBoxModelDemo {   
 public static void main(String args[]) {   
   JFrame frame = new JFrame("ArrayListComboBoxModel");   
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
   ArrayList<Object> arrayList = new ArrayList<Object>();   
   arrayList.add("A");   
   arrayList.add("B");   
   arrayList.add("C");   
   ArrayListComboBoxModel model = new ArrayListComboBoxModel(arrayList);   
 
   arrayList.add("A");   
   arrayList.add("B");   
   arrayList.add("C");   
   JComboBox comboBox = new JComboBox(model);   
 
   frame.add(comboBox, BorderLayout.NORTH);   
   frame.setSize(300, 225);   
   frame.setVisible(true);   
 }   
}   