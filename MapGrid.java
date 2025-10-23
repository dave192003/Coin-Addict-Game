
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
    private Cell cell = new Cell(0, 0);

    public MapGrid() {
        setTitle("Maze Game");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mazePanel = new JPanel(new GridLayout(rows, columns));
        mazePanel.setOpaque(true);
        Random rand = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {

                int random = rand.nextInt(200);
                boolean makeWall = random < 28;

                if (makeWall) {
                    // create wall
                    LavaCell wallCell = new LavaCell(r, c);
                    wallgrid[r][c] = wallCell;
                    wallCell.setOpaque(true);
                    mazePanel.add(wallCell);
                    wallCell.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            JOptionPane.showMessageDialog(null, "You fell into lava :(   Game Over.");
                            System.exit(0);

                        }
                    });

                } else {
                    // create normal cell
                    Cell cell = new Cell(r, c);
                    grid[r][c] = cell;

                    cell.setOpaque(true);
                    cell.setBackground(Color.LIGHT_GRAY);
                    totalNonWallCells++;

                    // mouse hover event
                    cell.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (cell.isWall()) {
                                JOptionPane.showMessageDialog(null, "You fell into lava :(   Game Over.");
                                System.exit(0);
                            } else {
                                if (!cell.isVisited()) {
                                    java.net.URL imageURL = getClass().getResource("Image/rock.png");
                                    cell.setImageURL(imageURL);

                                    cell.setVisited(true);
                                    visitedCells++;
                                    checkWinCondition();
                                }
                            }
                        }
                    });

                    mazePanel.add(cell);
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int checkLocation = r + c;
                if (checkLocation == cell.getLocationRow()) {
                    cell.setBackground(Color.BLUE);

                }
            }
        }

        add(mazePanel);
        setVisible(true);
    }

    private void checkWinCondition() {
        if (visitedCells == totalNonWallCells) {
            JOptionPane.showMessageDialog(null, "Ang Galing mo! Mukha kang Pera!");
            System.exit(0);
        }
    }
}
