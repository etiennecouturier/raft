package ui;

import board.Board;
import board.elems.Player;
import runner.CommandHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static io.FileHandler.loadGame;
import static io.FileHandler.saveGame;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.lang.Integer.parseInt;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BoxLayout.Y_AXIS;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingUtilities.getWindowAncestor;

public class Tabla extends JFrame {

    private final Board board;
    private final JPanel centerPanel = new JPanel();
    private final JPanel southPanel = new JPanel();
    private final JPanel westPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();
    private final JTextArea magyarazat = new JTextArea();
    private final Label korokSzamaLabel = new Label();
    private final Label szomjusagLabel = new Label();
    private final Label ehsegLabel = new Label();
    private final Label holmikLabel = new Label();
    private final JLabel commandLabel = new JLabel("command");
    private final JTextField commandField = new JTextField();
    private final JButton saveButton = new JButton("mentes");
    private final JButton loadButton = new JButton("betoltes");
    private final ImagePanel[] panels;

    private final CommandHandler commandHandler;

    public Tabla(Board board) {
        this.board = board;
        commandHandler = new CommandHandler(board);
        panels = new ImagePanel[board.getFields().length * board.getFields()[0].length];
        createAndShowGUI();
    }

    /**
     * GUI-t csinal
     */
    private void createAndShowGUI() {
        setTitle("Raft");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        Container container = getContentPane();
        southPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        southPanel.add(korokSzamaLabel, gbc);
        southPanel.add(szomjusagLabel, gbc);
        southPanel.add(ehsegLabel, gbc);
        southPanel.add(holmikLabel, gbc);
        eastPanel.setLayout(new BoxLayout(eastPanel, Y_AXIS));
        westPanel.add(magyarazat);
        commandField.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
        eastPanel.add(commandLabel);
        eastPanel.add(commandField);
        eastPanel.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));
        eastPanel.add(saveButton);
        eastPanel.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));
        eastPanel.add(loadButton);
        commandField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    int command;
                    try {
                        command = parseInt(commandField.getText());
                    } catch (NumberFormatException nfe) {
                        commandField.setText("");
                        return;
                    }
                    commandHandler.execute(command);
                    draw(getContentPane());
                    if (board.getPlayer().isDead()) {
                        showMessageDialog(getContentPane(), "Vesztettel!!!");
                        getWindowAncestor(getContentPane()).dispose();
                    }
                    if (board.getPlayer().hasHelpArrived()) {
                        showMessageDialog(getContentPane(), "Nyertel!!!");
                        getWindowAncestor(getContentPane()).dispose();
                    }
                    commandField.setText("");
                }
            }
        });

        saveButton.addActionListener(e -> saveGame(this, board));
        loadButton.addActionListener(e -> loadGame(this, board));
        magyarazat.setText("mozgas: szamok (kiveve 5)\n" +
                "\n" +
                "Parancsok irannyal:\n" +
                "palya.elemek.nyersanyag felvetele: 1\n" +
                "halo lehelyezese: 2\n" +
                "viztisztito lehelyezese: 3\n" +
                "tuzhely lehelyezese: 4\n" +
                "bovites: 5\n" +
                "\n" +
                "Irany nelkuli parancsok:\n" +
                "ivas: 90\n" +
                "hal evese: 91\n" +
                "burgonya evese: 92\n" +
                "horgaszat: 93\n" +
                "hal sutese: 94\n" +
                "burgonya sutese: 95");
        magyarazat.setEnabled(false);
        container.add(southPanel, BorderLayout.SOUTH);
        container.add(westPanel, BorderLayout.WEST);
        container.add(eastPanel, BorderLayout.EAST);
        draw(container);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void draw(Container contentPane) {
        Player player = board.getPlayer();
        korokSzamaLabel.setText(player.getFormattedTimeTillRescued());
        szomjusagLabel.setText(player.getFormattedThirst());
        ehsegLabel.setText(player.getFormattedHunger());
        holmikLabel.setText(player.getFormattedItems());
        GridLayout gridLayout = new GridLayout(board.getFields().length, board.getFields()[0].length);
        centerPanel.setLayout(gridLayout);
        centerPanel.setBorder(createEmptyBorder(2, 2, 2, 2));
        centerPanel.removeAll();
        addPanelsAndLabels();
        contentPane.add(centerPanel, BorderLayout.CENTER);
    }

    private void addPanelsAndLabels() {
        addPanelsAndImages();
        for (ImagePanel panel : panels) {
            centerPanel.add(panel);
        }
        centerPanel.revalidate();
    }

    private void addPanelsAndImages() {
        int count = 0;

        for (int row = 0; row < board.getFields().length; row++) {
            for (int col = 0; col < board.getFields()[0].length; col++) {
                ImagePanel imagePanel = new ImagePanel(board.getFields()[row][col].getTopElem().tipus());
                imagePanel.setBorder(createLineBorder(Color.BLACK));
                panels[count] = imagePanel;
                count++;
            }
        }
    }

}
