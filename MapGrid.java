
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;
import javax.swing.*;

public class MapGrid extends JFrame {

    private final int rows = 15;
    private final int columns = 15;
    private final Cell[][] grid = new Cell[rows][columns];
    private LavaCell[][] wallgrid = new LavaCell[rows][columns];
    private final Cell lavaShoesGrid[][] = new Cell[rows][columns];
    private int totalNonWallCells = 0;
    private int visitedCells = 0;
    private GameTimer gameTimer = new GameTimer();
    private ResultScreen resultsScreen = new ResultScreen();

    private int characterRow = 1;
    private int characterCol = 1;

    boolean walkableLava;

    public MapGrid() {
        setTitle("Coin");
        setSize(745, 785);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(740, 780));

        JPanel mazePanel = new JPanel(new GridLayout(rows, columns));
        mazePanel.setBounds(0, 0, 730, 749);
        mazePanel.setOpaque(false);

        layeredPane.add(mazePanel, JLayeredPane.DEFAULT_LAYER);
        Random rand = new Random();

        // Background sound loop
        SoundPlayer.loopSound("sounds/newGameMusic.wav");

        //lava shoes
        Cell lavaShoesCell;

        // Fill grid safely
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int random = rand.nextInt(200);
                boolean makeWall = random < 50;
                boolean randomCell = random < 8;
                boolean lavaShoes = random < 2;
                boolean islavaShoes = lavaShoes;

                if (r + c == 2 && r != 0 && c != 0 && r != rows - 1 && c != columns - 1) {
                    Cell startCell = new Cell(r, c);
                    URL startCellURL = getClass().getResource("/image/rock.png");
                    startCell.setImageURL(startCellURL);
                    mazePanel.add(startCell);
                    continue;
                } else if (lavaShoes && r != 0 && c != 0 && r != rows - 1 && c != columns - 1) {
                    lavaShoesCell = new Cell(r, c);
                    lavaShoesGrid[r][c] = lavaShoesCell;
                    URL lavaShoesCellURL = getClass().getResource("/image/lavaShoesGIF.gif");
                    lavaShoesCell.setImageURL(lavaShoesCellURL);
                    mazePanel.add(lavaShoesCell);
                    continue;

                }

                //rock cell 
                if (randomCell && r != 0 && c != 0 && r != rows - 1 && c != columns - 1) {
                    Cell rockCell = new Cell(r, c);
                    URL rockCellURL = getClass().getResource("/image/rockOnly.gif");
                    rockCell.setImageURL(rockCellURL);
                    mazePanel.add(rockCell);
                    continue;

                }

                // Left-edge wall
                if (c == 0) {
                    Cell edgeCell = new Cell(r, c);
                    java.net.URL rockOnlyURL = getClass().getResource("/image/wallRock.gif");
                    edgeCell.setImageURL(rockOnlyURL);
                    mazePanel.add(edgeCell);
                    continue;
                }

                //upper wall
                if (r == 0) {
                    Cell upperCell = new Cell(r, c);
                    URL upperWallCell = getClass().getResource("/image/wallRock.gif");
                    upperCell.setImageURL(upperWallCell);
                    mazePanel.add(upperCell);
                    continue;

                }

                //lower wall
                if (r == rows - 1) {
                    Cell lowerWallCell = new Cell(r, c);
                    URL lowerWall = getClass().getResource("/image/wallRock.gif");
                    lowerWallCell.setImageURL(lowerWall);
                    mazePanel.add(lowerWallCell);
                    continue;

                }

                if (c == columns - 1) {
                    Cell rightWallCell = new Cell(r, c);
                    URL rightWall = getClass().getResource("/image/wallRock.gif");
                    rightWallCell.setImageURL(rightWall);
                    mazePanel.add(rightWallCell);
                    continue;

                }

