import controller.demo;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        try{
            //引入beautyeye
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }catch(Exception e){}
        UIManager.put("RootPane.setupButtonVisible", false);
        new demo();
    }
}
