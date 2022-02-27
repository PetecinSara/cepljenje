package si.feri.opj.Petecin.ui;

import si.feri.opj.Petecin.cepljenje.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Samostojni implements ActionListener {

    private JComboBox<Cepivo> serijskastevilkacomboBox;
    ArrayList<Cepivo> cepivo;

    public Samostojni(JComboBox<Cepivo> serijskastevilkacomboBox, ArrayList<Cepivo> cepivo) {
        this.serijskastevilkacomboBox = serijskastevilkacomboBox;
        this.cepivo = cepivo;
    };

    @Override
    public void actionPerformed(ActionEvent arg0) {
        DefaultComboBoxModel<Cepivo> model = (DefaultComboBoxModel<Cepivo>) this.serijskastevilkacomboBox.getModel();

        if(this.serijskastevilkacomboBox.getSelectedIndex() >= 0) {
            model.removeElementAt(this.serijskastevilkacomboBox.getSelectedIndex());
            cepivo.remove(this.serijskastevilkacomboBox.getSelectedIndex());
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("Cepiva.ser");
            GZIPOutputStream gzipoutputstream = new GZIPOutputStream(fileoutputstream);
            ObjectOutputStream outputstream = new ObjectOutputStream(gzipoutputstream);
            outputstream.writeObject(cepivo);
            outputstream.flush();
            outputstream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