                // Starting cell (0,0)
                if (r + c == 0) {
                    Cell startCell = new Cell(r, c);
                    java.net.URL imageURL = getClass().getResource("/image/wallRock.gif");
                    startCell.setImageURL(imageURL);
                    mazePanel.add(startCell);
                    continue;
                }

                // Lava or walkable cell
                if (makeWall) {
                    wallgrid[r][c] = new LavaCell(r, c);
                    wallgrid[r][c].setWalkable(false);
                    wallgrid[r][c].setOpaque(true);
                    mazePanel.add(wallgrid[r][c]);
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

        // Character setup
        CharacterPanel character = new CharacterPanel("image/Male_wait.gif");
        character.setBounds(45, 47, 55, 55);
        character.setFocusable(true);
        layeredPane.add(character, JLayeredPane.PALETTE_LAYER);

        //Timer
        gameTimer.setBounds(305, 10, 150, 40);
        gameTimer.setBackgroundImage("/image/clock.png");
        gameTimer.makeTimer(1000);
        gameTimer.startTimer();

        layeredPane.add(gameTimer, JLayeredPane.PALETTE_LAYER);

        // Keyboard movement
        character.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int newRow = characterRow;
                int newCol = characterCol;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP ->
                        newRow--;
                    case KeyEvent.VK_DOWN ->
                        newRow++;
                    case KeyEvent.VK_LEFT -> {
                        newCol--;
                        character.setCharacterGIFMode("image/runleft.gif");
                    }
                    case KeyEvent.VK_RIGHT -> {
                        newCol++;
                        character.setCharacterGIFMode("image/Male_run.gif");

                    }
                    default -> {
                        character.setCharacterGIFMode("image/Male_idle.gif");

                    }
                }

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= columns) {
                    return;
                }

                // Collect coins
                if (grid[newRow][newCol] != null && !grid[newRow][newCol].isVisited()) {
                    java.net.URL newImageURL = getClass().getResource("/image/rock.png");
                    grid[newRow][newCol].setImageURL(newImageURL);
                    SoundPlayer.playSound("sounds/coins.wav");
                    grid[newRow][newCol].setVisited(true);
                    visitedCells++;
                    checkWinCondition();
                }

                //checks for lava shoes and set lava cells into walkable
                if (lavaShoesGrid[newRow][newCol] != null) {
                    walkableLava = true;
                    gameTimer.addTime(10);
                    URL lavaNewImageURL = getClass().getResource("/image/rock.png");
                    lavaShoesGrid[newRow][newCol].setImageURL(lavaNewImageURL);
                    SoundPlayer.playSound("sounds/gotLavaShoes.wav");

                }

                // Move character with proper scaling
                int cellWidth = 730 / columns;
                int cellHeight = 749 / rows;
                characterRow = newRow;
                characterCol = newCol;
                character.setLocation(characterCol * cellWidth, characterRow * cellHeight);

                //checks for lava cells
                if (wallgrid[newRow][newCol] != null && walkableLava != true) {
                    SoundPlayer.offSound(); // stops background
                    character.setCharacterGIFMode("image/Falls.gif");
                    SoundPlayer.playSound("sounds/fallIntoLava.wav");
                    gameTimer.stopTimer();

                    //Timer to pop up this results screen
                    Timer countDown = new Timer(1000, null);
                    countDown.addActionListener(new ActionListener() {
                        int timeLeft = 3;//initialized time left to decrement

                        @Override
                        public void actionPerformed(ActionEvent e
                        ) {
                            timeLeft--;
                            if (timeLeft == 0) {
                                countDown.stop();
                                SoundPlayer.playSound("sounds/arayko.wav");
                                resultsScreen.setImageURL("image/gameOver.gif");
                                resultsScreen.setImageCenter(layeredPane.getWidth(), layeredPane.getHeight(), resultsScreen.getWidth(), resultsScreen.getHeight());
                                layeredPane.add(resultsScreen, JLayeredPane.POPUP_LAYER);
                            }
                        }
                    }
                    );
                    countDown.start();

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
