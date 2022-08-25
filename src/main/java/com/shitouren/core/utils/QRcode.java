package com.shitouren.core.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class QRcode {
    //文字显示
    private static final int QRCOLOR = 0x201f1f; // 二维码颜色:黑色
    private static final int BGWHITE = 0xFFFFFF; //二维码背景颜色:白色

    // 设置QR二维码参数信息
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;
        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置QR二维码的纠错级别(H为最高级别)
            put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码方式
            put(EncodeHintType.MARGIN, 0);// 白边
        }
    };

    /**
     * 生成二维码图片+背景+文字描述
     * @param codeFile 生成图地址
     * @param bgImgFile 背景图地址
     * @param WIDTH 二维码宽度
     * @param HEIGHT 二维码高度
     * @param qrUrl 二维码识别地址
     * @param note  文字描述1
     * @param tui   文字描述2
     * @param size 文字大小
     * @param imagesX 二维码x轴方向
     * @param imagesY 二维码y轴方向
     * @param text1X 文字描述1x轴方向
     * @param text1Y 文字描述1y轴方向
     * @param text2X 文字描述2x轴方向
     * @param text2Y 文字描述2y轴方向
     * @param hendImgFile 头像地址
     * @param hendX 头像X轴方向
     * @param hendY 头像y轴方向
     * @param hendWidth 头像宽度
     * @param hendHeight 头像高度
     */
    public static boolean CreatQRCode(File codeFile, File bgImgFile, Integer WIDTH, Integer HEIGHT, String qrUrl,
                                   String note, String tui, Integer size, Integer imagesX, Integer imagesY, Integer text1X, Integer text1Y
            , Integer text2X, Integer text2Y,String hendImgFile,Integer hendX,Integer hendY,Integer hendWidth,Integer hendHeight) {
        boolean falg = false;
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为: 编码内容,编码类型,生成图片宽度,生成图片高度,设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑(0xFFFFFFFF) 白(0xFF000000)两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
//                    image.setRGB();
                }
            }

            /*
             *     添加背景图片
             */
            BufferedImage backgroundImage = ImageIO.read(bgImgFile);
            Image src;
            File file = new File(hendImgFile);
            if (!file.exists()) {
                //通过网络路径获取
                InputStream inputStream=getImageStream(hendImgFile);
                src = ImageIO.read(inputStream);
            }else {
                //本地路径获取
                src = ImageIO.read(new File(hendImgFile));
            }
//            BufferedImage hendImage = ImageIO.read(hendImgFile);
            int bgWidth=backgroundImage.getWidth();
            int qrWidth=image.getWidth();
            //距离背景图片x边的距离，居中显示
            int disx=(bgWidth-qrWidth)-imagesX;
            //距离y边距离 * * * *
            int disy=imagesY;
            Graphics2D rng=backgroundImage.createGraphics();
            rng.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));
            rng.drawImage(image,disx,disy,WIDTH,HEIGHT,null);
            rng.drawImage(src,hendX,hendY,hendWidth,hendHeight,null);
            /*
             *     文字描述参数设置
             */

            Color textColor=Color.white;
            rng.setColor(textColor);
            rng.drawImage(backgroundImage,0,0,null);
            //设置字体类型和大小(BOLD加粗/ PLAIN平常)
            rng.setFont(new Font("微软雅黑,Arial",Font.BOLD,size));
            //设置字体颜色
            rng.setColor(Color.black);
            int strWidth=rng.getFontMetrics().stringWidth(note);

            //文字1显示位置
            int disx1=(bgWidth-strWidth)-text1X;//左右
            rng.drawString(note,disx1,text1Y);//上下
            Graphics2D rng1=backgroundImage.createGraphics();
            //设置字体类型和大小(BOLD加粗/ PLAIN平常)
            rng1.setFont(new Font("宋体",Font.PLAIN,7));
            //设置字体颜色
            rng1.setColor(Color.black);
            //文字2显示位置
            int disx2=(bgWidth-strWidth)-text2X;//左右
            if (tui.length() > 10){
                rng1.drawString(tui.substring(0,11),disx2,text2Y);//上下
                rng1.drawString(tui.substring(11,tui.length()),disx2,text2Y+rng1.getFontMetrics().getHeight());//上下
            }else {
                rng1.drawString(tui,disx2,text2Y);//上下
            }
            rng1.dispose();
            rng.dispose();
            image=backgroundImage;
            image.flush();
            ImageIO.write(image, "jpg", codeFile);
            falg = true;
        } catch (Exception e) {
            falg = false;
            e.printStackTrace();
        }finally {
            return falg;
        }
    }


    /**
     * 获取网络图片流
     *
     * @param url
     * @return
     */
    public static InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成二维码图片+背景+文字描述
     * @param codeFile 生成图地址
     * @param WIDTH 二维码宽度
     * @param HEIGHT 二维码高度
     * @param qrUrl 二维码识别地址
     */
    public static boolean CreatQRCode(File codeFile, Integer WIDTH, Integer HEIGHT, String qrUrl) {
        boolean falg = false;
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为: 编码内容,编码类型,生成图片宽度,生成图片高度,设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑(0xFFFFFFFF) 白(0xFF000000)两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
//                    image.setRGB();
                }
            }
            image.flush();
            ImageIO.write(image, "jpg", codeFile);
            falg = true;
        } catch (Exception e) {
            falg = false;
            e.printStackTrace();
        }finally {
            return falg;
        }
    }
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args)  {
        File bgImgFile=new File("D:/Users/97938/Downloads/beijingtu.jpg");//背景图片
        File QrCodeFile = new File("D:/Users/97938/Downloads/myqrcode.png");//生成图片位置
        String hendImgFile = "http://thirdwx.qlogo.cn/mmopen/vi_32/0vkF6JRL91AlkqnrzjJlw9BkQfg1mX0a8Afc1KctM2rk7NVQYsgzzicSVHpuC57XydE5KvgCzyF7sqLPETx6g5g/132";
        String url = "https://www.ctwing.cn/index.html";//二维码链接
        String note = "Hi!我是XXX" ;//文字描述
        String tui = "四点零分艰苦拉萨解放" ;//文字描述


        //宣传二维码生成
        //生成图地址,背景图地址,二维码宽度,二维码高度,二维码识别地址,文字描述1,文字描述2,文字大小,图片x轴方向,图片y轴方向,文字1||2xy轴方向
        CreatQRCode(QrCodeFile,bgImgFile, 50, 50, url, note,tui, 10, 20, 365, 120, 380, 120, 400,hendImgFile,20,370,40,40);

    }
}
