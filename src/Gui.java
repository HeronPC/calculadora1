import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class Gui extends JFrame {

    JLabel display;
    int nBt = 17;
    JButton botones[] = new JButton[nBt];
    String textoBotones[] = {"Limpiar","7","8","9","/","4","5","6","*","1","2","3","-","=","0",".","+"};
    int xBt[] = {15, 15, 80, 145, 210, 15, 80, 145, 210, 15, 80, 145, 210, 15, 80, 145, 210};
    int yBt[] = {90, 155, 155, 155, 155, 220, 220, 220, 220, 285, 285, 285, 285, 350, 350, 350, 350};
    int numBt[] = {14, 9, 10, 11, 5, 6, 7, 1, 2, 3};
    int[]  operacionesBotones = {16, 12, 8, 4};
    int anchoBt = 50;
    int altoBt = 50;
    boolean newnum = true;
    boolean punto = false;
    double num1 = 0;
    double num2 = 0;
    double resultado = 0;
    String operacion = "";

    public Gui(){

        Display();
        Botones();
        Pantalla();
        Numeros();
        Decimales();
        Operaciones();
        Resultado();
        Limpiar();

    }

    private void Display(){

        display = new JLabel("");
        display.setBounds(15, 15, 245, 60);
        display.setOpaque(true);
        display.setBackground(Color.LIGHT_GRAY);
        display.setForeground(Color.BLACK);
        display.setFont(new Font("Arial",6, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display);
    }

    private void Botones(){

        for (int i = 0; i < nBt; i++){
            botones[i] = new JButton(textoBotones[i]);
            int size = (i == 0) ? 24 : 16;
            int ancho = (i == 0) ? 245 : anchoBt;
            botones[i].setBounds(xBt[i], yBt[i],ancho, altoBt);
            botones[i].setFont(new Font("Arial",6,size));
            botones[i].setOpaque(true);
            botones[i].setFocusPainted(false);
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setForeground(Color.BLACK);
            botones[i].setBorder(new LineBorder(Color.LIGHT_GRAY));
            add(botones[i]);
        }

    }

    private void Pantalla() {

        setLayout(null);
        setTitle("Calculadora");
        setSize(290, 455);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private void Numeros() {
        for (int i = 0; i < 10; i++){
            int numBoton = numBt[i];
            botones[numBoton].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    if (newnum){
                        if (!textoBotones[numBoton].equals("0")){
                            display.setText(textoBotones[numBoton]);
                            newnum = false;
                        }
                    }
                    else{
                        display.setText(display.getText() + textoBotones[numBoton]);
                    }
                }
            });
        }
    }

    private void Decimales(){
        botones[15].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!punto){
                    display.setText(display.getText() + textoBotones[15]);
                    punto = true;
                    newnum = false;
                }
            }
        });
    }

    private void Operaciones() {
        for (int numBoton : operacionesBotones) {
            botones[numBoton].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (operacion.equals("")) {
                        operacion = textoBotones[numBoton];
                        num2 = Double.parseDouble(display.getText());
                        newnum = true;
                        punto = false;
                    } else {
                        num2 = resultado();
                        operacion = textoBotones[numBoton];
                    }

                }
            });
        }

    }

    private double resultado(){

        num1 = Double.parseDouble(display.getText());

        switch (operacion){

            case "+" :  resultado = num2 + num1;
                break;
            case "-" :  resultado = num2 - num1;
                break;
            case "*" :  resultado = num2 * num1;
                break;
            case "/" :  resultado = num2 / num1;
                break;

        }

        DecimalFormat formatoResultado = new DecimalFormat("#.#####");
        display.setText(String.valueOf(formatoResultado.format(resultado)));
        limpiar();
        return resultado;

    }

    //Resetea los valores de la calculadora para poder continuar haciendo operaciones
    private void limpiar(){

        num1 = num2 = 0;
        operacion = "";
        newnum = true;
        punto = false;
    }


    private void Resultado() {
        botones[13].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultado();

            }
        });

    }

    private void Limpiar() {
        botones[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText("");
                limpiar();
            }
        });

    }

    public static void main(String[] args) {
        new Gui();
    }

}