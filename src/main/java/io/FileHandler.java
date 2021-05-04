package io;

import board.Board;
import board.Elem;
import board.Field;
import board.elems.Player;
import board.elems.Raft;
import board.elems.Sea;
import board.elems.Shark;
import board.elems.resource.Barrel;
import board.elems.resource.Leaf;
import board.elems.resource.Plank;
import board.elems.resource.Trash;
import product.Fire;
import product.Net;
import product.WaterCleaner;
import ui.Tabla;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Map;
import java.util.Optional;

import static board.elems.Player.JATEKOS_TIPUS;
import static board.elems.Raft.RAFT_TYPE;
import static board.elems.Shark.SHARK_TYPE;
import static board.elems.resource.Barrel.BARREL_TYPE;
import static board.elems.resource.Leaf.LEAF_TYPE;
import static board.elems.resource.Plank.PLANK_TYPE;
import static board.elems.resource.Trash.TRASH_TYPE;
import static java.lang.Integer.parseInt;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingUtilities.invokeLater;
import static product.Fire.TUZ_TIPUS;
import static product.Fish.FISH_TYPE;
import static product.Net.NET_TYPE;
import static product.Potato.POTATO_TYPE;
import static product.WaterCleaner.WATER_CLEANER_TYPE;

public class FileHandler {

    private FileHandler() {
    }

    public static void loadGameFromClassPath(Board board, String fileName) {
        try {
            load(new InputStreamReader(
                    FileHandler.class.getResourceAsStream("/" + fileName)
            ), board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGame(Tabla tabla, Board board) {
        openFileChooser(tabla, false).ifPresent(filePath -> {
            try {
                load(new FileReader(filePath), board);
                invokeLater(() -> tabla.draw(tabla));
            } catch (IOException e) {
                e.printStackTrace();
                showMessageDialog(null, "File not found!", "warning", ERROR_MESSAGE);
            }
        });
    }

    private static void load(InputStreamReader reader, Board board) throws IOException {
        Player player = board.getPlayer();
        board.clear();
        try (BufferedReader br = new BufferedReader(reader)) {
            String sor = br.readLine();
            String[] jatekosMezok = sor.split(" ");
            player.setTimeTillRescued(parseInt(jatekosMezok[0]));
            player.setHunger(parseInt(jatekosMezok[1]));
            player.setThirst(parseInt(jatekosMezok[2]));
            player.getItems().put(PLANK_TYPE, parseInt(jatekosMezok[3]));
            player.getItems().put(LEAF_TYPE, parseInt(jatekosMezok[4]));
            player.getItems().put(TRASH_TYPE, parseInt(jatekosMezok[5]));
            player.getItems().put(POTATO_TYPE, parseInt(jatekosMezok[6]));
            player.getItems().put(FISH_TYPE, parseInt(jatekosMezok[7]));

            int i = 0;
            while ((sor = br.readLine()) != null) {
                String[] mezok = sor.split(" ");
                for (int j = 0; j < mezok.length; j++) {
                    Field m = new Field(i, j);
                    for (String jatekElem : mezok[j].split(";")) {
                        m.lehelyez(nevhezJatekElem(jatekElem, player));
                    }
                    board.getFields()[i][j] = m;
                }
                i++;
            }
        }
    }

    public static void saveGame(JFrame f, Board board) {
        openFileChooser(f, true).ifPresent(filePath -> {
            try {
                save(filePath + ".rft", board);
            } catch (IOException e) {
                e.printStackTrace();
                showMessageDialog(null, "File not found!", "warning", ERROR_MESSAGE);
            }
        });
    }

    private static void save(String fileName, Board board) throws IOException {
        Field[][] tabla = board.getFields();
        Player player = board.getPlayer();
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(player.getTimeTillRescued() + " ");
            fw.write(player.getHunger() + " ");
            fw.write(player.getThirst() + " ");
            for (Map.Entry<String, Integer> entry : player.getItems().entrySet()) {
                fw.write(entry.getValue() + " ");
            }
            fw.write("\n");
            for (Field[] fields : tabla) {
                for (Field field : fields) {
                    for (int k = 0; k < field.getElems().size(); k++) {
                        fw.write(field.getElems().get(k).tipus());
                        if (k < field.getElems().size() - 1) {
                            fw.write(";");
                        }
                    }
                    fw.write(" ");
                }
                fw.write("\n");
            }
        }
    }

    private static Optional<String> openFileChooser(JFrame f, boolean save) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("raft file extention", "rft"));
        int returnVal = save ? chooser.showSaveDialog(f) : chooser.showOpenDialog(f);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            return of(chooser.getSelectedFile().getAbsolutePath());
        return empty();
    }

    private static Elem nevhezJatekElem(String nev, Player player) {
        switch (nev) {
            case RAFT_TYPE:
                return new Raft();
            case SHARK_TYPE:
                return new Shark();
            case JATEKOS_TIPUS:
                return player; // ne csinalj uj jatekost
            case LEAF_TYPE:
                return new Leaf();
            case BARREL_TYPE:
                return new Barrel();
            case TRASH_TYPE:
                return new Trash();
            case PLANK_TYPE:
                return new Plank();
            case NET_TYPE:
                return new Net();
            case TUZ_TIPUS:
                return new Fire();
            case WATER_CLEANER_TYPE:
                return new WaterCleaner();
            default:
                return new Sea();
        }
    }

}

