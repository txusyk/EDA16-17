/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * @author Josu
 */
public class EDA1516 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        Object[] options = {"GUI based", "Console based"};

        //int n = JOptionPane.showOptionDialog(null,"Choose what launcher do you want to use", "EDA16-17 Selection Menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1])

        System.out.println("\t\t***** Welcome to EDA16/17 project *****\n");

        System.out.println("\tSelect (1) for gui based program launch (still in development, less customizable)");
        System.out.println("\tSelect (2) for terminal based program launch (finished and more customizable experience");

        int n = Teclado.getMiTeclado().recogerInt();

        if (n == 1) {

            JFrame frame = JMenu.getMyJMenu();

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();         //En dim guardamos el tamaño de la pantalla donde se esta ejecutando el programa

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fijamos que la operacion por defecto al cerrar es salir
            frame.pack();
            frame.isAlwaysOnTop();
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);         //Fijamos por defecto que la ventana siempre aparezca en el centro
            frame.setVisible(true); //hacemos el frame visible
            frame.setResizable(false);
        } else {
            TerminalGUI.main();
        }
    }

}
