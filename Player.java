package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class Player {
    public int x, y;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public Player(){}
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void moveTop(){ this.y += 1; }
    public void moveBot(){ this.y -= 1; }
    public void moveLeft(){ this.x -= 1; }
    public void moveRight(){ this.x += 1; }

    public static void main(String[] args) {
        Player player = new Player();
        player.setPosition(15, 15);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];


        while(true) {
            StdDraw.clear();
            //set background on world
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }
            //set player on world
            world[player.x][player.y] = Tileset.PLAYER;
            ter.renderFrame(world);

            //player movement
            if(StdDraw.hasNextKeyTyped()){
                switch (StdDraw.nextKeyTyped()){

                    case 'w': //won't freeze after holding down and releasing 'w'
                        player.moveTop();
                        break;
                    case 'a': //programme will freeze after holding down and releasing 'a'
                        player.moveLeft();
                        break;
                    case 's': //programme will freeze after holding down and releasing 's'
                        player.moveBot();
                        break;

                    case 'd': //won't freeze after holding down and releasing 'd'
                        player.moveRight();
                        break;
                    /*
                    case 't': //won't freeze after holding down and releasing 't'
                        player.moveTop();
                        break;
                    case 'f': ////won't freeze after holding down and releasing 'f'
                        player.moveLeft();
                        break;
                    case 'g': ////won't freeze after holding down and releasing 'g'
                        player.moveBot();
                        break;

                    case 'h': ////won't freeze after holding down and releasing 'h'
                        player.moveRight();
                        break;
                        */

                    default: // however, if I rebind movement keys to "tfgh", programme won't freeze after holding down
                             // (and releasing) any of them
                        break;
                }

            }

        }
    }



}
