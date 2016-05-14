
    public MapComponent(int w, int h, int rows, int cols, Character you){
        width = w;
        height = h;
        row = rows;
        col = cols;
        map = new Tile[row][col];
        currentCol = col / 2;
        currentRow = row / 2;
        try {
            wall = ImageIO.read(new File("Wall.png"));
        } catch (IOException e) {
        }

        try {
            road = ImageIO.read(new File("Road.png"));
        } catch (IOException e) {
        }

        try {
            roadV = ImageIO.read(new File("RoadV.png"));
        } catch (IOException e) {
        }

        try {
            roadH = ImageIO.read(new File("RoadH.png"));
        } catch (IOException e) {
        }

        try {
            sideW = ImageIO.read(new File("SideWalk4.png"));
        } catch (IOException e) {
        }

        try {
            grass1 = ImageIO.read(new File("Grass1.png"));
        } catch (IOException e) {
        }

        try {
            grass2 = ImageIO.read(new File("Grass2.png"));
        } catch (IOException e) {
        }

        try {
            grass3 = ImageIO.read(new File("Grass3.png"));
        } catch (IOException e) {
        }

        try {
            wood = ImageIO.read(new File("Wood.png"));
        } catch (IOException e) {
        }

        try {
            door = ImageIO.read(new File("Door.png"));
        } catch (IOException e) {
        }

        try {
            mud = ImageIO.read(new File("Mud.png"));
        } catch (IOException e) {
        }
        int grass = 0;
        int randomL = 0;
        int randomW = 0;
        Room room = new Room("Simple House", you.getMove());
        int randomRow = 0;
        int randomCol = 0;
        //         while(empty()){
        //             randomRow = (int)(Math.random() * row);
        //             randomCol = (int)(Math.random() * col);
        //             if(map[randomRow = randomCol
        //         }
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(map[r][c] == null){
                    if(r < 20 || r > (row - 20) || (c < 20) || (c > col - 20)){
                        map[r][c] = new Tile(r,c, 100, 100);
                    }
                    else if(r % 100 == 40 && c % 100 == 40){
                        randomL = (int)(Math.random() * 10 + 11);
                        randomW = (int)(Math.random() * 10 + 11);
                        room.changeRoom(r,c,randomW,randomL);
                        for(int i = 0; i < randomW; i++){
                            for(int k = 0; k < randomL; k++){
                                map[r + i][c + k] = room.getTile(i,k);
                            }
                        }
                    }
                    else if(r % 100 < 5 || c % 100 < 5){
                        if(r % 100 == 2){
                            map[r][c] = new Tile(r, c, 1, 3);
                        }
                        else if(c % 100 == 2){
                            map[r][c] = new Tile(r, c, 2, 3);
                        }
                        else if(((r % 100 == 0 || r % 100 == 4) && (c % 100 > 3 || c % 100 == 0)) || ((c % 100 == 0 || c % 100 == 4) && (r % 100 > 3 || r % 100 == 0))){
                            map[r][c] = new Tile(r, c, 3, 3);
                        }
                        else {
                            map[r][c] = new Tile(r, c, 0, 3);
                        }
                    }
                    else{
                        grass = (int)((Math.random() * 6) + 10);
                        if(grass < 13){
                            map[r][c] = new Tile(r,c, grass, 4);
                        }
                        else{
                            map[r][c] = new Tile(r,c, 17, 7);
                        }
                    }
                    if(r == row / 2 && c == col / 2){
                        currentTile = map[r][c];
                        currentTile.addCharacter(you);
                    }
                }
            }
        }
    }