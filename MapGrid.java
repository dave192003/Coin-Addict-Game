
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class MapGrid extends JFrame {

    private final int rows = 20;
    private final int columns = 20;
    private final Cell[][] grid = new Cell[rows][columns];
    private final LavaCell[][] wallgrid = new LavaCell[rows][columns];
    private int totalNonWallCells = 0;
    private int visitedCells = 0;

    private int characterRow = 0;
    private int characterCol = 0;

    public MapGrid() {
        setTitle("Maze Game");
        setSize(715, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 700));

        JPanel mazePanel = new JPanel(new GridLayout(rows, columns));
        mazePanel.setBounds(0, 0, 700, 700);
        mazePanel.setOpaque(false);

        layeredPane.add(mazePanel, JLayeredPane.DEFAULT_LAYER);
        Random rand = new Random();

        // Background sound
        SoundPlayer.loopSound("sounds/gameMusic.wav");

        // Fill grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int random = rand.nextInt(200);
                boolean makeWall = random < 50;

                if (r + c == 0) {
                    Cell startCell = new Cell(r, c);
                    java.net.URL imageURL = getClass().getResource("Image/rock.png");
                    startCell.setImageURL(imageURL);
                    mazePanel.add(startCell);
                    continue;

                }

                if (makeWall) {
                    LavaCell wallCell = new LavaCell(r, c);
                    wallgrid[r][c] = wallCell;
                    wallCell.setOpaque(true);
                    mazePanel.add(wallCell);
                } else {
                    Cell cell = new Cell(r, c);
                    grid[r][c] = cell;
                    cell.setOpaque(true);
                    cell.setBackground(Color.LIGHT_GRAY);
                    totalNonWallCells++;
                    mazePanel.add(cell);
                }
            }
        }

        // Character
        CharacterPanel character = new CharacterPanel("image/Male_wait.gif", 0, 0);
        character.setBounds(0, 0, 35, 35);
        character.setFocusable(true);
        layeredPane.add(character, JLayeredPane.PALETTE_LAYER);

        // Keyboard listener
        character.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int newRow = characterRow;
                int newCol = characterCol;
                java.net.URL imageURL = getClass().getResource("image/rock.png");

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        newRow--;
                        break;
                    case KeyEvent.VK_DOWN:
                        newRow++;
                        break;
                    case KeyEvent.VK_LEFT:
                        newCol--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        newCol++;
                        break;
                    default:
                        break;
                }

                if (wallgrid[newRow][newCol] != null) {
                    SoundPlayer.playSound("sounds/arayko.wav");
                    JOptionPane.showMessageDialog(null, "You fell into lava :(   Game Over.");
                    System.exit(0);
                }

                // Move character
                characterRow = newRow;
                characterCol = newCol;
                character.setLocation(characterCol * 35, characterRow * 35);

                if (grid[newRow][newCol] != null && !grid[newRow][newCol].isVisited()) {
                    java.net.URL newImageURL = getClass().getResource("/image/rock.png");
                    grid[newRow][newCol].setImageURL(newImageURL);
                    SoundPlayer.playSound("sounds/coins.wav");
                    grid[newRow][newCol].setVisited(true);
                    visitedCells++;
                    checkWinCondition();
                }

            }
        });

        add(layeredPane);
        setVisible(true);

        SwingUtilities.invokeLater(() -> character.requestFocusInWindow());
    }

    private void checkWinCondition() {
        if (visitedCells == totalNonWallCells) {
            JOptionPane.showMessageDialog(null, "Ang Galing mo! Mukha kang Pera!");
            System.exit(0);
        }
    }
}
