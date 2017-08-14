package lk.ilemo.viewer.view.other;

import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;
import java.net.URL;

public class WallpaperChanger {

    String filename = "D:\\2nd Semester Project\\Project\\ViewerClient\\src\\lk\\ilemo\\viewer\\view\\icons\\purple.jpg";
    String url = getClass().getResource("/lk/ilemo/viewer/view/icons/purple.jpg").getPath();
    
    public WallpaperChanger() {
        System.out.println("changing");
        String replace = url.replace("/", "\\");
        //System.out.println(replace);
        String newReplace = replace.replace("%"," ");
        String gs = newReplace.replace("20","");
        System.out.println("gs:"+gs);
        String substring = gs.substring(1);
        System.out.println(substring);
        //System.out.println(newReplace);
        System.out.println(filename);
        SPI.INSTANCE.SystemParametersInfo(
                new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), new UINT_PTR(0),
                substring, new UINT_PTR(SPI.SPIF_UPDATEINIFILE
                        | SPI.SPIF_SENDWININICHANGE));
        System.out.println("changed");
    }

    public interface SPI extends StdCallLibrary {

        long SPI_SETDESKWALLPAPER = 20;
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        @SuppressWarnings("serial")
        SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class,
                new HashMap<Object, Object>() {
            {
                put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                put(OPTION_FUNCTION_MAPPER,
                        W32APIFunctionMapper.UNICODE);
            }
        });

        boolean SystemParametersInfo(UINT_PTR uiAction, UINT_PTR uiParam,
                String pvParam, UINT_PTR fWinIni);
    }

    public static void main(String[] args) {
        WallpaperChanger c = new WallpaperChanger();
    }

}
