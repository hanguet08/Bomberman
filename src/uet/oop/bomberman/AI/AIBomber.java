package uet.oop.bomberman.AI;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tiles.Brick;
import uet.oop.bomberman.graphics.Sprite;


public class AIBomber extends AIAdvance {

    public AIBomber(Bomber bomber, Entity e) {
        _bomber = bomber;
        _e = e;
    }
    public AIBomber(){}


    // phần này dùng cho thuật toán BFS
    //gia tri matrix[x][y] la ten cua dinh tuong ung voi vi tri x y

    @Override
    public  void getMatrix(){

        int nameOfVertext=1;
        for ( int i = 1 ; i < heigh ; i++){
            for ( int j = 1 ; j < width ; j++){
                if (  BombermanGame.level.lineTiles_[i].charAt(j) =='#'  ){
                    matrix[i][j]=0;
                }
                else {
                    this.matrix[i][j]=nameOfVertext;
                    nameOfVertext++;
                }

            }

        }
    }
    
    @Override
    public int calculateDirection(int t) {
        // TODO: cài đặt thuật toán tìm đường đi


        this.getMatrix();
        
        //this.updateDestroy_Brick();
        this.convertNearNodeMatrix();
        // cập nhập liên tục
        int ex=(int)(this._bomber.x/32);
        int ey=(int)(this._bomber.y/32);

        if(t==3 )  ex=(int)Math.ceil((this._bomber.x/32));
        else if(t==1) ex=(int)Math.floor((this._bomber.x/32));
        else if(t==0) ey=(int) Math.ceil((this._bomber.y/32));
        else if(t==2) ey=(int)Math.floor((this._bomber.y/32));



//            đỉnh bắt đầu
        int start = this.matrix [ey][ex];
//             toaj ddoo dinh ket thuc laf toa do cua boober
        int end = this.matrix[(int)(this._e.y/32)][(int)(this._e.x/32)];

//             trả về đỉnh cần đi tiếp
        int result = this.bfs(start, end);

        if (result == -1 ) //random.nextInt(4);

            return -1;  // nếu đứng -1 cho đứng im



        if ( result - start == 1 ) {
            return 1; // ben phai
        }
        if ( start -  result == 1) {
            return 3; // ssang trai
        }
        if ( start > result ) return 0; // len tren
        if ( start < result ) return 2; // duoi

        return -1;

    }

    @Override
    public void pr() {
        System.out.println(this._e.y/32+"  " +this._e.x/32);
    }


    @Override
    public int setBomb() {
        double x= _bomber.x;;
        double y= _bomber.y;
        int direct= _bomber._direction;
        int bo=0;
        for(Entity a: BombermanGame.stillObjects){
            if(direct==1){
                if (Math.floor(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.x
                        &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                        &&a instanceof Brick) {
                    bo=1;
                }
            }
            if(direct==3){
                if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x
                        &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                        &&a instanceof Brick) {
                    bo=1;
                }
            }
            if(direct==0){
                if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                        &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.y
                        &&a instanceof Brick) {
                    bo=1;
                }
            }
            if(direct==2){
                if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                        &&Math.floor(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.y
                        &&a instanceof Brick) {
                    bo=1;
                }
            }


        }
        for(Entity a: BombermanGame.enemies){
            if(direct==1) {
                if (Math.abs(x - a.x) <= 64 && Math.ceil(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == Math.ceil(a.y)) {
                    bo = 1;
                }
            }
            if(direct==3){
                if (Math.abs(x - a.x) <= 64 &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==Math.ceil(a.y))
                {
                    bo=1;
                }
            }
            if(direct==0){
                if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                        &&Math.abs(y - a.y) <= 64) {
                    bo=1;
                }
            }
            if(direct==2){
                if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                        &&Math.abs(a.y-y)<=64) {
                    bo=1;
                }
            }
        }
        return bo;

    }


}
