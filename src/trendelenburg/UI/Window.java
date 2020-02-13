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
    JTextField textFieldAnswer = new JTextField();



    public Window() throws HeadlessException {
        super("Hallo Thommi");
        setLocationRelativeTo(null);
        setSize(900, 600);

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
                }
            });
        }
        labelFragen.setPreferredSize(new Dimension(230, fragen.size()*35+5));

    }


    private void zuweisen() {
        add(suche);
        add(labelAuswahl);
        add(textFieldAnswer);
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

        labelAuswahl.setSize(180, 30);
        labelAuswahl.setLocation(270, 40);
        labelAuswahl.setForeground(Color.RED);
        labelAuswahl.setBorder(new LineBorder(Color.BLACK));
        labelAuswahl.setVisible(true);

        textFieldAnswer.setSize(180, 30);
        textFieldAnswer.setLocation(270, 75);
        textFieldAnswer.setBorder(new LineBorder(Color.BLACK));
        textFieldAnswer.setHorizontalAlignment(JTextField.CENTER);
        textFieldAnswer.setVisible(true);

        js = new JScrollPane(labelFragen);
        js.setSize(230, 520);
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

}
