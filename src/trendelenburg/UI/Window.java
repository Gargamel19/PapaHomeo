package trendelenburg.UI;

import trendelenburg.data.Data;
import trendelenburg.data.Karte;
import trendelenburg.utils.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Window extends JFrame {

    public static ArrayList<Karte> karten;

    ArrayList<JButton> fragen = new ArrayList<>();
    JLabel labelFragen = new JLabel();
    JScrollPane js = new JScrollPane();

    JLabel labelNix = new JLabel();

    JTextField suche = new JTextField();

    JLabel labelAuswahl = new JLabel("AUSWAHL", JLabel.CENTER);

    JLabel labelAnswer = new JLabel("Antwort:", JLabel.CENTER);
    JTextField textFieldAnswer = new JTextField();
    JButton buttonCheck = new JButton("abgeben");
    JButton buttonRight = new JButton("anzeigen");

    JLabel labelLegende = new JLabel("Legende:", JLabel.CENTER);

    JLabel labelOutput = new JLabel("Check:", JLabel.CENTER);
    JLabel labelOutputStatus = new JLabel();

    JLabel labelOption1 = new JLabel("Richtig:", JLabel.CENTER);
    JLabel labelOption1COLOR = new JLabel();

    JLabel labelOption2 = new JLabel("Richtig dabei:", JLabel.CENTER);
    JLabel labelOption2COLOR = new JLabel();

    JLabel labelOption3 = new JLabel("Falsch:", JLabel.CENTER);
    JLabel labelOption3COLOR = new JLabel();



    public Window() throws HeadlessException {
        super("Hallo Thommi");
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(550, 390);

        Utils.readPropertys();
        karten = Data.readFromFileAsList(Utils.dataFile);
        dateienLaden();


        printNew();
    }

    public void dateienLaden(){
        for (int i = 0; i < karten.size(); i++) {

            JButton jbutton = new JButton(karten.get(i).getFrage() + "");
            jbutton.setLocation(20, i*35+5);
            jbutton.setSize(180, 30);
            labelFragen.add(jbutton);
            fragen.add(jbutton);
            jbutton.setVisible(true);
            jbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    labelAuswahl.setText(jbutton.getText());
                    labelAuswahl.setForeground(Color.GREEN);
                    labelOutputStatus.setOpaque(true);
                    labelOutputStatus.setBackground(Color.WHITE);
                    labelOutputStatus.setText("");
                    textFieldAnswer.setText("");

                }
            });
        }
        labelFragen.setPreferredSize(new Dimension(230, fragen.size()*35+5));

    }


    private void zuweisen() {
        add(suche);
        add(labelAuswahl);
        add(labelAnswer);
        add(textFieldAnswer);
        add(buttonCheck);
        add(buttonRight);
        add(labelOutput);
        add(labelOutputStatus);
        add(labelLegende);
        add(labelOption1);
        add(labelOption1COLOR);
        add(labelOption2);
        add(labelOption2COLOR);
        add(labelOption3);
        add(labelOption3COLOR);
        add(js);
        add(labelNix);
    }

    private void componentenRegistrieren() {
    }

    private void eigentschaften() {


        suche.setSize(180, 30);
        suche.setLocation(40, 5);
        suche.setVisible(true);
        suche.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent event) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                makeButtonListFilter(suche.getText());
                suche.requestFocus();
            }
        });

        labelAuswahl.setBackground(Color.BLACK);
        labelAuswahl.setOpaque(true);

        labelAuswahl.setSize(245, 30);
        labelAuswahl.setLocation(270, 40);
        labelAuswahl.setForeground(Color.RED);
        labelAuswahl.setBorder(new LineBorder(Color.BLACK));
        labelAuswahl.setVisible(true);

        labelAnswer.setSize(120, 30);
        labelAnswer.setLocation(270, 75);
        labelAnswer.setHorizontalAlignment(JTextField.RIGHT);
        labelAnswer.setVisible(true);

        textFieldAnswer.setSize(120, 30);
        textFieldAnswer.setLocation(395, 75);
        textFieldAnswer.setBorder(new LineBorder(Color.BLACK));
        textFieldAnswer.setHorizontalAlignment(JTextField.CENTER);
        textFieldAnswer.setVisible(true);
        textFieldAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkIfRight();
            }
        });

        labelOutput.setSize(120, 30);
        labelOutput.setLocation(270, 110);
        labelOutput.setHorizontalAlignment(JTextField.RIGHT);
        labelOutput.setVisible(true);

        labelOutputStatus.setSize(120, 30);
        labelOutputStatus.setLocation(395, 110);
        labelOutputStatus.setBorder(new LineBorder(Color.BLACK));
        labelOutputStatus.setHorizontalAlignment(JTextField.CENTER);
        labelOutputStatus.setVisible(true);

        buttonRight.setSize(120, 30);
        buttonRight.setLocation(270, 145);
        buttonRight.setBackground(Color.GRAY);
        buttonRight.setBorder(new LineBorder(Color.BLACK));
        buttonRight.setVisible(true);
        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < karten.size(); i++) {
                    if(karten.get(i).getFrage().equals(labelAuswahl.getText())) {
                        String out = textFieldAnswer.getText() + " (" + karten.get(i).getAntwort() + ")";
                        if(out.length()>10){
                            out = textFieldAnswer.getText().substring(0, 6) + "... (" + karten.get(i).getAntwort() + ")";
                        }
                        labelOutputStatus.setText(out);
                    }

                }
            }
        });

        buttonCheck.setSize(120, 30);
        buttonCheck.setLocation(395, 145);
        buttonCheck.setVisible(true);
        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkIfRight();
            }
        });

        labelLegende.setSize(120, 30);
        labelLegende.setLocation(395, 225);
        labelLegende.setHorizontalAlignment(JTextField.RIGHT);
        labelLegende.setVisible(true);

        labelOption1.setSize(120, 30);
        labelOption1.setLocation(270, 250);
        labelOption1.setHorizontalAlignment(JTextField.RIGHT);
        labelOption1.setVisible(true);

        labelOption1COLOR.setSize(120, 30);
        labelOption1COLOR.setLocation(395, 250);
        labelOption1COLOR.setOpaque(true);
        labelOption1COLOR.setBackground(Color.GREEN);
        labelOption1COLOR.setBorder(new LineBorder(Color.BLACK));
        labelOption1COLOR.setHorizontalAlignment(JTextField.CENTER);
        labelOption1COLOR.setVisible(true);

        labelOption2.setSize(120, 30);
        labelOption2.setLocation(270, 285);
        labelOption2.setHorizontalAlignment(JTextField.RIGHT);
        labelOption2.setVisible(true);

        labelOption2COLOR.setSize(120, 30);
        labelOption2COLOR.setLocation(395, 285);
        labelOption2COLOR.setOpaque(true);
        labelOption2COLOR.setBackground(Color.YELLOW);
        labelOption2COLOR.setBorder(new LineBorder(Color.BLACK));
        labelOption2COLOR.setHorizontalAlignment(JTextField.CENTER);
        labelOption2COLOR.setVisible(true);

        labelOption3.setSize(120, 30);
        labelOption3.setLocation(270, 320);
        labelOption3.setHorizontalAlignment(JTextField.RIGHT);
        labelOption3.setVisible(true);

        labelOption3COLOR.setSize(120, 30);
        labelOption3COLOR.setLocation(395, 320);
        labelOption3COLOR.setOpaque(true);
        labelOption3COLOR.setBackground(Color.RED);
        labelOption3COLOR.setBorder(new LineBorder(Color.BLACK));
        labelOption3COLOR.setHorizontalAlignment(JTextField.CENTER);
        labelOption3COLOR.setVisible(true);

        js = new JScrollPane(labelFragen);
        js.setSize(230, 310);
        js.setLocation(20, 40);
        js.setVisible(true);
        labelFragen.setVisible(true);



    }


    private void makeButtonListFilter(String searchExp){
        if(!searchExp.equals("")){
            int counter = 0;
            for (int i = 0; i < fragen.size(); i++) {

                if(fragen.get(i).getText().toLowerCase().contains(searchExp.toLowerCase())){
                    fragen.get(i).setLocation(20, counter*35+5);
                    fragen.get(i).setVisible(true);
                    counter++;
                }else{
                    fragen.get(i).setLocation(0, 0);
                    fragen.get(i).setVisible(false);
                }
                labelFragen.setPreferredSize(new Dimension(230, counter*35+5));
            }
        }else{
            makeButtonListAll();
        }

    }

    private void makeButtonListAll(){
        for (int i = 0; i < fragen.size(); i++) {

            fragen.get(i).setLocation(20, i*35+5);
            fragen.get(i).setVisible(true);

        }
        labelFragen.setPreferredSize(new Dimension(230, fragen.size()*35+5));
    }

    private void printNew(){
        eigentschaften();
        componentenRegistrieren();
        zuweisen();
        setVisible(true);
    }

    private void checkIfRight(){
        labelOutputStatus.setText("");
        String[] input = textFieldAnswer.getText().replace(" ", "").split(",");
        for (int i = 0; i < karten.size(); i++) {
            if(karten.get(i).getFrage().equals(labelAuswahl.getText())) {
                for (int j = 0; j < input.length; j++) {
                    if(karten.get(i).getAntwort().toLowerCase().equals(input[j].toLowerCase())) {
                        if(input.length>1) {
                            labelOutputStatus.setBackground(Color.YELLOW);
                            labelOutputStatus.setOpaque(true);
                        }else {
                            labelOutputStatus.setText(textFieldAnswer.getText());
                            labelOutputStatus.setBackground(Color.GREEN);
                            labelOutputStatus.setOpaque(true);
                        }
                        break;
                    }else{
                        labelOutputStatus.setBackground(Color.RED);
                        labelOutputStatus.setOpaque(true);
                    }
                }
            }
        }
    }

}
