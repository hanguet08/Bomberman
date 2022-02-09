package uet.oop.bomberman.level;

import java.io.*;
import java.net.URL;
import java.util.StringTokenizer;

public class LevelFile extends Level {
    public void insertFile(String path) {
        try {
            URL absPath = LevelFile.class.getResource("/levels/Level" + path + ".txt");
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(absPath.openStream()));
            String data = r.readLine();
            StringTokenizer s = new StringTokenizer(data);
            level_ = Integer.parseInt(s.nextToken());
            height_ = Integer.parseInt(s.nextToken())+1;
            width_ = Integer.parseInt(s.nextToken());
            lineTiles_ = new String[height_];
            for (int i = 1; i < height_; i++) {
                lineTiles_[i] = r.readLine().substring(0,width_);
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
